package com.example.products.db;

import com.example.products.db.impl.OperationProductDBImpl;
import com.example.products.models.OperationProducts;

import java.util.List;

public interface OperationProductDB {

    OperationProductDB INSTANCE = new OperationProductDBImpl();
    boolean saveOperationProduct(int operationId, List<OperationProducts> operationProductsList);
}
