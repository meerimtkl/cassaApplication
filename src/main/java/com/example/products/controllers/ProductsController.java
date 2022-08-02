package com.example.products.controllers;

import com.example.products.Main;
import com.example.products.models.Product;
import com.example.products.services.ProductService;
import com.example.products.services.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;



public class ProductsController {
   // private ProductServiceImpl productService = ProductServiceImpl();
    @FXML
    private TableView<Product> tbProducts;

    @FXML
    private TableColumn<String, Product> colmAmount;

    @FXML
    private TableColumn<String, Product>  colmBarcode;

    @FXML
    private TableColumn<String, Product>  colmCategory;

    @FXML
    private TableColumn<String, Product>  colmDiscount;

    @FXML
    private TableColumn<String, Product>  colmName;

    @FXML
    private TableColumn<String, Product>  colmPrice;

    @FXML
    private TableColumn<String, Product>  colmUnit;

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;



    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnItemAdd)){
            addProduct();
        }else if (event.getSource().equals(mnItemEdit)){
            editProduct();
        }else if(event.getSource().equals(mnItemDelete)){
            deleteProduct();

        }
    }

    private void deleteProduct() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().get();
        if (result.equals(ButtonType.YES)) {
            Product product = tbProducts.getSelectionModel().getSelectedItem();


            ProductService.INSTANCE.delete(product.getId());
            refreshTable();
        }
    }

    private void showForm(Product product){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("productEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            ProductEditController controller = loader.getController();
            controller.setProduct(product);
            stage.setTitle("Редактирование данных продукта");

            stage.showAndWait();

            refreshTable();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            stage.close();
        }
    }

    private void editProduct() {
        showForm(tbProducts.getSelectionModel().getSelectedItem());


    }

    private void addProduct() {
        showForm(new Product());
    }

    @FXML
    void initialize() {

        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colmBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colmUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));

        refreshTable();


    }

    private void refreshTable() {
        tbProducts.setItems(FXCollections.observableList(ProductServiceImpl.INSTANCE.getProducts()));
    }

}
