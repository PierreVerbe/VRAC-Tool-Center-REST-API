package com.vrac.restservice.controller.monitoring;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.repository.MonitoringRepository;
import com.vrac.restservice.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    @Autowired
    public MonitoringRepository monitoringRepository;

    @Autowired
    private MonitoringService monitoringService;

    // CREATE
    @PostMapping(value = "/insert/monitoring")
    public String insertStrategy(@RequestBody Monitoring monitoring) {
        Monitoring result = monitoringService.insertMonitoring(monitoring);

        return String.format("Monitoring id=%d created", result.getId());
    }

}
