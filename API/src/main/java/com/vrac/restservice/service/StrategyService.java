package com.vrac.restservice.service;

import com.vrac.restservice.entity.strategy.Strategy;
import com.vrac.restservice.error.exception.ResourceNotFoundException;
import com.vrac.restservice.repository.StrategyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.vrac.restservice.entity.strategy.Strategy.SEQUENCE_NAME;

@Data
@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private MongoOperations mongoOperations;

    public Strategy insertStrategy(Strategy strategy) {
        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        strategy.setDate(currentTime);

        // Generate Sequence
        strategy.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));

        Strategy insertedStrategy = strategyRepository.insert(strategy);
        return insertedStrategy;
    }

    public List<Strategy> findAll() {
        return strategyRepository.findAll();
    }

    public Strategy findStrategyWithId(Long id) {
        Optional<Strategy> strategy = strategyRepository.findById(id);
        return strategy.orElseThrow(() -> new ResourceNotFoundException("Strategy not found with id:" + id));
    }

    public String updateStrategy(Strategy strategyToUpdate) {
        Long id = strategyToUpdate.getId();
        Strategy strategy = strategyRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Strategy not found with id:" + id));

        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();

        strategy.setName(strategyToUpdate.getName());
        strategy.setDate(currentTime);
        strategy.setDescription(strategyToUpdate.getDescription());
        strategy.setSender(strategyToUpdate.getSender());
        strategy.setStrategy(strategyToUpdate.getStrategy());
        strategy.setVersion(strategyToUpdate.getVersion());
        strategyRepository.deleteById(id);
        strategyRepository.insert(strategy);
        return String.format("Strategy id=%d updated", id);
    }

    public String deleteStrategy(Long id) {
        Strategy strategy = strategyRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Strategy not found with id:" + id));

        strategyRepository.deleteById(id);
        return String.format("Strategy id=%d deleted", id);
    }

}
