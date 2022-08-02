package com.example.products.services;


import com.example.products.models.Position;
import com.example.products.services.impl.PositionServiceImpl;

import java.util.List;

public interface PositionService {

    PositionService INSTANCE = new PositionServiceImpl();

    void save(Position position);

    Position findPositionById(int positionId);

    List<Position> findAllPositions();

}
