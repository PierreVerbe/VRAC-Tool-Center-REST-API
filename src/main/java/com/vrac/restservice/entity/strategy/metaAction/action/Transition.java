package com.vrac.restservice.entity.strategy.metaAction.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transition {

    private String destination;
    private String type;

}
