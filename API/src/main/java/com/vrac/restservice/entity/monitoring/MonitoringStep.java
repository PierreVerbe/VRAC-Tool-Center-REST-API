package com.vrac.restservice.entity.monitoring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringStep {

    @Id
    private long id;
    private String name;
    private double x;
    private double y;

}
