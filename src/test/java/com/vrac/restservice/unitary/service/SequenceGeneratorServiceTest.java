package com.vrac.restservice.unitary.service;

import com.vrac.restservice.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;

import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SequenceGeneratorServiceTest {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private

    @BeforeEach
    void beforeEach() {
        sequenceGeneratorService.getMongoOperations().remove(new Query(), SEQUENCE);
    }

    @Test
    public void getNewSequenceNumberTest() {
        // Given
        String sequenceName = "newSequenceName";

        // When
        Long result = sequenceGeneratorService.getSequenceNumber(sequenceName);

        // Then
        assertEquals(result, 1);
    }

    @Test
    public void getSequenceNumber() {
        // Given
        String sequenceName = "newSequenceName";
        sequenceGeneratorService.getSequenceNumber(sequenceName);
        sequenceGeneratorService.getSequenceNumber(sequenceName);

        // When
        Long result = sequenceGeneratorService.getSequenceNumber(sequenceName);

        // Then
        assertEquals(result, 3);
    }

}
