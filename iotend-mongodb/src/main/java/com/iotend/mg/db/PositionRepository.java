package com.iotend.mg.db;

import com.iotend.mg.model.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PositionRepository extends MongoRepository<Position,String> {
    @Override
    Position insert(Position position);
}
