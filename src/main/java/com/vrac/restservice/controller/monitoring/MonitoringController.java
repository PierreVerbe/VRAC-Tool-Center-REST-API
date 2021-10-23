package com.vrac.restservice.controller.monitoring;

import com.vrac.restservice.entity.monitoring.Monitoring;
import com.vrac.restservice.repository.MonitoringRepository;
import com.vrac.restservice.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitoringController {

    @Autowired
    public MonitoringRepository monitoringRepository;

    @Autowired
    private MonitoringService monitoringService;

    // CREATE
    @PostMapping(value = "/insert/monitoring")
    public String insertMonitoring(@RequestBody Monitoring monitoring) {
        Monitoring result = monitoringService.insertMonitoring(monitoring);
        return String.format("Monitoring id=%d created", result.getId());
    }

    // READ
    @GetMapping(value = "/find/allMonitorings")
    public List<Monitoring> getAllMonitorings() {
        return monitoringService.findAllMonitorings();
    }

    @GetMapping(value = "/find/monitoring")
    public Monitoring getMonitoringById(@RequestBody Monitoring monitoring) {
        return monitoringService.findMonitoringWithId(monitoring.getId());
    }

    // UPDATE
    @PutMapping("/update/monitoring")
    public String updateMonitoring(@RequestBody Monitoring monitoringToUpdate) {
        return monitoringService.updateMonitoring(monitoringToUpdate);
    }

    // DELETE
    @DeleteMapping("/delete/monitoring")
    public String deleteMonitoringById(@RequestBody Monitoring monitoring) {
        return monitoringService.deleteMonitoring(monitoring.getId());
    }

}
