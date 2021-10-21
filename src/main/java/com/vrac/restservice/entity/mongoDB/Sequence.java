package com.vrac.restservice.entity.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.vrac.restservice.entity.MongoCollection.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = SEQUENCE)
public class Sequence {

    @Id
    private String id;
    private Long seq;

}
