package com.vrac.restservice.unitary.controller;

import com.vrac.restservice.controller.monitoring.MonitoringController;
import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.repository.MonitoringRepository;
import com.vrac.restservice.service.MonitoringService;
import com.vrac.restservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@WebMvcTest(controllers = MonitoringController.class)
public class MonitoringControllerTest implements UtilTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonitoringService monitoringService;

    @MockBean
    private MonitoringRepository monitoringRepository;

    @Test
    public void insertMonitoringTest() throws Exception {
        // Given
        String path = "/insert/monitoring";
        Monitoring monitoring = new Monitoring();
        monitoring.setId(1L);
        monitoring.setName("myName");
        monitoring.setDescription("myDescription");
        monitoring.setVersion("v1.0");
        String content = UtilTest.ObjectToString(monitoring);

        // When
        Mockito.when(monitoringService.insertMonitoring(monitoring)).thenReturn(monitoring);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("Monitoring id=1 created"));
    }

    @Test
    public void getAllMonitorings() throws Exception {
        // Given
        String path = "/find/allMonitorings";

        // When
        Mockito.when(monitoringService.findAllMonitorings()).thenReturn(new ArrayList<>());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void getMonitoringById() throws Exception {
        // Given
        String path = "/find/monitoring";
        String content = "{\"id\":1}";

        // When
        Mockito.when(monitoringService.findMonitoringWithId(1L)).thenReturn(new Monitoring());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"name\":null,\"date\":null,\"description\":null,\"monitoring\":null,\"version\":null}"));
    }

    @Test
    public void updateMonitoring() throws Exception {
        // Given
        String path = "/update/monitoring";
        String content = "{\"id\":1}";
        Monitoring monitoring = new Monitoring();
        monitoring.setId(1L);

        // When
        Mockito.when(monitoringService.updateMonitoring(monitoring)).thenReturn("Monitoring id=1 updated");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("Monitoring id=1 updated"));
    }

    @Test
    public void deleteMonitoring() throws Exception {
        // Given
        String path = "/delete/monitoring";
        String content = "{\"id\":1}";
        Monitoring monitoring = new Monitoring();
        monitoring.setId(1L);

        // When
        Mockito.when(monitoringService.deleteMonitoring(1L)).thenReturn("Monitoring id=1 deleted");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(path).content(content).contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("Monitoring id=1 deleted"));
    }

}
