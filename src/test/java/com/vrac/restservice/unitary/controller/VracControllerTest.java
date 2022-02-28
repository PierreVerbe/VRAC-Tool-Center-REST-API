package com.vrac.restservice.unitary.controller;

import com.vrac.restservice.controller.VracController;
import com.vrac.restservice.service.VracService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = VracController.class)
public class VracControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VracService vracService;

    @Test
    public void getWelcomeTest() throws Exception {
        // Given
        String path = "/welcome";

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("Welcome to the VRAC tool center"));
    }

    @Test
    public void generateRandomIntBetween0and0Test() throws Exception {
        // Given
        int min = 0;
        int max = 0;
        String path = String.format("/random/%d/%d", min, max);

        // When
        Mockito.when(vracService.generateInt(0, 0)).thenReturn(10);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path));

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("[0, 0] => 10"));
    }

}
