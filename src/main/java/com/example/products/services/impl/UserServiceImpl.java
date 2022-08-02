package com.example.products.services.impl;

import com.example.products.db.UserDB;
import com.example.products.models.User;
import com.example.products.services.UserService;
import javafx.fxml.FXML;

import java.util.List;

public class UserServiceImpl implements UserService {



    public boolean addOrEditUser(User user) {
    System.out.println("dobavlena");
        return save(user);

    }

    private boolean save(User user) {
        if (user.getId() == null){
            System.out.println("user id null");
            return UserDB.INSTANCE.insert(user);}
        else
            return UserDB.INSTANCE.update(user);

    }

    public List<User> findAllUsers() {
        return UserDB.INSTANCE.getAllUsersFromDb();
    }/*
    public  User findUserById(){
        
    }*/

    public User findOneUserById(int userId) {
        return UserDB.INSTANCE.getUserById(userId);
    }

    public boolean deleteUser(Integer id) {
        return UserDB.INSTANCE.deleteUserById(id);
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return UserDB.INSTANCE.findUserByLoginAndPassword(login, password);
    }
}
