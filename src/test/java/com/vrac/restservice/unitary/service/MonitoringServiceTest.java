package com.vrac.restservice.unitary.service;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.error.exception.ResourceNotFoundException;
import com.vrac.restservice.service.MonitoringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.vrac.restservice.entity.MongoCollection.MONITORING;
import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void findAllMonitoringsTest() {
        // Given
        for (int i = 1; i <= 3; i++) {
            Monitoring monitoring = new Monitoring();
            monitoring.setName("myName" + i);
            monitoring.setDescription("myDescription" + i);
            monitoring.setVersion("v1.0");
            monitoringService.insertMonitoring(monitoring);
        }

        // When
        List<Monitoring> result = monitoringService.findAllMonitorings();

        // Then
        for (int i = 1; i <= 3; i++) {
            Monitoring resulti = result.get(i - 1);
            assertEquals(resulti.getId(), new Long(i));
            assertEquals(resulti.getName(), "myName" + i);
            assertEquals(resulti.getDescription(), "myDescription" + i);
            assertNull(resulti.getMonitoring());
            assertEquals(resulti.getVersion(), "v1.0");
        }
    }

    @Test
    public void findNoneExistingAllMonitoringsTest() {
        // Given

        // When
        List<Monitoring> result = monitoringService.findAllMonitorings();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void findMonitoringWithIdTest() {
        // Given
        Long id = 1L;
        Monitoring monitoring = new Monitoring();
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");

        // When
        monitoringService.insertMonitoring(monitoring);
        Monitoring result = monitoringService.findMonitoringWithId(id);

        // Then
        assertEquals(result.getId(), id);
        assertEquals(result.getName(), "myName");
        assertEquals(result.getDescription(), "myDescription");
        assertNull(result.getMonitoring());
        assertEquals(result.getVersion(), "v1.0");
    }

    @Test
    public void findNoneExistingMonitoringWithIdTest() {
        // Given
        Long id = 1L;

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> monitoringService.findMonitoringWithId(id));

        // Then
        assertEquals("Monitoring not found with id=1", exception.getMessage());
    }

    @Test
    public void updateExitingMonitoringTest() {
        // Given
        Monitoring monitoring = new Monitoring();
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");
        monitoringService.insertMonitoring(monitoring);

        // When
        monitoring.setName("myNameUpdated");
        monitoring.setDescription("myDescriptionUpdated");
        monitoring.setVersion("v1.1");
        String result = monitoringService.updateMonitoring(monitoring);

        // Then
        assertEquals("Monitoring id=1 updated", result);
    }

    @Test
    public void updateNoneExitingMonitoringTest() {
        // Given
        Monitoring monitoring = new Monitoring();
        monitoring.setId(999L);

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> monitoringService.updateMonitoring(monitoring));

        // Then
        assertEquals("Monitoring not found with id=999", exception.getMessage());
    }

    @Test
    public void deleteExistingMonitoringTest() {
        // Given
        Long id = 1L;
        Monitoring monitoring = new Monitoring();
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");
        monitoringService.insertMonitoring(monitoring);

        // When
        String result = monitoringService.deleteMonitoring(id);

        // Then
        assertEquals("Monitoring id=1 deleted", result);
    }

    @Test
    public void deleteNoneExistingMonitoringTest() {
        // Given
        Long id = 999L;

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> monitoringService.deleteMonitoring(id));

        // Then
        assertEquals("Monitoring not found with id=999", exception.getMessage());
    }

}
