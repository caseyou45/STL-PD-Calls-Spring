package com.stlpd.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "locations")
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String neighborhood;
    private String latitude;
    private String longitude;
    private String location;
    private String locationApprox;
    private String city;
    private String state;
    private String zip;

    public Location(String neighborhood, String latitude, String longitude, String location, String locationApprox,
            String city, String state,
            String zip) {
        this.neighborhood = neighborhood;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.locationApprox = locationApprox;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

}
