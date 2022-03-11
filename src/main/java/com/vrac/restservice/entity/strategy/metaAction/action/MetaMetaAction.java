package com.vrac.restservice.entity.strategy.metaAction.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaMetaAction {

    private String tag;
    private Parameters parameters;
    private List<Transition> transitions;

}
