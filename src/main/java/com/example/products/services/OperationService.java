package com.example.products.services;

import com.example.products.models.OperationProducts;
import com.example.products.services.impl.OperationServiceImpl;

import java.util.List;

public interface OperationService {
OperationService INSTANCE=new OperationServiceImpl();

boolean closeAndSaveOperation(double totalPrice, int userId, List<OperationProducts> operationProductsList);
}
