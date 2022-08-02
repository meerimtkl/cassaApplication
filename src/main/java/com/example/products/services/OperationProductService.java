package com.example.products.services;

import com.example.products.models.OperationProducts;
import com.example.products.services.impl.OperationProductServiceImpl;

import java.util.List;

public interface OperationProductService {
    OperationProductService INSTANCE= new OperationProductServiceImpl();
    boolean saveOperationProducts(int operationId, List<OperationProducts> operationProductsList);

       }
