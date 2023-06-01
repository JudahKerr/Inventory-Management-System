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
    @FXML private TextField stockField;
    @FXML private TextField priceField;
    @FXML private TextField minField;
    @FXML private TextField maxField;
    @FXML private TextField machineField;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    @FXML private Label machineText;


    private Boolean isOutsourced = false;

    // Changing the Label based on the radio buttons
    @FXML public void outsourcedRadioClick(ActionEvent event) throws IOException {
        machineText.setText("Company Name");
        isOutsourced = true;
    }
    @FXML public void houseRadioClick(ActionEvent event) throws IOException {
        machineText.setText("Machine ID");
        isOutsourced = false;
    }

   public void onSaveClick(ActionEvent event) throws IOException {
       try {
           // Random number for ID
           int randomNum;
           do {
               randomNum = (int)(Math.random() * 1000);
           } while (Inventory.lookupPart(randomNum) != null);

           // Parsing the text-fields
           String name = nameField.getText();
           int stock = Integer.parseInt(stockField.getText());
           double price = Double.parseDouble(priceField.getText());
           int min = Integer.parseInt(minField.getText());
           int max = Integer.parseInt(maxField.getText());




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

           if(max < min || max < 0 || min < 0) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setContentText("Max and Min values not valid");
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

           // Adding the part to the Inventory
           if(!isOutsourced) {
               int machineID = Integer.parseInt(machineField.getText());
               InHouse newPart = new InHouse(randomNum, name, stock, price, min, max, machineID);
               Inventory.addPart(newPart);
           } else {
               String companyName = machineField.getText();
               Outsourced newPart = new Outsourced(randomNum, name, stock, price, min, max, companyName);
               Inventory.addPart(newPart);
           }



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
