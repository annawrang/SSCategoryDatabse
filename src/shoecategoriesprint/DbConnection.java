package shoecategoriesprint;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DbConnection {

    private Properties p = new Properties();

    public DbConnection() {
        try {
            p.load(new FileInputStream("src/ShoeCategoriesPrint/settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printCategory(String kategori) {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("ConnectionString"),
                p.getProperty("username"),
                p.getProperty("password"))) {

            PreparedStatement prepStmt = con.prepareStatement(
                    "select categories.id, categories.CategoryName, "
                    + "model.name, count(shoes.id), model.id "
                    + " from categories left join categorymapping on categorymapping."
                    + "CategoryID=categories.id "
                    + "left join model on model.id=categorymapping.modelid "
                    + "left join shoes on model.id=shoes.modelid group by "
                    + "categories.CategoryName, model.name having categories.categoryname=?;");
            prepStmt.setString(1, kategori);
            ResultSet rs = prepStmt.executeQuery();
            Category category = null;
            Map<Integer, Category> categoryListOne = new HashMap<>();
            while (rs.next()) {
                int categoryId = rs.getInt("categories.id");
                String modelName = rs.getString("model.name");
                int modelId = rs.getInt("model.id");
                Model model = new Model(modelName, modelId);
                category = categoryListOne.get(categoryId);
                if (category == null) {
                    category = new Category(rs.getString("categories.CategoryName"));
                    categoryListOne.put(categoryId, category);         // CATEGORY-MAP - instansvariabel här
                }

                Map<Integer, Model> modelsOne = category.getModelIdMap();
                modelsOne.put(modelId, model);
            }

            if (category == null) {
                System.out.println("Kategorin finns inte.");
            } else {
                System.out.println("Kateogri: " + category.getName().toUpperCase());
                for (int id : category.getModelIdMap().keySet()) {

                    System.out.print("  ModellID: " + id);
                    System.out.println("  Modellnamn: " + (category.getModelIdMap().get(id).getName()));
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printAllCategories() {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("ConnectionString"),
                p.getProperty("username"),
                p.getProperty("password"))) {

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select categories.id, categories.CategoryName, "
                    + "model.name, count(shoes.id), model.id "
                    + " from categories left join categorymapping on categorymapping."
                    + "CategoryID=categories.id "
                    + "left join model on model.id=categorymapping.modelid "
                    + "left join shoes on model.id=shoes.modelid group by "
                    + "categories.CategoryName, model.name;");

            Map<Integer, Category> categoryList = new HashMap<>();
            Category temp;

            while (r.next()) {
                int categoryId = r.getInt("categories.id");
                String modelName = r.getString("model.name");
                int modelId = r.getInt("model.id");
                Model model = new Model(modelName, modelId);
                temp = categoryList.get(categoryId);
                if (temp == null) {
                    temp = new Category(r.getString("categories.CategoryName"));
                    categoryList.put(categoryId, temp);         // CATEGORY-MAP - instansvariabel här
                }

                Map<Integer, Model> models = temp.getModelIdMap();
                models.put(modelId, model);
            }

            for (Map.Entry<Integer, Category> entry : categoryList.entrySet()) {
                Category catTemp = entry.getValue();
                System.out.println("Kategori: " + catTemp.getName().toUpperCase());
                for (int id : catTemp.getModelIdMap().keySet()) {

                    System.out.print("  ModellID: " + id);
                    System.out.println("  Modellnamn: " + (catTemp.getModelIdMap().get(id).getName()));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
