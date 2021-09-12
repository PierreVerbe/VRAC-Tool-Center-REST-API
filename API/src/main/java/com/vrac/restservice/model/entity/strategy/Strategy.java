package com.vrac.restservice.model.entity.strategy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Strategy")
public class Strategy {

    @Transient
    public static final String SEQUENCE_NAME = "strategy_sequence";

    @Id
    private Long id;
    private String name;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", timezone="Europe/Paris")
    private LocalDateTime date;
    private String description;
    private String sender;
    private List<StrategyStep> strategy;
    private String version;

}
