package com.example.products.services.impl;

import com.example.products.db.impl.UnitDBImpl;
import com.example.products.models.Unit;
import com.example.products.services.UnitService;

import java.util.List;

public class UnitServiceImpl implements UnitService {

    public List<Unit> getUnits(){
        return UnitDBImpl.INSTANCE.getUnits();
    }
}
