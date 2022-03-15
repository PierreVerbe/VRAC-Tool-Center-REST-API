package com.vrac.restservice.entity.strategy.metaAction.action.parameters;

import com.vrac.restservice.entity.strategy.metaAction.action.Parameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineParameters extends Parameters {

    private int distance;
    private Boolean forward;
    private Boolean chained;
    private String speed;

}
