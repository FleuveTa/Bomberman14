module com.example.bomberm4n {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.bomberm4n to javafx.fxml;
    exports com.example.bomberm4n;
}