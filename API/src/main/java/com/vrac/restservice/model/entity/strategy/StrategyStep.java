package com.vrac.restservice.model.entity.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyStep {

    @Id
    private long id;
    private String name;
    private double x;
    private double y;

}
