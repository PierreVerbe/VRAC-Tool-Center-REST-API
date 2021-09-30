package com.vrac.restservice.unitary.service;

import com.vrac.restservice.service.VracService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VracServiceTest {

    @Autowired
    private VracService vracService;

    @Test
    public void generateIntBetween0and0Test() {
        // Given
        int min = 0;
        int max = 0;

        // When
        int result = vracService.generateInt(min, max);

        // Then
        assertEquals(result, 0);
    }

    @Test
    public void generateIntBetween0and15Test() {
        // Given
        int min = 0;
        int max = 15;
        Collection<Integer> testedCollection = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        );

        // When
        int result = vracService.generateInt(min, max);

        // Then
        assertTrue(testedCollection.contains(result));
    }

}
