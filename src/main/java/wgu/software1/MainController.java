package wgu.software1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    // Assigning the Parts Table and Columns
    @FXML public TableView partsTable;
    @FXML public TableColumn partsIDColumn;
    @FXML public TableColumn partsNameColumn;
    @FXML public TableColumn partsInventoryColumn;
    @FXML public TableColumn partsPriceColumn;



    @FXML
    protected void onAddPartClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toAddPart(stage);  // Pass stage to toSecond method
    }

    @FXML
    protected void onModifyPartClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toModifyPart(stage);  // Pass stage to toSecond method
    }

   @FXML protected void onAddProductClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toAddProduct(stage);  // Pass stage to toSecond method
    }

    @FXML protected void onModifyProductClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toModifyProduct(stage);  // Pass stage to toSecond method
    }

    @FXML
    protected void onDeleteClick(ActionEvent event) throws IOException {

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

    @FXML protected void onExitClick(ActionEvent event) throws IOException {
        Platform.exit();
    }

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

    public void initialize() {
        System.out.println("Program started.");
        partsTable.setItems(PartsList.getPartsList());
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
