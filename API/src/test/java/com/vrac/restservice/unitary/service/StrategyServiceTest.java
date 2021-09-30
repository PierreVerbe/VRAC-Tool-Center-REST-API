package com.vrac.restservice.unitary.service;

import com.vrac.restservice.entity.strategy.Strategy;
import com.vrac.restservice.service.StrategyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;

import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;
import static com.vrac.restservice.entity.MongoCollection.STRATEGY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class StrategyServiceTest {

    @Autowired
    private StrategyService strategyService;

    @BeforeEach
    void beforeEach() {
        strategyService.getMongoOperations().remove(new Query(), SEQUENCE);
        strategyService.getMongoOperations().remove(new Query(), STRATEGY);
    }

    @Test
    public void insertStrategyTest() {
        // Given
        Strategy strategy = new Strategy();
        strategy.setName("myName");
        strategy.setDescription("myDescription");
        strategy.setSender("mySender");
        strategy.setVersion("v1.0");

        // When
        Strategy result = strategyService.insertStrategy(strategy);

        // Then
        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "myName");
        assertEquals(result.getDescription(), "myDescription");
        assertEquals(result.getSender(), "mySender");
        assertNull(result.getStrategy());
        assertEquals(result.getVersion(), "v1.0");
    }

    @Test
    public void findAllStrategyTest() {

    }

    @Test
    public void findStrategyWithIdTest() {
        // Given
        Strategy strategy = new Strategy();
        strategy.setName("myName");
        strategy.setDescription("myDescription");
        strategy.setSender("mySender");
        strategy.setVersion("v1.0");

        // When
        strategyService.insertStrategy(strategy);
        Strategy result = strategyService.findStrategyWithId(1L);

        // Then
        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "myName");
        assertEquals(result.getDescription(), "myDescription");
        assertEquals(result.getSender(), "mySender");
        assertNull(result.getStrategy());
        assertEquals(result.getVersion(), "v1.0");
    }

    @Test
    public void updateExitingStrategyTest() {

    }

    @Test
    public void updateNoneExitingStrategyTest() {

    }

    @Test
    public void deleteExistingStrategyTest() {

    }

    @Test
    public void deleteNoneExistingStrategyTest() {
        // Given
        Long id = 999L;

        // When
        String result = strategyService.deleteStrategy(id);

        // Then
        assertEquals(result, "Strategy id=999 does not exist");
    }

}
