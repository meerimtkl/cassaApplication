package com.example.products.db.impl;

import com.example.products.db.ConnectionDB;
import com.example.products.db.ProductDB;
import com.example.products.models.Category;
import com.example.products.models.Product;
import com.example.products.models.Unit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBImpl implements ProductDB {



    public static void delete(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from products where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }
         catch (SQLException e){
                e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }


    public void insert(Product product){
        Connection connection=null;

        try {
             connection=ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps=connection.prepareStatement("insert into products(name, price, barcode, discount, amount, categories_id, units_id)" + "values(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setString(3,product.getBarcode());
            ps.setInt(4,product.getDiscount());
            ps.setDouble(5,product.getAmount());
            ps.setInt(6,product.getCategory().getId());
            ps.setInt(7,product.getUnit().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.INSTANCE.close(connection);
        }

    }

    public List<Product> selectProducts() {

        Connection connection=null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("select p.id, p.name, categories_id, price, units_id, barcode, amount, discount, c.name, u.name from products p\n" +
                    "join categories c\n" +
                    "on p.categories_id = c.id\n" +
                    "join units u\n" +
                    "on p.units_id = u.id");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(4));
                product.setBarcode(resultSet.getString(6));
                product.setAmount(resultSet.getDouble(7));
                product.setDiscount(resultSet.getInt(8));

                Category category = new Category();
                category.setId(resultSet.getInt(3));
                category.setName(resultSet.getString(9));

                product.setCategory(category);

                Unit unit = new Unit();
                unit.setId(resultSet.getInt(5));
                unit.setName(resultSet.getString(10));

                product.setUnit(unit);


                products.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
            return products;
        }
    }
    public void update(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "update products set name = ?, price = ?, barcode= ?, discount= ?, amount= ?, categories_id= ?, units_id= ? where id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getBarcode());
            ps.setInt(4, product.getDiscount());
            ps.setDouble(5, product.getAmount());
            ps.setInt(6, product.getCategory().getId());
            ps.setInt(7, product.getUnit().getId());
            ps.setInt(8, product.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public Product findProductByBarcode(String barcode) {
        Connection connection=null;
        Product product=new Product();

        try{
            connection=ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps=connection.prepareStatement("select p.id, p.name, p.categories_id,p.price,p.units_id, p.barcode,\n" +
                    "p.amount,p.discount, c.name, u.name\n" +
                    "from products p join categories c  \n" +
                    "on p.categories_id = c.id \n" +
                    "join units u\n" +
                    "on p.units_id = u.id\n" +
                    " where barcode = ?");
            ps.setString(1,barcode);
            ResultSet resultSet=ps.executeQuery();

            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setBarcode(barcode);
            product.setDiscount(resultSet.getInt(8));
            product.setAmount(resultSet.getDouble(7));
            product.setPrice(resultSet.getDouble(4));

            Category category=new Category();
            category.setId(resultSet.getInt(3));
            category.setName(resultSet.getString(9));

            product.setCategory(category);
            Unit unit=new Unit();
            unit.setId(resultSet.getInt(5));
            unit.setName(resultSet.getString(10));
            product.setUnit(unit);



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
            return product;
        }
    }
}

