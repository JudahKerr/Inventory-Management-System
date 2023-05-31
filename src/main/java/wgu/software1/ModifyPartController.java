package wgu.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartController {

    @FXML public TextField idField;
    @FXML public TextField nameField;
    @FXML public TextField inventoryField;
    @FXML public TextField priceField;
    @FXML public TextField minField;
    @FXML public TextField maxField;
    @FXML public TextField machineField;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    @FXML private Label machineText;


    private static Part selectedPart = null;
    private Boolean isOutsourced = false;
    public static void getPart(Part part) {
        selectedPart = part;
    }

    @FXML public void onSaveClick(ActionEvent event) throws IOException {


        try {

            int id = selectedPart.getID();

            // Parsing the text-fields
            String name = nameField.getText();
            int inventory = Integer.parseInt(inventoryField.getText());
            double price = Double.parseDouble(priceField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            String machineID = machineField.getText();
            Boolean machineType = isOutsourced;

            if(max < min || max < 0 || min < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Max and Min values not valid");
                alert.showAndWait();
                return;
            }

            // Adding the part to the PartsList
            Part newPart = new Part(id, name, inventory, price, min, max, machineID, machineType);
            PartsList.addPart(newPart); // Adding the new part
            PartsList.removePart(selectedPart); // Deleting the old part
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
    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();  // Get the current stage
        toMainScreen(stage);  // Pass stage to toSecond method
    }

    @FXML public void outsourcedRadioClick(ActionEvent event) throws IOException {
        machineText.setText("Company Name");
        isOutsourced = true;
    }
    @FXML public void houseRadioClick(ActionEvent event) throws IOException {
        machineText.setText("Machine ID");
        isOutsourced = false;
    }

    public void toMainScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        inHouseRadio.setToggleGroup(group);
        outsourcedRadio.setToggleGroup(group);

        idField.setText(String.valueOf(selectedPart.getID()));
        nameField.setText(selectedPart.getName());
        inventoryField.setText(String.valueOf(selectedPart.getInventory()));
        priceField.setText(String.valueOf(selectedPart.getPrice()));
        minField.setText(String.valueOf(selectedPart.getMin()));
        maxField.setText(String.valueOf(selectedPart.getMax()));
        machineField.setText(selectedPart.getMachineID());

        if(selectedPart.isMachineType()) {
            outsourcedRadio.setSelected(true);
        } else {
            inHouseRadio.setSelected(true);
        }

    }
}
