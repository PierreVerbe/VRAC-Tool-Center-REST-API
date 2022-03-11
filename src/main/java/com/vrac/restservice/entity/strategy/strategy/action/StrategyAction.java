package com.vrac.restservice.entity.strategy.strategy.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAction {

    private String tag;
    private List<Transition> transitions;

}
