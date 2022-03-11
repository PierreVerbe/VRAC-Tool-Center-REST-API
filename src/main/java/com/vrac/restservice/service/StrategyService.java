package com.vrac.restservice.service;

import com.vrac.restservice.entity.strategy.GlobalStrategy;
import com.vrac.restservice.error.exception.ResourceNotFoundException;
import com.vrac.restservice.repository.StrategyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.vrac.restservice.entity.strategy.GlobalStrategy.SEQUENCE_NAME;

@Data
@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private MongoOperations mongoOperations;

    public GlobalStrategy insertStrategy(GlobalStrategy globalStrategy) {
        // Generate LocalDateTime
        if (globalStrategy.getDate() == null) {
            LocalDateTime currentTime = LocalDateTime.now();
            globalStrategy.setDate(currentTime);
        }

        // Generate Sequence
        globalStrategy.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));

        return strategyRepository.insert(globalStrategy);
    }

    public List<GlobalStrategy> findAllStrategies() {
        return strategyRepository.findAll();
    }

    public GlobalStrategy findStrategyWithId(Long id) {
        Optional<GlobalStrategy> strategy = strategyRepository.findById(id);
        return strategy.orElseThrow(() -> new ResourceNotFoundException("Strategy not found with id=" + id));
    }

    public String updateStrategy(GlobalStrategy globalStrategyToUpdate) {
        Long id = globalStrategyToUpdate.getId();
        GlobalStrategy globalStrategy = strategyRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Strategy not found with id=" + id));

        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        globalStrategy.setDate(currentTime);
        globalStrategy.setStrategy(globalStrategyToUpdate.getStrategy());
        globalStrategy.setMetaActions(globalStrategyToUpdate.getMetaActions());

        strategyRepository.deleteById(id);
        strategyRepository.insert(globalStrategy);
        return String.format("Strategy id=%d updated", id);
    }

    public String deleteStrategy(Long id) {
        GlobalStrategy globalStrategy = strategyRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Strategy not found with id=" + id));

        strategyRepository.deleteById(id);
        return String.format("Strategy id=%d deleted", id);
    }

}
