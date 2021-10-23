package com.vrac.restservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class RestServiceVracTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getWelcomeTest() throws Exception {
        // Given
        String path = "/welcome";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome to the VRAC tool center"));
    }

    @Test
    void generateRandomInt() throws Exception {
        // Given
        int min = 0;
        int max = 0;
        String path = String.format("/random/%d/%d", min, max);

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[0, 0] => 0"));
    }

}
