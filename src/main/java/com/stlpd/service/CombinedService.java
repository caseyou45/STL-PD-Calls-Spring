package com.stlpd.service;

import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.util.DTOSorting;
import com.stlpd.util.NeighborhoodMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombinedService {

    private CallService callService;

    private IncidentService incidentService;

    private final NeighborhoodMap neighborhoodMap;

    public CombinedService(CallService callService, IncidentService incidentService) {
        this.callService = callService;
        this.incidentService = incidentService;
        this.neighborhoodMap = new NeighborhoodMap();
    }

    public List<DisplayDTO> getDTOs(QueryDTO query)
            throws Exception {

        List<DisplayDTO> items = new ArrayList<>();
        if (query.getSource().equals("calls")) {
            items = callService.getCalls(query);
        } else {
            items = incidentService.getIncidents(query);
        }

        for (DisplayDTO displayDTO : items) {
            String n = displayDTO.getNeighborhood();
            if (n != null && !n.isEmpty()) {
                Integer neighborhoodAsInt = Integer.parseInt(displayDTO.getNeighborhood());
                displayDTO.setNeighborhood(neighborhoodMap.getNeighborhood(neighborhoodAsInt));
            }
        }

        DTOSorting.sort(query.getSortMethod(), query.getSortDirection(), items);

        return items;

    }
}
