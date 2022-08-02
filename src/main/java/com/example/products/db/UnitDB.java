package com.example.products.db;

import com.example.products.db.impl.UnitDBImpl;
import com.example.products.models.Unit;

import java.util.List;

public interface UnitDB {
    UnitDB INSTANCE=new UnitDBImpl();
    List<Unit> getUnits();
}
