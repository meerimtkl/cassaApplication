package com.example.products.controllers;

import com.example.products.Main;
import com.example.products.models.Category;
import com.example.products.services.CategoryService;
import com.example.products.services.impl.CategoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class CategoryController {
   // private CategoryServiceImpl categoryService = CategoryServiceImpl.getINSTANCE();
    @FXML
    private ListView<Category> listViewCategories;

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;


    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnItemAdd)) {
            addCategory();
        } else if (event.getSource().equals(mnItemEdit)) {
            editCategory();
        } else if (event.getSource().equals(mnItemDelete)) {
            deleteCategory();
        }
    }

    private void deleteCategory() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().get();
        if (result.equals(ButtonType.YES)) {
            Category category = listViewCategories.getSelectionModel().getSelectedItem();

            CategoryService.INSTANCE.delete(category.getId());
            refreshList();
        }

    }

    private void editCategory() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("categoryEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            Category category=listViewCategories.getSelectionModel().getSelectedItem();
            CategoryEditController controller=loader.getController();
            controller.setCategory(category);
            stage.showAndWait();
            refreshList();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCategory() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("categoryEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Форма редактирования категории");
            CategoryEditController controller=loader.getController();
            controller.setCategory(new Category());

            stage.showAndWait();

            refreshList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        refreshList();


    }

    private void refreshList() {
        List<Category> categories=CategoryService.INSTANCE.getCategories();
        listViewCategories.setItems(FXCollections.observableList(categories));
    }

}
