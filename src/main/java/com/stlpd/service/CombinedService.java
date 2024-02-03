package com.stlpd.service;

import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.enums.Type;
import com.stlpd.util.DTOSorting;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CombinedService {

    // Service instances
    private CallService callService;
    private IncidentService incidentService;
    private final NeighborhoodService neighborhoodService;

    // Date and time formatters
    DateTimeFormatter dateFormattter = DateTimeFormatter.ofPattern("E, MM/dd/yyyy");
    DateTimeFormatter timeFormattter = DateTimeFormatter.ofPattern("h:mm a");

    // Constructor
    public CombinedService(CallService callService, IncidentService incidentService,
            NeighborhoodService neighborhoodService) {
        this.callService = callService;
        this.incidentService = incidentService;
        this.neighborhoodService = neighborhoodService;
    }

    // Retrieves DTOs based on query
    public List<DisplayDTO> getDTOs(QueryDTO query)
            throws Exception {

        // List to hold retrieved DTOs
        List<DisplayDTO> items = new ArrayList<>();

        // Delegate retrieval based on source requsted "calls" or "incidents"
        if (query.getSource().equals("calls")) {
            items = callService.getCalls(query);
        } else {
            items = incidentService.getIncidents(query);
        }

        // Process each DTO
        for (DisplayDTO displayDTO : items) {
            // Format neighborhood name
            displayDTO.setNeighborhood(neighborhoodService.getNeighborhoodAsDisplayName(displayDTO.getNeighborhood()));

            // Format date
            displayDTO.setDisplayDate(displayDTO.getDatetime().format(dateFormattter));

            // Format time if DTO type is CALL
            if (displayDTO.getType().equals(Type.CALL)) {
                displayDTO.setDisplayTime(displayDTO.getDatetime().format(timeFormattter));
            }
        }

        // Sort DTOs based on query criteria (datetime, location, type)
        DTOSorting.sort(query.getSortMethod(), query.getSortDirection(), items);

        return items; // Return sorted DTOs
    }
}
