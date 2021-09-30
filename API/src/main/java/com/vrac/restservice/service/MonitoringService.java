package com.vrac.restservice.service;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.repository.MonitoringRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.vrac.restservice.entity.monitoring.Monitoring.SEQUENCE_NAME;


@Data
@Service
public class MonitoringService {

    @Autowired
    private MonitoringRepository monitoringRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private MongoOperations mongoOperations;

    public Monitoring insertMonitoring(Monitoring monitoring) {
        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        monitoring.setDate(currentTime);

        // Generate Sequence
        monitoring.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));

        Monitoring insertedMonitoring = monitoringRepository.insert(monitoring);
        return insertedMonitoring;
    }
}
