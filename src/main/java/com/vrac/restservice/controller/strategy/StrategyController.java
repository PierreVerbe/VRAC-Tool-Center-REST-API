package com.vrac.restservice.controller.strategy;

import com.vrac.restservice.entity.strategy.GlobalStrategy;
import com.vrac.restservice.repository.StrategyRepository;
import com.vrac.restservice.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StrategyController {

    @Autowired
    public StrategyRepository strategyRepository;

    @Autowired
    private StrategyService strategyService;

    // CREATE
    @PostMapping(value = "/insert/strategy")
    public String insertStrategy(@RequestBody GlobalStrategy globalStrategy) {
        GlobalStrategy result = strategyService.insertStrategy(globalStrategy);
        return String.format("Strategy id=%d created", result.getId());
    }

    // READ
    @GetMapping(value = "/find/allStrategies")
    public List<GlobalStrategy> getAllStrategies() {
        return strategyService.findAllStrategies();
    }

    @GetMapping(value = "/find/strategy")
    public GlobalStrategy getStrategyById(@RequestBody GlobalStrategy globalStrategy) {
        return strategyService.findStrategyWithId(globalStrategy.getId());
    }

    // UPDATE
    @PutMapping(value = "/update/strategy")
    public String updateStrategy(@RequestBody GlobalStrategy globalStrategyToUpdate) {
        return strategyService.updateStrategy(globalStrategyToUpdate);
    }

    // DELETE
    @DeleteMapping(value = "/delete/strategy")
    public String deleteStrategyById(@RequestBody GlobalStrategy globalStrategy) {
        return strategyService.deleteStrategy(globalStrategy.getId());
    }

}
