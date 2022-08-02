package com.example.products.controllers;

import com.example.products.models.Category;
import com.example.products.models.Product;
import com.example.products.models.Unit;
import com.example.products.services.CategoryService;
import com.example.products.services.UnitService;
import com.example.products.services.impl.CategoryServiceImpl;
import com.example.products.services.impl.ProductServiceImpl;
import com.example.products.services.impl.UnitServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ProductEditController {
    private Product product;
   // private CategoryServiceImpl categoryService = CategoryServiceImpl.getINSTANCE();
    //private UnitServiceImpl unitService = UnitServiceImpl.getINSTANCE();

    @FXML
    private ComboBox<Category> cmbCategories;

    @FXML
    private ComboBox<Unit> cmbUnits;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtBarcode;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    void onCancelClicked(ActionEvent event) {

    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String barcode = txtBarcode.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        int discount = Integer.parseInt(txtDiscount.getText());
        Category category = cmbCategories.getSelectionModel().getSelectedItem();
        Unit unit = cmbUnits.getSelectionModel().getSelectedItem();


        product.setName(name);
        product.setPrice(price);
        product.setBarcode(barcode);
        product.setAmount(amount);
        product.setDiscount(discount);
        product.setCategory(category);
        product.setUnit(unit);
        ProductServiceImpl.INSTANCE.save(product);

    }

    public void setProduct(Product product) {
        this.product = product;
        if (product.getId() != null) {
            txtName.setText(product.getName());
            txtAmount.setText(String.valueOf(product.getAmount()));
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtBarcode.setText(product.getBarcode());
            txtDiscount.setText(String.valueOf(product.getDiscount()));
            cmbCategories.getSelectionModel().select(product.getCategory());
            cmbUnits.getSelectionModel().select(product.getUnit());
        }
    }

    @FXML
    void initialize() {
        cmbCategories.setItems(FXCollections.observableList(CategoryService.INSTANCE.getCategories()));
        cmbUnits.setItems(FXCollections.observableList(UnitService.INSTANCE.getUnits()));
    }
}
