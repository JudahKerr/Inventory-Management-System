package wgu.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int id;
    private String name;

    private int inventory;

    private Double price;

    private int min;

    private int max;

    private static ObservableList<Part> partListProducts = FXCollections.observableArrayList();


    public Product(int id, String name, int inventory, double price, int min, int max, ObservableList partListProducts) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.price = price;
        this.min = min;
        this.max = max;
        this.partListProducts = partListProducts;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public double getPrice() {
        return price;
    }

    public int getMin() { return min;}

    public int getMax() {
        return max;
    }

    public static ObservableList<Part> getPartListProducts() {
        return partListProducts;
    }
}
