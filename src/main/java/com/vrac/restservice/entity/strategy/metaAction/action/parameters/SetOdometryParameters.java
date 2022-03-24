package com.vrac.restservice.entity.strategy.metaAction.action.parameters;

import com.vrac.restservice.entity.strategy.metaAction.action.Parameters;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetOdometryParameters extends Parameters {

    private int x;
    private int y;
    private int theta;

}