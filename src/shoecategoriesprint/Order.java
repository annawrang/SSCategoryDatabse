package shoecategoriesprint;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

class Order {
    private int id;
    private List<Shoe> shoeList = new ArrayList<>();
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public void setShoeList(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
