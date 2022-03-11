package com.vrac.restservice.integration;

import com.vrac.restservice.entity.strategy.GlobalStrategy;
import com.vrac.restservice.service.StrategyService;
import com.vrac.restservice.util.UtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;
import static com.vrac.restservice.entity.MongoCollection.STRATEGY;

@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceGlobalStrategyTest implements UtilTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StrategyService strategyService;

    @BeforeEach
    void beforeEach() {
        strategyService.getMongoOperations().remove(new Query(), SEQUENCE);
        strategyService.getMongoOperations().remove(new Query(), STRATEGY);
    }

    @Test
    void insertStrategy() throws Exception {
        // Given
        String path = "/insert/strategy";
        String content = "{\"id\":null," +
                "\"name\":\"myName\"," +
                "\"date\":null," +
                "\"description\":\"myDescription\"," +
                "\"strategy\":null," +
                "\"version\":\"v1.0\"}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Strategy id=1 created"));
    }

    @Test
    void insertStrategyWithMapper() throws Exception {
        // Given
        String path = "/insert/strategy";
        GlobalStrategy globalStrategy = new GlobalStrategy();
        //globalStrategy.setName("myName");
        String content = UtilTest.ObjectToString(globalStrategy);

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Strategy id=1 created"));
    }

    @Test
    void getAllStrategies() throws Exception {
        // Given
        String path = "/find/allStrategies";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void getStrategyById() throws Exception {
        // Given
        String path = "/find/strategy";
        String content = "{\"id\":1}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateStrategy() throws Exception {
        // Given
        String path = "/update/strategy";
        String content = "{\"id\":1," +
                "\"name\":\"myName\"," +
                "\"date\":null," +
                "\"description\":\"myDescription\"," +
                "\"monitoring\":null," +
                "\"version\":\"v1.1\"}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteStrategyById() throws Exception {
        // Given
        String path = "/delete/strategy";
        String content = "{\"id\":1}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
