package com.example.products.services;

import com.example.products.models.User;
import com.example.products.services.impl.UserServiceImpl;

import java.util.List;

public interface UserService {
    UserService INSTANCE = new UserServiceImpl();

     boolean addOrEditUser(User user);

     List<User> findAllUsers();

     User findOneUserById(int userId);

     boolean deleteUser(Integer id);

    User findUserByLoginAndPassword(String login, String password);

}
