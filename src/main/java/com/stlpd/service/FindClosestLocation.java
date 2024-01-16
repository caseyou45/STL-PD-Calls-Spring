package com.stlpd.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stlpd.model.Call;
import com.stlpd.model.Incident;
import com.stlpd.respository.IncidentRepository;

@Service
public class FindClosestLocation {

    private IncidentRepository incidentRepository;

    public FindClosestLocation(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public void findClosestMatch(Call call) {

        Optional<Incident> matchingIncident = incidentRepository
                .findFirstByIncidentLocationLike(call.getModifiedLocation());

        if (matchingIncident.isPresent()) {
            Incident i = matchingIncident.get();
            call.setNeighborhood(i.getNeighborhood());
        }

    }
}