package com.vrac.restservice.repository;

import com.vrac.restservice.entity.monitoring.Monitoring;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringRepository extends MongoRepository<Monitoring, Long> {
}
