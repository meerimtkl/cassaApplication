package com.example.products.services.impl;

import com.example.products.db.PositionDB;
import com.example.products.models.Position;
import com.example.products.services.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {


    public void save(Position position) {
        if (position.getId() == null)


            PositionDB.INSTANCE.insert(position);

        else
            PositionDB.INSTANCE.update(position);
    }

    public Position findPositionById(int positionId) {
        return null;
    }


    public List<Position> findAllPositions() {
        return PositionDB.INSTANCE.findAllPositions();

    }


}