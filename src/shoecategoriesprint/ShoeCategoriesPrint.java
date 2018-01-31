package shoecategoriesprint;

import java.util.*;

public class ShoeCategoriesPrint {

    private final DbConnection db = new DbConnection();

    public ShoeCategoriesPrint() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Vilken kategori vill du se produkter för? "
                    + "(Om alla skriv ALLA)");
            String in = sc.next();
            if (in.equalsIgnoreCase("alla")) {
                db.printAllCategories();
            } else {
                db.printCategory(in);
            }
        }
    }

    public static void main(String[] args) {
        ShoeCategoriesPrint ss = new ShoeCategoriesPrint();
    }

}
