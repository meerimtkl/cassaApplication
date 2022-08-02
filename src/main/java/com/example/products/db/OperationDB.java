package com.example.products.db;

import com.example.products.db.impl.OperationDBImpl;
import com.example.products.models.Operation;

public interface OperationDB {
    OperationDB INSTANCE=new OperationDBImpl();
     Operation saveOperation(Operation operation, int userId);
     Operation findOperationByTotalPriceAndOperDate(double totalPrice,String operDate);

}
