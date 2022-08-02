package com.example.products.db.impl;

import com.example.products.db.ConnectionDB;
import com.example.products.db.UnitDB;
import com.example.products.models.Unit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitDBImpl implements UnitDB {


    public List<Unit> getUnits(){
        List<Unit>units=new ArrayList<>();
        Connection connection=null;


        try{
             connection= ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps=connection.prepareStatement("Select * from units");

            ResultSet resultSet=ps.executeQuery();

            while(resultSet.next()){
                Unit unit=new Unit();
                unit.setId(resultSet.getInt(1));
                unit.setName(resultSet.getString(2));
                units.add(unit);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
            return units;
        }
    }

}
