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
    @FXML public TextField stockField;
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
                InHouse newPart = new InHouse(id, name, stock, price, min, max, machineID);
                Inventory.addPart(newPart);
            } else {
                String companyName = machineField.getText();
                Outsourced newPart = new Outsourced(id, name, stock, price, min, max, companyName);
                Inventory.addPart(newPart);
            }

            Inventory.deletePart(selectedPart); // Deleting the old part
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
        stockField.setText(String.valueOf(selectedPart.getStock()));
        priceField.setText(String.valueOf(selectedPart.getPrice()));
        minField.setText(String.valueOf(selectedPart.getMin()));
        maxField.setText(String.valueOf(selectedPart.getMax()));
        if (selectedPart instanceof InHouse) {
            machineText.setText("Machine ID");
            String machineID = String.valueOf(((InHouse) selectedPart).getMachineId());
            machineField.setText(machineID);
            inHouseRadio.setSelected(true);
        } else if (selectedPart instanceof  Outsourced) {
            machineText.setText("Company Name");
            machineField.setText(((Outsourced) selectedPart).getCompanyName());
            outsourcedRadio.setSelected(true);
        }

//
//

    }
}
