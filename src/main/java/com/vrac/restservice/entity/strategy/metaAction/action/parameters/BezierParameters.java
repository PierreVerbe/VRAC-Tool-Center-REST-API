package com.vrac.restservice.entity.strategy.metaAction.action.parameters;

import com.vrac.restservice.entity.strategy.metaAction.action.Parameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BezierParameters extends Parameters {

    private Boolean chained;
    private int radius;
    private int x;
    private int y;
    private String speed;

}
