package com.vrac.restservice.unitary.service;

import com.vrac.restservice.entity.strategy.Strategy;
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
        Long id = 1L;
        Strategy strategy = new Strategy();
        strategy.setName("myName");
        strategy.setDescription("myDescription");
        strategy.setSender("mySender");
        strategy.setVersion("v1.0");

        // When
        strategyService.insertStrategy(strategy);
        Strategy result = strategyService.findStrategyWithId(id);

        // Then
        assertEquals(result.getId(), id);
        assertEquals(result.getName(), "myName");
        assertEquals(result.getDescription(), "myDescription");
        assertEquals(result.getSender(), "mySender");
        assertNull(result.getStrategy());
        assertEquals(result.getVersion(), "v1.0");
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
        Strategy strategy = new Strategy();
        strategy.setId(999L);

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> strategyService.updateStrategy(strategy));

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
