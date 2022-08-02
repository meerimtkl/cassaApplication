package com.example.products.db.impl;

import com.example.products.db.ConnectionDB;
import com.example.products.db.PositionDB;
import com.example.products.models.Position;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDBImpl implements PositionDB {


    public void insert(Position position) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            //  Connection connection=DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into position (name) values(?)");
            preparedStatement.setString(1, position.getName());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public void update(Position position) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            //  Connection connection=DriverManager.getConnection("jdbc:sqlite:D:/javabootcamp2022/Databases/products_db.db");
            PreparedStatement preparedStatement = connection.prepareStatement("update position set name=?  where id=?");
            preparedStatement.setString(1, position.getName());
            preparedStatement.setInt(5, position.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }


    public List<Position> findAllPositions() {
        Connection connection = null;
        List<Position> positionList = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "Select * from position";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position item = new Position();
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                positionList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return positionList;
    }
    /*
    public List<Position> getPositions() {

            List<Position>positions=new ArrayList<>();
            Connection connection=null;


            try{
                connection=ConnectionDB.getConnection();
                PreparedStatement ps=connection.prepareStatement("Select * from position");

                ResultSet resultSet=ps.executeQuery();

                while(resultSet.next()){
                    Position position=new Position();
                    position.setId(resultSet.getInt(1));
                    position.setName(resultSet.getString(2));
                    positions.add(position);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                ConnectionDB.close(connection);
                return positions;
            }
        }*/
}

