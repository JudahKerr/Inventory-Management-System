package wgu.software1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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
        // Adding Parts
        Inventory.addPart(new Part(1, "Schwinn Tire", 3, 4.99, 1, 10));
        Inventory.addPart(new Part(2, "Kayak Shell", 6, 14.99, 1, 1000));
        Inventory.addPart(new Part(3, "Headlight", 26, 7.99, 1, 25));
        Inventory.addPart(new Part(4, "Carbon Fiber Paddle", 77, 12, 1, 10));
        Inventory.addPart(new Part(5, "Bike Blinkers", 45, 29.99, 1, 1000));
        Inventory.addPart(new Part(6, "Basket", 42, 12.99, 1, 25));

        // Adding Products
        ObservableList<Part> bikeList = FXCollections.observableArrayList();
        bikeList.add(new Part(1, "Schwinn Tire", 3, 4.99, 1, 10));
        bikeList.add(new Part(3, "Headlight", 26, 7.99, 1, 25));
        bikeList.add(new Part(6, "Basket", 42, 12.99, 1, 25));

        ObservableList<Part> kayakList = FXCollections.observableArrayList();
        kayakList.add(new Part(2, "Kayak Shell", 6, 14.99, 1, 1000));
        kayakList.add(new Part(4, "Carbon Fiber Paddle", 77, 12, 1, 10));


        Inventory.addProduct(new Product(1, "Schwinn Bike", 12, 249.99, 1, 25, bikeList));
        Inventory.addProduct(new Product(2, "Kayak Bundle", 7, 449.99, 1, 25, kayakList));
    }

    public static void main(String[] args) {
        addTestData(); //Calling testdata before launch
        launch();
    }
}