package com.vrac.restservice.unitary.service;

import com.vrac.restservice.service.VracService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VracServiceTest {

    @MockBean
    private VracService vracService;

    @Test
    public void generateIntTest() {
        // Given
        int min = 0;
        int max = 1;

        // When
        //int result = vracService.generateInt(0, 3);

        // Then
        //assertEquals(result, 1);
    }
}
