package com.example.products.controllers;

import com.example.products.Main;
import com.example.products.models.OperationProducts;
import com.example.products.models.Product;
import com.example.products.models.User;
import com.example.products.services.OperationService;
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
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<OperationProducts> operationProductsList = new ArrayList<>();

    @FXML
    private TextField txtBarcode;
    @FXML
    private TextField txtUserCash;
    @FXML
    private TextField txtTotal;

    @FXML
    private MenuItem mnItemCategories;

    @FXML
    private Button btnEnter;

    @FXML
    private MenuItem mnItemProducts;

    @FXML
    private MenuItem mnItemUsers;


    @FXML
    private TableColumn<String, OperationProducts> colmAmount;

    @FXML
    private TableColumn<String, OperationProducts> colmDiscount;

    @FXML
    private TableColumn<String, OperationProducts> colmPrice;

    @FXML
    private TableColumn<String, OperationProducts> colmProduct;


    @FXML
    private Label cashierNameId;
    @FXML
    private TableColumn<String, OperationProducts> colmUnit;


    @FXML
    private TableColumn<String, OperationProducts> colmAmountPrice;
    @FXML
    private TableView<OperationProducts> tbOperations;


    private User user;

    public void setData(User user) {
        this.user = user;
        cashierNameId.setText(user.getName());
    }


    @FXML
    void onCloseBtnAction(ActionEvent event) {

        if (operationProductsList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Нет товаров для закрытия операции");
            alert.show();
            return;
        } else if (txtUserCash.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Введите сумму для оплаты!");
            alert.show();
            return;
        } else if (Double.parseDouble(txtUserCash.getText().trim()) >= Double.parseDouble(txtTotal.getText().trim())) {
            boolean isSaveResult = OperationService
                    .INSTANCE
                    .closeAndSaveOperation(Double.parseDouble(txtTotal.getText()), 0, operationProductsList);
            if (!isSaveResult) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ошибка при закрытии операции!");
                alert.show();
                return;
            }

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("chequeForm.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);

                ChequeController controller = loader.getController();
                controller.setData(Double.parseDouble(txtUserCash.getText().trim()) - Double.parseDouble(txtTotal.getText().trim())
                        , operationProductsList);

                stage.showAndWait();
                operationProductsList.clear();
                refreshList();
                txtUserCash.clear();
                txtBarcode.clear();
                txtTotal.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Не хватате денег для закрытия оперции!");
            alert.show();
        }
    }

    private void addProductToList(Product product) {


        for (int i = 0; i < operationProductsList.size(); i++) {
            if (operationProductsList.get(i).getProduct().getId().equals(product.getId())) {
                operationProductsList.get(i).setAmount(operationProductsList.get(i).getAmount() + 1);
                operationProductsList.get(i).setTotal(operationProductsList.get(i).getAmount() * operationProductsList.get(i).getPriceWithDiscount());
                return;
            }
        }

        OperationProducts operationProducts = new OperationProducts();
        operationProducts.setAmount(1);
        operationProducts.setPriceWithDiscount(product.getPrice() * (100 - product.getDiscount()) / 100);
        operationProducts.setTotal(operationProducts.getAmount() * operationProducts.getPriceWithDiscount());
        operationProducts.setProduct(product);

        operationProductsList.add(operationProducts);

    }

    private void refreshList() {
        tbOperations.setItems(FXCollections.observableList(operationProductsList));
        tbOperations.refresh();

        double total = 0;
        for (OperationProducts item : operationProductsList
        ) {
            total += item.getTotal();
        }

        txtTotal.setText(String.valueOf(total));
    }


    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnItemCategories)) {
            showCategoriesForm();
        } else if (event.getSource().equals(mnItemProducts)) {
            showProductsForm();
        } else if (event.getSource().equals(mnItemUsers)) {
            showUserForm();
        }
    }

    private void showUserForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("usersForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Ошибка при переходе на users form");
            e.printStackTrace();
        }
    }

    private void showProductsForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("productsForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showCategoriesForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("categoriesForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void initialize() {

        colmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("priceWithDiscount"));
        colmAmountPrice.setCellValueFactory(new PropertyValueFactory<>("total"));
        colmProduct.setCellValueFactory(new PropertyValueFactory<>("product"));

    }

    public void onBtnEnterlicked(ActionEvent actionEvent) {
        Product product = ProductService.INSTANCE.findProductByBarcode(txtBarcode.getText());
        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Товар по штрих-коду не найден!");
            alert.show();
            return;
        }
        addProductToList(product);

        refreshList();
    }
}
