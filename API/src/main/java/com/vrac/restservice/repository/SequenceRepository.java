package com.vrac.restservice.repository;

import com.vrac.restservice.entity.mongoDB.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String> {
}
