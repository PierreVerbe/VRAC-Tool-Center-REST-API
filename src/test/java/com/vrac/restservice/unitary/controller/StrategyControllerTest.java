package com.vrac.restservice.unitary.controller;

import com.vrac.restservice.controller.strategy.StrategyController;
import com.vrac.restservice.service.StrategyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = StrategyController.class)
public class StrategyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StrategyService strategyService;

    @Test
    public void getFindAllStrategiesTest() throws Exception {
        // Given
        String path = "/find/allStrategies";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        //resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        //resultActions.andExpect(MockMvcResultMatchers.content().string("Welcome to the VRAC tool center"));
    }
}
