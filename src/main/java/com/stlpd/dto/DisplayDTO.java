package com.stlpd.dto;

import java.time.LocalDateTime;

import com.stlpd.model.Call;
import com.stlpd.model.Incident;
import com.stlpd.model.Location;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class DisplayDTO {
    private String date;
    private String offense;
    private String neighborhood;
    private String latitude;
    private String longitude;
    private String location;
    private String city;
    private String state;
    private String zip;
    private LocalDateTime datetime;

    public DisplayDTO(Incident incident) {
        this.date = incident.getDateInc();
        this.offense = incident.getOffense();
        this.neighborhood = incident.getNeighborhood();
        this.latitude = incident.getLatitude();
        this.longitude = incident.getLongitude();
        this.location = incident.getIncidentLocation();
        this.datetime = incident.getConvertedDate();
    }

    public DisplayDTO(Call call) {

        this.datetime = call.getDatetime();
        this.location = call.getLocation();
        this.offense = call.getType();

        if (call.getLocationEntity() != null) {
            this.neighborhood = call.getLocationEntity().getNeighborhood();
            this.latitude = call.getLocationEntity().getLatitude();
            this.longitude = call.getLocationEntity().getLongitude();
        }

    }

    public void ifLocationAddInfoToDTO(Location location) {
        this.neighborhood = location.getNeighborhood();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

    }

}
