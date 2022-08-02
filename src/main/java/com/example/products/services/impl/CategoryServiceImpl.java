package com.example.products.services.impl;

import com.example.products.db.CategoryDB;
import com.example.products.db.impl.CategoryDBImpl;
import com.example.products.models.Category;
import com.example.products.services.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
/*
    private CategoryDBImpl categoryDB = CategoryDBImpl.getINSTANCE();
    private static CategoryServiceImpl INSTANCE;

    public static CategoryServiceImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryServiceImpl();
        }
        return INSTANCE;
    }*/
@Override
    public void save(Category category) throws SQLException {
        if (category.getId() == null) {
            CategoryDB.INSTANCE.insert(category);
        } else
            CategoryDB.INSTANCE.update(category);
    }
@Override
    public List<Category> getCategories() {
        return CategoryDB.INSTANCE.findAll();
    }
@Override
    public void delete(Integer id) {
        try {
            CategoryDB.INSTANCE.delete(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
