package com.vrac.restservice.entity.strategy.strategy;

import com.vrac.restservice.entity.strategy.strategy.action.StrategyAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Strategy {

    private String name;
    private List<StrategyAction> actions;

}
