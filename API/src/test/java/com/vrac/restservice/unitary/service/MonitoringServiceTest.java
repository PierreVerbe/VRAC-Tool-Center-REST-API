package com.vrac.restservice.unitary.service;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.service.MonitoringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;

import static com.vrac.restservice.entity.MongoCollection.MONITORING;
import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MonitoringServiceTest {

    @Autowired
    private MonitoringService monitoringService;

    @BeforeEach
    void beforeEach() {
        monitoringService.getMongoOperations().remove(new Query(), SEQUENCE);
        monitoringService.getMongoOperations().remove(new Query(), MONITORING);
    }

    @Test
    public void insertMonitoringTest() {
        // Given
        Monitoring monitoring = new Monitoring();
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");

        // When
        Monitoring result = monitoringService.insertMonitoring(monitoring);

        // Then
        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "myName");
        assertEquals(result.getDescription(), "myDescription");
        assertNull(result.getMonitoring());
        assertEquals(result.getVersion(), "v1.0");
    }
}
