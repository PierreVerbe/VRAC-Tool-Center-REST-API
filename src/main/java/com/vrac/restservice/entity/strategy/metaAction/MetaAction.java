package com.vrac.restservice.entity.strategy.metaAction;

import com.vrac.restservice.entity.strategy.metaAction.action.MetaMetaAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaAction {

    private String name;
    private List<MetaMetaAction> actions;

}
