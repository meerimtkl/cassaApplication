package com.example.products.db;

import com.example.products.db.impl.ConnectionDBImpl;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDB {

    ConnectionDB INSTANCE=new ConnectionDBImpl() ;
    Connection getConnection() throws SQLException;
    void close(Connection connection);


}
