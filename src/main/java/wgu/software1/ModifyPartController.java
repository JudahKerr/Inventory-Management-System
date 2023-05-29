package wgu.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartController {

    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {  // Added ActionEvent parameter
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
}
