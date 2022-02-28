package com.vrac.restservice.unitary.service;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.error.exception.ResourceNotFoundException;
import com.vrac.restservice.repository.MonitoringRepository;
import com.vrac.restservice.service.MonitoringService;
import com.vrac.restservice.service.SequenceGeneratorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static com.vrac.restservice.entity.monitoring.Monitoring.SEQUENCE_NAME;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MonitoringServiceTest {

    @Autowired
    private MonitoringService monitoringService;

    @MockBean
    private SequenceGeneratorService sequenceGeneratorService;

    @MockBean
    private MonitoringRepository monitoringRepository;

    @Test
    public void insertMonitoringTest() {
        // Given
        Monitoring monitoring = new Monitoring();
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");

        // When
        Mockito.when(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME)).thenReturn(1L);
        Mockito.when(monitoringRepository.insert(monitoring)).thenReturn(monitoring);

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
        List<Monitoring> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Monitoring monitoring = new Monitoring();
            monitoring.setId((long) i);
            monitoring.setName("myName" + i);
            monitoring.setDescription("myDescription" + i);
            monitoring.setVersion("v1.0");
            list.add(monitoring);
        }

        System.out.println(list);

        // When
        Mockito.when(monitoringRepository.findAll()).thenReturn(list);
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
        Mockito.when(monitoringRepository.findAll()).thenReturn(new ArrayList<>());
        List<Monitoring> result = monitoringService.findAllMonitorings();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void findMonitoringWithIdTest() {
        // Given
        Long id = 1L;
        Monitoring monitoring = new Monitoring();
        monitoring.setId(id);
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");

        // When
        Mockito.when(monitoringRepository.findById(id)).thenReturn(java.util.Optional.of(monitoring));
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
        Long id = 1L;
        Monitoring monitoring = new Monitoring();
        monitoring.setId(id);
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");

        Monitoring updatedMonitoring = new Monitoring();
        updatedMonitoring.setId(id);
        updatedMonitoring.setName("myNameUpdated");
        updatedMonitoring.setDescription("myDescriptionUpdated");
        updatedMonitoring.setVersion("v1.1");

        // When
        Mockito.when(monitoringRepository.findById(id)).thenReturn(java.util.Optional.of(monitoring));
        Mockito.when(monitoringRepository.insert(updatedMonitoring)).thenReturn(updatedMonitoring);
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
        monitoring.setId(id);
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");
        monitoringService.insertMonitoring(monitoring);

        // When
        Mockito.when(monitoringRepository.findById(id)).thenReturn(java.util.Optional.of(monitoring));
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
