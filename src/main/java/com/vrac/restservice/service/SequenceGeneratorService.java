package com.vrac.restservice.service;

import com.vrac.restservice.entity.mongoDB.Sequence;
import com.vrac.restservice.repository.SequenceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Data
@Service
public class SequenceGeneratorService {

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public Long getSequenceNumber(String sequenceName) {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        Sequence counter = mongoOperations
                .findAndModify(query, update, options().returnNew(true).upsert(true), Sequence.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1L;
    }
}
