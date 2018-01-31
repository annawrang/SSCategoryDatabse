package shoecategoriesprint;

import java.util.*;

public class Category {

    private int id;
    private String name;
    private List<Map<Integer, Model>> modelList = new ArrayList<>();
    private Map<Integer, Model> modelIdMap = new HashMap<>();
    private int quantity;

    public Category(String categoryName) {
        this.name = categoryName;
        this.modelList.add(modelIdMap);
    }

    public Map<Integer, Model> getModelIdMap() {
        return modelIdMap;
    }

    public void setModelIdMap(Map<Integer, Model> modelIdMap) {
        this.modelIdMap = modelIdMap;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Map<Integer, Model>> getModelList() {
        return modelList;
    }

    public void setModelList(List<Map<Integer, Model>> secondModelList) {
        this.modelList = secondModelList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
