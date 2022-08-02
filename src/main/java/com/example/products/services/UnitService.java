package com.example.products.services;

import com.example.products.models.Unit;
import com.example.products.services.impl.UnitServiceImpl;


import java.util.List;

public interface UnitService {

    UnitService INSTANCE = new UnitServiceImpl();

    List<Unit> getUnits();
}
