package shoecategoriesprint;

import java.util.ArrayList;
import java.util.List;

class Model {

    private int id;
    private String name;
    private String brand;
    private String gender;
    private double price;
    private boolean active;
    private List<Shoe> shoeList = new ArrayList<>();
    private List<Feedback> feedbackList = new ArrayList<>();

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public Model(){}
    
    public Model(String modelName, int id) {
        this.name = modelName;
        this.id=id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public void setShoeList(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

}
