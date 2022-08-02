package com.example.products.db.impl;

import com.example.products.db.ConnectionDB;
import com.example.products.db.UserDB;
import com.example.products.models.Position;
import com.example.products.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBImpl implements UserDB {

/*
    public static List<User> findAll() {


    }*/

    public boolean insert(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            //  Connection connection=DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name,login,password,position_id) values(?,?,?,?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getPosition().getId());

            int countUpRows = preparedStatement.executeUpdate();
            ConnectionDB.INSTANCE.close(connection);
            System.out.println(countUpRows);
            return countUpRows >= 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }

        return false;
    }

    public boolean update(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            //  Connection connection=DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name=?, login=?, password=?, position_id=? where id=?");
            preparedStatement.setString(1, user.getName());
            //preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getPosition().getId());
            preparedStatement.setInt(5, user.getId());
            int countUpRows = preparedStatement.executeUpdate();
            ConnectionDB.INSTANCE.close(connection);
            return countUpRows >= 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return false;
    }

    public List<User> getAllUsersFromDb() {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "Select us.id, us.name , us.login,us.password, p.id, p.name from users us join position p on us.position_id=p.id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setPassword(rs.getString(4));
                int positionId = rs.getInt(5);
                String positionName = rs.getString(6);
                user.setPosition(new Position(positionId, positionName));
                userList.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return userList;
    }


    public User getUserById(int id) {
        Connection connection = null;
        User user = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "select us.id,us.name,us.login,us.password,p.id,p.name from users us join position p" +
                    " on us.position_id=p.id where us.id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setPassword(rs.getString(4));
            int positionId = rs.getInt(5);
            String posName = rs.getString(6);
            user.setPosition(new Position(positionId, posName));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return user;

    }


    public boolean deleteUserById(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();

            String query = "delete from users where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int countDeletedRows = ps.executeUpdate();
            return countDeletedRows >= 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return false;


    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        Connection connection = null;
        User userFromDb = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "select * from users where login = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userFromDb = new User();
                userFromDb.setId(rs.getInt("id"));
                userFromDb.setName(rs.getString("name"));
                userFromDb.setLogin(rs.getString("login"));
                userFromDb.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return userFromDb;
    }
}
