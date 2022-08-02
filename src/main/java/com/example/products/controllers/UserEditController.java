package com.example.products.controllers;

import com.example.products.db.UserDB;
import com.example.products.models.Position;
import com.example.products.models.User;
import com.example.products.services.PositionService;
import com.example.products.services.UserService;
import com.example.products.services.impl.PositionServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UserEditController {

    @FXML
    private ComboBox<Position> cmbPosition;

    @FXML
    private Button saveButton;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    public void setUser(User user) {
        System.out.println("LOG--" + user);

        this.user = user;
        if (this.user.getId() != null) {
            txtName.setText(this.user.getName());
            txtLogin.setText(this.user.getLogin());
            txtPassword.setText(this.user.getPassword());
            cmbPosition.getSelectionModel().select(this.user.getPosition());
        }
    }
    @FXML
    void onSaveBtnClicked(ActionEvent event) {
        String name = txtName.getText().trim();
        String password = txtPassword.getText().trim();
        String login = txtLogin.getText().trim();
        Position cmbxPosition = cmbPosition.getSelectionModel().getSelectedItem();
        System.out.println(this.user.getId());
        System.out.println(name);
        System.out.println(password);
        System.out.println(login);
        System.out.println(cmbxPosition);
        user.setLogin(txtLogin.getText().trim());
        user.setPassword(txtPassword.getText().trim());
        user.setName(txtName.getText().trim());
        user.setPosition(cmbPosition.getSelectionModel().getSelectedItem());

        boolean isResult = UserService.INSTANCE.addOrEditUser(user);

        if (isResult) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Пользователь сохранен!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Пользователь не сохранен!");
            alert.show();
        }



    }

    @FXML
    void initialize() {
        cmbPosition.setItems(FXCollections.observableList(PositionService.INSTANCE.findAllPositions()));
    }

    private User user;


}
