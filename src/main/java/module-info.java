module wgu.software1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens wgu.software1 to javafx.fxml;
    exports wgu.software1;
}