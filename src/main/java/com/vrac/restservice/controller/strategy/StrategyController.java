package com.vrac.restservice.controller.strategy;

import com.vrac.restservice.entity.strategy.Strategy;
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
    public String insertStrategy(@RequestBody Strategy strategy) {
        Strategy result = strategyService.insertStrategy(strategy);
        return String.format("Strategy id=%d created", result.getId());
    }

    // READ
    @GetMapping(value = "/find/allStrategies")
    public List<Strategy> getAllStrategies() {
        return strategyService.findAllStrategies();
    }

    @GetMapping(value = "/find/strategy")
    public Strategy getStrategyById(@RequestBody Strategy strategy) {
        return strategyService.findStrategyWithId(strategy.getId());
    }

    // UPDATE
    @PutMapping("/update/strategy")
    public String updateStrategy(@RequestBody Strategy strategyToUpdate) {
        return strategyService.updateStrategy(strategyToUpdate);
    }

    // DELETE
    @DeleteMapping("/delete/strategy")
    public String deleteStrategyById(@RequestBody Strategy strategy) {
        return strategyService.deleteStrategy(strategy.getId());
    }

}
