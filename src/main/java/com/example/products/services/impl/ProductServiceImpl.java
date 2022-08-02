package com.example.products.services.impl;

import com.example.products.db.impl.ProductDBImpl;
import com.example.products.models.Product;
import com.example.products.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {


    public void save(Product product) {
        if(product.getId()==null)
        ProductDBImpl.INSTANCE.insert(product);
        else
            ProductDBImpl.INSTANCE.update(product);
    }

    public List<Product> getProducts() {return  ProductDBImpl.INSTANCE.selectProducts();}

    public Product findProductByBarcode(String barcode) {
        return ProductDBImpl.INSTANCE.findProductByBarcode(barcode);

    }

    public void delete(Integer id) {
        ProductDBImpl.delete(id);

    }
}
