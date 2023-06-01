package wgu.software1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    // Assigning the Parts Table and Columns
    @FXML public TableView<Part> partsTable;
    @FXML public TableColumn<Part, Integer> partsIDColumn;
    @FXML public TableColumn<Part, String> partsNameColumn;
    @FXML public TableColumn<Part, Integer> partsStockColumn;
    @FXML public TableColumn<Part, Double> partsPriceColumn;
    @FXML public TextArea partsSearchField;

    // Assigning the Product Table and Columns
    @FXML public TableView<Product> productsTable;

    @FXML public TableColumn<Product, Integer> productIDColumn;
    @FXML public TableColumn<Product, String> productNameColumn;
    @FXML public TableColumn<Product, Integer> productStockColumn;
    @FXML public TableColumn<Product, Double> productPriceColumn;

    @FXML public TextArea productsSearchField;




    @FXML
    protected void onAddPartClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toAddPart(stage);  // Pass stage to toSecond method
    }


    @FXML
    public void onModifyPartClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter


        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();

        //  If nothing selected throw up an error
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected to modify.");
            alert.showAndWait();
        } else {
            ModifyPartController.getPart(selectedPart);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
            toModifyPart(stage);  // Pass stage to toSecond method
        }

    }



   @FXML protected void onAddProductClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toAddProduct(stage);  // Pass stage to toSecond method
    }

    @FXML public void onModifyProductClick(ActionEvent event) throws IOException {

        Product selectedProduct = (Product) productsTable.getSelectionModel().getSelectedItem();

        //  If nothing selected throw up an error
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No product selected to modify.");
            alert.showAndWait();
        } else {
            ModifyProductController.getProduct(selectedProduct);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
            toModifyProduct(stage);  // Pass stage to toSecond method
        }

    }

    @FXML
    public void onPartDeleteClick(ActionEvent event) throws IOException {

        //  Assigning an object to the selected item in the parts table
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();

        //  If nothing selected throw up an error
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected for deletion.");
            alert.showAndWait();
        } else {
            //  Confirming the delete
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Please confirm you want to delete this item.");
            alert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    partsTable.getItems().remove(selectedPart); // Removing selection from the partsTable list
                }
            });
        }
    }

    @FXML
    public void onProductDeleteClick(ActionEvent event) throws IOException {

        //  Assigning an object to the selected item in the parts table
        Product selectedProduct = (Product) productsTable.getSelectionModel().getSelectedItem();

        //  If nothing selected throw up an error
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected for deletion.");
            alert.showAndWait();
        } else {
            //  Confirming the delete
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Please confirm you want to delete this item.");
            alert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    productsTable.getItems().remove(selectedProduct); // Removing selection from the partsTable list
                }
            });
        }
    }

    @FXML protected void onExitClick(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Platform.exit();
            }
        });}



    public void toAddPart(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 660, 492);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    public void toModifyPart(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModifyPart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 660, 492);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    public void toAddProduct(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    public void toModifyProduct(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModifyProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    public void onPartsSearch(KeyEvent event) throws IOException {
        ObservableList<Part> searchedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        String userInput = partsSearchField.getText().toLowerCase();
        int idSearch = -1;

        // Check if the userInput can be parsed to an integer
        try {
            idSearch = Integer.parseInt(userInput);
        } catch(NumberFormatException e) {
            // userInput is not an integer
        }

        for(Part i : allParts){
            if(i.getName().toLowerCase().contains(userInput)){
                searchedParts.add(i);
            } else if (i.getID() == idSearch){
                searchedParts.add(i);
            }
        }

        partsTable.setItems(searchedParts);

    }

    public void onProductsSearch(KeyEvent event) throws IOException {
        ObservableList<Product> searchedParts = FXCollections.observableArrayList();
        ObservableList<Product> allParts = Inventory.getAllProducts();
        String userInput = productsSearchField.getText().toLowerCase();
        int idSearch = -1;

        // Check if the userInput can be parsed to an integer
        try {
            idSearch = Integer.parseInt(userInput);
        } catch(NumberFormatException e) {
            // userInput is not an integer
        }

        for(Product i : allParts){
            if(i.getName().toLowerCase().contains(userInput)){
                searchedParts.add(i);
            } else if (i.getID() == idSearch){
                searchedParts.add(i);
            }
        }

        productsTable.setItems(searchedParts);

    }

    public void initialize() {
        System.out.println("Program started.");

        // Setting parts for the Parts Table
        partsTable.setItems(Inventory.getAllParts());
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Setting Products for the Products Table
        productsTable.setItems(Inventory.getAllProducts());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
