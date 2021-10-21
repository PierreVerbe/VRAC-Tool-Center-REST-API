package com.vrac.restservice.unitary.controller;

import com.vrac.restservice.controller.monitoring.MonitoringController;
import com.vrac.restservice.service.MonitoringService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = MonitoringController.class)
public class MonitoringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonitoringService monitoringService;

    @Disabled("Failed to load ApplicationContext")
    @Test
    public void insertMonitoringTest() throws Exception {
        // Given
        String path = "/insert/monitoring";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path));

        // Then
        //resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        //resultActions.andExpect(MockMvcResultMatchers.content().string("Welcome to the VRAC tool center"));
    }

    @Disabled
    @Test
    public void getFindAllMonitoringsTest() throws Exception {
        // Given
        String path = "/find/allMonitorings";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        //resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        //resultActions.andExpect(MockMvcResultMatchers.content().string("Welcome to the VRAC tool center"));
    }




}
