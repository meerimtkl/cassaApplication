package com.example.products.db;

import com.example.products.db.impl.UserDBImpl;
import com.example.products.models.User;

import java.util.List;

public interface UserDB {
    UserDB INSTANCE=new UserDBImpl();
    boolean insert(User user);
    boolean update(User user);
    List<User> getAllUsersFromDb();
    User getUserById(int id);
    boolean deleteUserById(Integer id);


    User findUserByLoginAndPassword(String login, String password);
}
