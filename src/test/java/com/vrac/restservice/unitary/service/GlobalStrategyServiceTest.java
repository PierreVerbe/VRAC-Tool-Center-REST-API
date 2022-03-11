package com.vrac.restservice.unitary.service;

import com.vrac.restservice.entity.strategy.GlobalStrategy;
import com.vrac.restservice.entity.strategy.strategy.Strategy;
import com.vrac.restservice.error.exception.ResourceNotFoundException;
import com.vrac.restservice.service.StrategyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;

import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;
import static com.vrac.restservice.entity.MongoCollection.STRATEGY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GlobalStrategyServiceTest {

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
        GlobalStrategy globalStrategy = new GlobalStrategy();
        globalStrategy.setStrategy(new Strategy("my strategy", null));

        // When
        GlobalStrategy result = strategyService.insertStrategy(globalStrategy);

        // Then
        assertEquals(result.getId(), 1L);
        assertEquals(result.getStrategy(), new Strategy("my strategy", null));
    }

    @Test
    public void findAllStrategyTest() {

    }

    @Test
    public void findStrategyWithIdTest() {
        // Given
        Long id = 1L;
        GlobalStrategy globalStrategy = new GlobalStrategy();
        globalStrategy.setStrategy(new Strategy("my strategy", null));

        // When
        strategyService.insertStrategy(globalStrategy);
        GlobalStrategy result = strategyService.findStrategyWithId(id);

        // Then
        assertEquals(result.getId(), id);
        assertEquals(result.getStrategy(), new Strategy("my strategy", null));
    }

    @Test
    public void findNoneExistingStrategyWithIdTest() {
        // Given
        Long id = 1L;

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> strategyService.findStrategyWithId(id));

        // Then
        assertEquals("Strategy not found with id=1", exception.getMessage());
    }

    @Test
    public void updateExitingStrategyTest() {

    }

    @Test
    public void updateNoneExitingStrategyTest() {
        // Given
        GlobalStrategy globalStrategy = new GlobalStrategy();
        globalStrategy.setId(999L);

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> strategyService.updateStrategy(globalStrategy));

        // Then
        assertEquals("Strategy not found with id=999", exception.getMessage());
    }

    @Test
    public void deleteExistingStrategyTest() {

    }

    @Test
    public void deleteNoneExistingStrategyTest() {
        // Given
        Long id = 999L;

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> strategyService.deleteStrategy(id));

        // Then
        assertEquals("Strategy not found with id=999", exception.getMessage());
    }

}
