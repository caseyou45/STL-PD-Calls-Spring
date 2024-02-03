package com.stlpd.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stlpd.model.Neighborhood;

import com.stlpd.respository.NeighborhoodRepository;

@Service
public class NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;

    }

    public String getNeighborhoodAsNumber(String neighborhood) {
        if (neighborhood != null && !neighborhood.isEmpty()) {
            Optional<Neighborhood> neighborhoodEntity = neighborhoodRepository.findByNeighborhood(neighborhood);
            if (neighborhoodEntity.isPresent()) {
                return neighborhoodEntity.get().getNumber();
            }
        }

        return null;
    }

    public String getNeighborhoodAsDisplayName(String neighborhoodString) {
        if (neighborhoodString != null && !neighborhoodString.isEmpty()) {
            Optional<Neighborhood> neighborhoodEntity = neighborhoodRepository.findByNumber(neighborhoodString);
            if (neighborhoodEntity.isPresent()) {
                return neighborhoodEntity.get().getNeighborhood();
            }
        }
        return null;
    }
}
