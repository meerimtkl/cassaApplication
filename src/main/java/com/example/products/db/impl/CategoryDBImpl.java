package com.example.products.db.impl;

import com.example.products.db.CategoryDB;
import com.example.products.db.ConnectionDB;
import com.example.products.models.Category;
import com.example.products.services.impl.CategoryServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDBImpl implements CategoryDB {


    public void insert(Category category) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            //  Connection connection=DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into categories(name) values(?)");
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } finally {
            connection.close();
        }
    }

    public void update(Category category) throws SQLException {
        Connection connection = null;
        try {
            connection =ConnectionDB.INSTANCE.getConnection();
            //  Connection connection=DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
            PreparedStatement preparedStatement = connection.prepareStatement("update categories set name=? where id=?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from categories where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Category> findAll() {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from categories");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
           ConnectionDB.INSTANCE.close(connection);
            return categories;
        }
    }
}


    /*(public void insert(Category category){
        Connection connection= DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db")
    }*/

