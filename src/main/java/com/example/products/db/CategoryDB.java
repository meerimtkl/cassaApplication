package com.example.products.db;

import com.example.products.db.impl.CategoryDBImpl;
import com.example.products.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDB {
    CategoryDB INSTANCE = new CategoryDBImpl();

    void insert(Category category) throws SQLException;

    void update(Category category) throws SQLException;

    void delete(Integer id) throws SQLException;

    List<Category> findAll();
}
