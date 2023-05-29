package wgu.software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    // Adding Test Data when the application launches
    private static void addTestData() {
        Part test = new Part(1, "Schwinn Tire", 3, 4.99);
        PartsList.addPart(test);
        PartsList.addPart(new Part(2, "Screw Bracket", 6, 14.99));
        PartsList.addPart(new Part(3, "Headlight", 26, 7.99));

    }

    public static void main(String[] args) {
        addTestData(); //Calling testdata before launch
        launch();
    }
}