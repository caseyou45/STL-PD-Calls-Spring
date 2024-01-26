package com.stlpd.service;

import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.enums.Type;
import com.stlpd.model.Neighborhood;
import com.stlpd.respository.NeighborhoodRepository;
import com.stlpd.util.DTOSorting;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CombinedService {

    private CallService callService;

    private IncidentService incidentService;

    private final NeighborhoodRepository neighborhoodRepository;

    DateTimeFormatter dateFormattter = DateTimeFormatter.ofPattern("E, MM/dd/yyyy");
    DateTimeFormatter timeFormattter = DateTimeFormatter.ofPattern("h:mm a");

    public CombinedService(CallService callService, IncidentService incidentService,
            NeighborhoodRepository neighborhoodRepository) {
        this.callService = callService;
        this.incidentService = incidentService;
        this.neighborhoodRepository = neighborhoodRepository;
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
                Optional<Neighborhood> neighborhoodEntity = neighborhoodRepository.findByNumber(neighborhoodString);
                if (neighborhoodEntity.isPresent()) {
                    displayDTO.setNeighborhood(neighborhoodEntity.get().getNeighborhood());
                }
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
