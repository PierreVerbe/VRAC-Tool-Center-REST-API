package com.vrac.restservice.controller.strategy;

import com.vrac.restservice.model.entity.strategy.Strategy;
import com.vrac.restservice.repository.StrategyRepository;
import com.vrac.restservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.vrac.restservice.model.entity.strategy.Strategy.SEQUENCE_NAME;

@RestController
public class StrategyController {

    @Autowired
    public StrategyRepository strategyRepository;

    @Autowired
    private SequenceGeneratorService service;

    // CREATE
    @PostMapping(value = "/insert/strategy")
    public String insertStrategy(@RequestBody Strategy strategy) {
        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        strategy.setDate(currentTime);

        // Generate Sequence
        strategy.setId(service.getSequenceNumber(SEQUENCE_NAME));

        Strategy insertedStrategy = strategyRepository.insert(strategy);
        return String.format("Strategy id=%d created", insertedStrategy.getId());
    }

    // READ
    @GetMapping(value = "/find/allStrategies")
    public List<Strategy> getAllStrategies() {
        return strategyRepository.findAll();
    }

    // UPDATE
    @PutMapping("/update/strategy")
    public String updateStrategy(@RequestBody Strategy strategyToUpdate) {
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

    // DELETE
    @DeleteMapping("/delete/strategy")
    public String deleteStrategyById(@RequestBody Long id) {
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
