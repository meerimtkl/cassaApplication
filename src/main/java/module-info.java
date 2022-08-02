module com.example.products {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.products to javafx.fxml;
    exports com.example.products;
    exports com.example.products.controllers;
    exports com.example.products.models;
    opens com.example.products.controllers to javafx.fxml;


}