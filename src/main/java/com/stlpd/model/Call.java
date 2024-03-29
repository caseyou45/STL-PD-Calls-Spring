package com.stlpd.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datetime;
    private String eventID;
    private String location;
    private String modifiedLocation;
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location locationEntity;

    public Call(LocalDateTime datetime, String eventID, String location, String modifiedLocation, String type,
            Location locationEntity) {
        this.datetime = datetime;
        this.eventID = eventID;
        this.location = location;
        this.modifiedLocation = modifiedLocation;
        this.type = type;
        this.locationEntity = locationEntity;
    }
}
