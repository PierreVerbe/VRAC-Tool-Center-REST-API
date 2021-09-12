package com.vrac.restservice.repository;

import com.vrac.restservice.model.entity.strategy.Strategy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends MongoRepository<Strategy, Long> {
}
