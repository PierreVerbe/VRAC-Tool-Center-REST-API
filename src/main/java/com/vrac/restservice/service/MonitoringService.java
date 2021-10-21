package com.vrac.restservice.service;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.error.exception.ResourceNotFoundException;
import com.vrac.restservice.repository.MonitoringRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Monitoring> findAllMonitorings() {
        return monitoringRepository.findAll();
    }

    public Monitoring findMonitoringWithId(Long id) {
        Optional<Monitoring> monitoring = monitoringRepository.findById(id);
        return monitoring.orElseThrow(() -> new ResourceNotFoundException("Monitoring not found with id=" + id));
    }

    public String updateMonitoring(Monitoring monitoringToUpdate) {
        Long id = monitoringToUpdate.getId();
        Monitoring monitoring = monitoringRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoring not found with id=" + id));

        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();

        monitoring.setName(monitoringToUpdate.getName());
        monitoring.setDate(currentTime);
        monitoring.setDescription(monitoringToUpdate.getDescription());
        monitoring.setMonitoring(monitoringToUpdate.getMonitoring());
        monitoring.setVersion(monitoringToUpdate.getVersion());
        monitoringRepository.deleteById(id);
        monitoringRepository.insert(monitoring);
        return String.format("Monitoring id=%d updated", id);
    }

    public String deleteMonitoring(Long id) {
        Monitoring monitoring = monitoringRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoring not found with id=" + id));

        monitoringRepository.deleteById(id);
        return String.format("Monitoring id=%d deleted", id);
    }

}
