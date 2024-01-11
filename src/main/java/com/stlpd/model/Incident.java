package com.stlpd.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "incidents")
@ToString
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inciId;
    private String dateInc;
    private String timeInc;
    private String offense;
    private String nibrsGrp;
    private String nibrsCode;
    private String beat;
    private String district;
    private String neighborhood;
    private String latitude;
    private String longitude;
    private String incidentLocation;
    private String locationStreet2Apt;
    private String city;
    private String state;
    private String zip;
    private LocalDateTime convertedDate;

    public Incident(String inciId, String dateInc, String timeInc, String offense, String nibrsGrp, String nibrsCode,
            String beat, String district, String neighborhood, String latitude, String longitude,
            String incidentLocation, String locationStreet2Apt, String city, String state, String zip,
            LocalDateTime convertedDate) {
        this.inciId = inciId;
        this.dateInc = dateInc;
        this.timeInc = timeInc;
        this.offense = offense;
        this.nibrsGrp = nibrsGrp;
        this.nibrsCode = nibrsCode;
        this.beat = beat;
        this.district = district;
        this.neighborhood = neighborhood;
        this.latitude = latitude;
        this.longitude = longitude;
        this.incidentLocation = incidentLocation;
        this.locationStreet2Apt = locationStreet2Apt;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.convertedDate = convertedDate;
    }

}
