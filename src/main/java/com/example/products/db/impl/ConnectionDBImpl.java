package com.example.products.db.impl;

import com.example.products.db.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBImpl implements ConnectionDB {
    public  Connection getConnection() throws SQLException{
        Connection connection= DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
        System.out.println("connected");
        return connection;
    }
    public  void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
