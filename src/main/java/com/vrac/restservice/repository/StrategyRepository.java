package com.vrac.restservice.repository;

import com.vrac.restservice.entity.strategy.GlobalStrategy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends MongoRepository<GlobalStrategy, Long> {
}
