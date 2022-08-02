package com.example.products.services.impl;

import com.example.products.db.OperationProductDB;
import com.example.products.models.OperationProducts;
import com.example.products.services.OperationProductService;

import java.util.List;

public class OperationProductServiceImpl implements OperationProductService {
    @Override
    public boolean saveOperationProducts(int operationId, List<OperationProducts> operationProductsList) {
    boolean isSaveResult= OperationProductDB.INSTANCE.saveOperationProduct(operationId,operationProductsList);
        return isSaveResult;
    }
}
