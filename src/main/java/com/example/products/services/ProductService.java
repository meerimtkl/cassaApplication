package com.example.products.services;

import com.example.products.models.Product;
import com.example.products.services.impl.ProductServiceImpl;

import java.util.List;

public interface ProductService {
    ProductService INSTANCE=new ProductServiceImpl();
    void save(Product product);
    List<Product> getProducts();
    Product findProductByBarcode(String barcode);
    void delete(Integer id);

}
