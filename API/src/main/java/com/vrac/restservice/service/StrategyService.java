package com.vrac.restservice.service;

import com.vrac.restservice.entity.strategy.Strategy;
import com.vrac.restservice.repository.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.vrac.restservice.entity.strategy.Strategy.SEQUENCE_NAME;

@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

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

    public String updateStrategy(Strategy strategyToUpdate) {
        Long id = strategyToUpdate.getId();
        Optional<Strategy> optionalStrategy = strategyRepository.findById(id);

        if (optionalStrategy.isPresent()) {
            // Generate LocalDateTime
            LocalDateTime currentTime = LocalDateTime.now();

            Strategy strategy = optionalStrategy.get();
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
        else {
            return String.format("Strategy id=%d cannot be updated", id);
        }
    }

    public String deleteStrategy(Long id) {
        Optional<Strategy> strategy = strategyRepository.findById(id);

        if (strategy.isPresent()) {
            strategyRepository.deleteById(id);
            return String.format("Strategy id=%d deleted", id);
        }
        else {
            return String.format("Strategy id=%d does not exist", id);
        }
    }

}
