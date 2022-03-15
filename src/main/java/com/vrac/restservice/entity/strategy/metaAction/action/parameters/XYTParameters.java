package com.vrac.restservice.entity.strategy.metaAction.action.parameters;

import com.vrac.restservice.entity.strategy.metaAction.action.Parameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XYTParameters extends Parameters {

    private Boolean forward;
    private int x;
    private int y;
    private int theta;
    private String speed;

}
