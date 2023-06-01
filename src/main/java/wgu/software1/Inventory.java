package wgu.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getID() == partId) {
                return part;
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getID() == productId) {
                return product;
            }
        }
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public void updatePart(int index, Part updatedPart) {
        allParts.set(index, updatedPart);
    }

    public void updateProduct(int index, Product updatedProduct) {
        allProducts.set(index, updatedProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
