package wgu.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int id;
    private String name;

    private int stock;

    private Double price;

    private int min;

    private int max;

    private  ObservableList<Part> partListProducts = FXCollections.observableArrayList();


    public Product(int id, String name, int stock, double price, int min, int max, ObservableList partListProducts) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.min = min;
        this.max = max;
        this.partListProducts = partListProducts;
    }

    // Setters
    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }


    // Getters
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public int getMin() { return min;}

    public int getMax() {
        return max;
    }

    public ObservableList<Part> getPartListProducts() {
        return partListProducts;
    }

    public void addAssociatedPart(Part part) {
        this.partListProducts.add(part);
    }

    public void deleteAssociatedPart(Part part) {
        this.partListProducts.remove(part);
    }
}
