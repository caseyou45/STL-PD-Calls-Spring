package com.stlpd.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stlpd.model.Call;
import com.stlpd.model.Location;
import com.stlpd.respository.LocationRepository;

@Service
public class FindClosestLocation {

    private LocationRepository locationRepository;

    public FindClosestLocation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Location> findClosestMatch(Call call) {
        return locationRepository.findFirstByLocationApprox(call.getModifiedLocation());
    }

    public Optional<Location> findClosestMatch(String location) {
        return locationRepository.findFirstByLocationApprox(location);
    }
}