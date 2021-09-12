package com.vrac.restservice.controller.strategy;

import com.vrac.restservice.model.entity.strategy.Strategy;
import com.vrac.restservice.repository.StrategyRepository;
import com.vrac.restservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.vrac.restservice.model.entity.strategy.Strategy.SEQUENCE_NAME;

@RestController
public class StrategyController {

    @Autowired
    public StrategyRepository strategyRepository;

    @Autowired
    private SequenceGeneratorService service;

    @GetMapping(value = "/findAllStrategies")
    public List<Strategy> getAllStrategies() {
        return strategyRepository.findAll();
    }

    @PostMapping(value = "/insertStrategy")
    public String createStrategy(@RequestBody Strategy strategy) {
        // Generate LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        strategy.setDate(currentTime);

        // Generate Sequence
        strategy.setId(service.getSequenceNumber(SEQUENCE_NAME));

        Strategy insertedStrategy = strategyRepository.insert(strategy);
        return String.format("Strategy id=%d created", insertedStrategy.getId());
    }

}
