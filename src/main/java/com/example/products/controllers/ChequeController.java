package com.example.products.controllers;

import com.example.products.models.OperationProducts;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class ChequeController {

    @FXML
    private ListView<OperationProducts> chequeLV;

    @FXML
    private TextField txtChange;
    @FXML
    void initialize() {
    }
    public void setData(double change, List<OperationProducts>operationProductsList){
        txtChange.setText(String.valueOf(change));
        chequeLV.setItems(FXCollections.observableList(operationProductsList));
    }
}
