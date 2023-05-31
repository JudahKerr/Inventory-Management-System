package wgu.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductList {

    private static ObservableList<Product> productsList = FXCollections.observableArrayList();

    public static void addProduct(Product product) {
        productsList.add(product);
    }

    public static void removeProduct(Product product) {
        productsList.remove(product);
    }

    public static ObservableList<Product> getProductsList() {
        return productsList;
    }
}
