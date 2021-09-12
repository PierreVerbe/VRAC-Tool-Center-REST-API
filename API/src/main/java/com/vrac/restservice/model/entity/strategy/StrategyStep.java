package com.vrac.restservice.model.entity.strategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class StrategyStep {

    private long id;
    private String name;
    private double x;
    private double y;

    public StrategyStep(long id, String name, double x, int y) {
        super();
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

}
