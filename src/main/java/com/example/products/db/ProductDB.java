package com.example.products.db;

import com.example.products.db.impl.ProductDBImpl;
import com.example.products.models.Product;

import java.util.List;

public interface ProductDB {
    ProductDB INSTANCE = new ProductDBImpl();
    void insert(Product product);
    List<Product> selectProducts();
    void update(Product product);
    Product findProductByBarcode(String barcode);

}
