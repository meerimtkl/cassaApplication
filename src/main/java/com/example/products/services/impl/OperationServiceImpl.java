package com.example.products.services.impl;

import com.example.products.db.OperationDB;
import com.example.products.models.Operation;
import com.example.products.models.OperationProducts;
import com.example.products.services.OperationProductService;
import com.example.products.services.OperationService;

import java.time.LocalDateTime;
import java.util.List;

public class OperationServiceImpl implements OperationService {

    @Override
    public boolean closeAndSaveOperation(double totalPrice, int userId, List<OperationProducts> operationProductsList) {
        Operation operation = OperationDB.INSTANCE.saveOperation(new Operation(LocalDateTime.now(), totalPrice), userId);
        return OperationProductService.INSTANCE.saveOperationProducts(operation.getId(), operationProductsList);

    }
}