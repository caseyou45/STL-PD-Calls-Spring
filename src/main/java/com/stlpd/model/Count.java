package com.stlpd.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "counts")
public class Count {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datetime;
    private String location;
    private String type;
    private Integer count;

    public Count(Long id, LocalDateTime datetime, String location, String type, Integer count) {
        this.id = id;
        this.datetime = datetime;
        this.location = location;
        this.type = type;
        this.count = count;
    }

}
