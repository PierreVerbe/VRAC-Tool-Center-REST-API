package com.vrac.restservice.integration;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.service.MonitoringService;
import com.vrac.restservice.util.IntegrationTest;
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

import static com.vrac.restservice.entity.MongoCollection.MONITORING;
import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;

@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceMonitoringTest implements IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MonitoringService monitoringService;

    @BeforeEach
    void beforeEach() {
        monitoringService.getMongoOperations().remove(new Query(), SEQUENCE);
        monitoringService.getMongoOperations().remove(new Query(), MONITORING);
    }

    @Test
    void insertMonitoring() throws Exception {
        // Given
        String path = "/insert/monitoring";
        String content = "{\"id\":null," +
                "\"name\":\"myName\"," +
                "\"date\":null," +
                "\"description\":\"myDescription\"," +
                "\"monitoring\":null," +
                "\"version\":\"v1.0\"}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Monitoring id=1 created"));
    }

    @Test
    void insertMonitoringWithMapper() throws Exception {
        // Given
        String path = "/insert/monitoring";
        Monitoring monitoring = new Monitoring();
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");
        String content = IntegrationTest.ObjectToString(monitoring);

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Monitoring id=1 created"));
    }

    @Test
    void getAllMonitorings() throws Exception {
        // Given
        String path = "/find/allMonitorings";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void getMonitoringById() throws Exception {
        // Given
        String path = "/find/monitoring";
        String content = "{\"id\":1}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateMonitoring() throws Exception {
        // Given
        String path = "/update/monitoring";
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
    void deleteMonitoringById() throws Exception {
        // Given
        String path = "/delete/monitoring";
        String content = "{\"id\":1}";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
