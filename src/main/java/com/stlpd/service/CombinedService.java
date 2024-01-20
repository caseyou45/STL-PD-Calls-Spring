package com.stlpd.service;

import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.enums.Type;
import com.stlpd.util.DTOSorting;
import com.stlpd.util.NeighborhoodMap;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CombinedService {

    private CallService callService;

    private IncidentService incidentService;

    private final NeighborhoodMap neighborhoodMap;

    DateTimeFormatter dateFormattter = DateTimeFormatter.ofPattern("E, MM/dd/yyyy");
    DateTimeFormatter timeFormattter = DateTimeFormatter.ofPattern("h:mm a");

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

            String neighborhoodString = displayDTO.getNeighborhood();

            if (neighborhoodString != null && !neighborhoodString.isEmpty()) {

                displayDTO.setNeighborhood(neighborhoodMap.getNeighborhood(displayDTO.getNeighborhood()));

            }

            displayDTO.setDisplayDate(displayDTO.getDatetime().format(dateFormattter));

            if (displayDTO.getType().equals(Type.CALL)) {
                displayDTO.setDisplayTime(displayDTO.getDatetime().format(timeFormattter));
            }

        }

        DTOSorting.sort(query.getSortMethod(), query.getSortDirection(), items);

        return items;

    }
}
