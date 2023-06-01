package wgu.software1;

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

public class ModifyProductController {

    // Assigning the Parts Table and Columns
    @FXML public TableView<Part> partsTable;
    @FXML public TableColumn<Part, Integer> partsIDColumn;
    @FXML public TableColumn<Part, String> partsNameColumn;
    @FXML public TableColumn<Part, Integer> partsStockColumn;
    @FXML public TableColumn<Part, Double> partsPriceColumn;
    @FXML public TextField partsSearchField;

    // Assigning the Product Text Fields

    @FXML public TextField productIDField;
    @FXML public TextField productNameField;
    @FXML public TextField productStockField;
    @FXML public TextField productPriceField;
    @FXML public TextField productMinField;
    @FXML public TextField productMaxField;


    //  Adding Active Parts Table

    @FXML public TableView<Part> activePartsTable;
    @FXML public TableColumn<Part, Integer> activePartsIDColumn;
    @FXML public TableColumn<Part, String> activePartsNameColumn;
    @FXML public TableColumn<Part, Integer> activePartsStockColumn;
    @FXML public TableColumn<Part, Double> activePartsPriceColumn;

    private static Product selectedProduct = null;

    public static void getProduct(Product product) {
        selectedProduct = product;
    }


    public void onRemoveClick(ActionEvent event) throws IOException {
        Part selectedPart = (Part) activePartsTable.getSelectionModel().getSelectedItem();

        //  If nothing selected throw up an error
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected to remove.");
            alert.showAndWait();
        } else {
            //  Confirming the delete
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Please confirm you want to delete this item.");
            alert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    activePartsTable.getItems().remove(selectedPart); // Removing selection from the partsTable list
                }
            });
        }
    }

    public void onSaveClick(ActionEvent event) throws IOException {
        try {

            int id = selectedProduct.getID();

            // Parsing the text-fields
            String name = productNameField.getText();
            int stock = Integer.parseInt(productStockField.getText());
            double price = Double.parseDouble(productPriceField.getText());
            int min = Integer.parseInt(productMinField.getText());
            int max = Integer.parseInt(productMaxField.getText());
            ObservableList<Part> activeParts = activePartsTable.getItems();

            if(name.length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                return;
            }

            if(price < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Price cannot be less than 0.");
                alert.showAndWait();
                return;
            }

            if(activeParts.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Product needs to contain at least 1 part.");
                alert.showAndWait();
                return;
            }

            if(stock < 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inventory cannot be less than 0.");
                alert.showAndWait();
                return;
            }

            if(max < min || max < 0 || min < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Max and Min values not valid");
                alert.showAndWait();
                return;
            }

            // Adding the product to the product list
            Product newProduct = new Product(id, name, stock, price, min, max, activeParts);
            Inventory.addProduct(newProduct); // Adding the new product
            Inventory.deleteProduct(selectedProduct); // Deleting the old product
            //Back to MainScreen
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
            toMainScreen(stage);

        } catch (NumberFormatException e) {
            // Catching if there is a type mismatch and throwing up an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid values in all fields.");
            alert.showAndWait();
        }
    }

    public void onAddClick(ActionEvent event) throws IOException {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected to add.");
            alert.showAndWait();
        } else {
                activePartsTable.getItems().add(selectedPart);
        }
    }




    @FXML protected void onCancelClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toMainScreen(stage);  // Pass stage to toSecond method
    }

    public void toMainScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Inventory Management System");
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

    public void initialize() {

        // Setting the data for the Parts Table
        partsTable.setItems(Inventory.getAllParts());
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Setting the Product Text Fields
        productIDField.setText(String.valueOf(selectedProduct.getID()));
        productNameField.setText(selectedProduct.getName());
        productStockField.setText(String.valueOf(selectedProduct.getStock()));
        productPriceField.setText(String.valueOf(selectedProduct.getPrice()));
        productMinField.setText(String.valueOf(selectedProduct.getMin()));
        productMaxField.setText(String.valueOf(selectedProduct.getMax()));

        // Setting the Active Parts Table
        activePartsTable.setItems(selectedProduct.getPartListProducts());
        activePartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        activePartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        activePartsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        activePartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
