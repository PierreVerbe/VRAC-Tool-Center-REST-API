package com.vrac.restservice.unitary.controller;

import com.vrac.restservice.controller.VracController;
import com.vrac.restservice.service.VracService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.test.web.servlet.MockMvc;

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
    }

}
