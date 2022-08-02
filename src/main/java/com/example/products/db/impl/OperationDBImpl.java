package com.example.products.db.impl;

import com.example.products.db.ConnectionDB;
import com.example.products.db.OperationDB;
import com.example.products.models.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationDBImpl implements OperationDB {
    @Override
    public Operation saveOperation(Operation operation, int userId) {
        Connection connection = null;
        Operation operationFromDb = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "insert into operations(oper_date, total, user_id) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, operation.getAddDate().toString());
            ps.setDouble(2, operation.getTotal());
            ps.setInt(3, userId);
            ps.execute();
            operationFromDb = findOperationByTotalPriceAndOperDate(operation.getTotal(), operation.getAddDate().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return operationFromDb;
    }
    public Operation findOperationByTotalPriceAndOperDate(double totalPrice, String operDate) {
        Connection connection = null;
        Operation operation = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "select * from operations where oper_date = ? and total = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, operDate);
            ps.setDouble(2, totalPrice);

            ResultSet rs = ps.executeQuery();
           operation = new Operation();
            operation.setId(rs.getInt(1));
            //System.out.println((rs.getDate(2)));
            //operation.setAddDate(rs.getDate(2));
            operation.setTotal(rs.getInt(3));

            return operation;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return operation;
    }
}
