package com.vrac.restservice.service;

import com.vrac.restservice.entity.strategy.Strategy;
import com.vrac.restservice.repository.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    public List<Strategy> truc() {
        Query query = new Query();
        query.addCriteria(Criteria.);
        List<User> users = mongoTemplate.find(query, User.class);
        strategyRepository.
    }
}
