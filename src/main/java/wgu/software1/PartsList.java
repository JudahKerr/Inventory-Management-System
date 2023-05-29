package wgu.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartsList {
    private static ObservableList<Part> partsList = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        partsList.add(part);
    }

    public static ObservableList<Part> getPartsList() {
        return partsList;
    }
}
