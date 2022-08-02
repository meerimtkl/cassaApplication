package com.example.products.db;

import com.example.products.db.impl.PositionDBImpl;
import com.example.products.models.Position;

import java.util.List;

public interface PositionDB {
    PositionDB INSTANCE=new PositionDBImpl();
    void insert(Position position);
    void update(Position position);
    List<Position> findAllPositions();
}
