package com.example.products.controllers;

import com.example.products.Main;
import com.example.products.models.User;
import com.example.products.services.UserService;
import com.example.products.services.impl.UserServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    private TableColumn<String, User> tbUserLogin;

    @FXML
    private TableColumn<String, User> tbUserName;

    @FXML
    private TableColumn<String, User> tbUserPosition;

    @FXML
    private TableView<User> tbUsers;

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnItemAdd)) {
            System.out.println("окно добавления");
            userAdd();
        } else if (event.getSource().equals(mnItemEdit)) {
            userEdit();

            System.out.println("окно редактирования");
        } else if (event.getSource().equals(mnItemDelete)) {
            deleteUser(tbUsers.getSelectionModel().getSelectedItem());
            System.out.println("окно удаления");
        }
    }

    private void deleteUser(User selectedItem) {
        System.out.println("Log--" + selectedItem.getId());
        UserServiceImpl.INSTANCE.deleteUser(selectedItem.getId());
        refreshTableUsers();
    }

    private void userAdd() {
        showForm(new User());
    }

    private void userEdit() {
        showForm(tbUsers.getSelectionModel().getSelectedItem());
    }


    private void showForm(User user) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("usersAddForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            UserEditController controller = loader.getController();
            controller.setUser(user);

            stage.setTitle("Редактирование пользователя");

            stage.showAndWait();
            refreshTableUsers();
            stage.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stage.close();
        }
    }

    @FXML
    void initialize() {
        tbUserLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tbUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbUserPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        refreshTableUsers();
    }

    private void refreshTableUsers() {
        tbUsers.setItems(FXCollections.observableList(UserService.INSTANCE.findAllUsers()));
    }

}
