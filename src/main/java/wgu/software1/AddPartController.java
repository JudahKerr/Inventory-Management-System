package wgu.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {
    @FXML private TextField nameField;
    @FXML private TextField inventoryField;
    @FXML private TextField priceField;
    @FXML private TextField minField;
    @FXML private TextField maxField;
    @FXML private TextField machineField;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    @FXML private Label machineText;



   public void onSaveClick(ActionEvent event) throws IOException {
       try {
           // Random number for ID
           int randomNum = (int)(Math.random() * 100);

           // Parsing the text-fields
           String name = nameField.getText();
           int inventory = Integer.parseInt(inventoryField.getText());
           double price = Double.parseDouble(priceField.getText());

           // Adding the part to the PartsList
           Part newPart = new Part(randomNum, name, inventory, price);
           PartsList.addPart(newPart);

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
    protected void onCancelClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        toMainScreen(stage);
    }

    // Changing the Label based on the radio buttons
    @FXML public void outsourcedRadioClick(ActionEvent event) throws IOException {
       machineText.setText("Company Name");
    }
    @FXML public void houseRadioClick(ActionEvent event) throws IOException {
        machineText.setText("Machine ID");
    }

    public void toMainScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() {

        //Adding Radio Buttons to a group
        ToggleGroup group = new ToggleGroup();
        inHouseRadio.setToggleGroup(group);
        outsourcedRadio.setToggleGroup(group);
    }
}
