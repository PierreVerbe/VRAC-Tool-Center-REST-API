package com.vrac.restservice.entity.strategy.metaAction.action.parameters;

import com.vrac.restservice.entity.strategy.metaAction.action.Parameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomingParameters extends Parameters {

    private Boolean forward;
    private Boolean axis;
    private int offset;

}
