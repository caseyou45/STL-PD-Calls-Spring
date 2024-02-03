package com.stlpd.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.model.Call;
import com.stlpd.respository.CallRepository;

@Service
public class CallService {

    // Repository instances
    private CallRepository callRepository;
    private final NeighborhoodService neighborhoodService;

    // Constructor
    public CallService(CallRepository callRepository,
            NeighborhoodService neighborhoodService) {
        this.callRepository = callRepository;
        this.neighborhoodService = neighborhoodService;
    }

    // Retrieves calls based on query
    public List<DisplayDTO> getCalls(QueryDTO query) throws Exception {

        // List to hold display DTOs
        List<DisplayDTO> displayDTOs = new ArrayList<>();

        // List to hold retrieved calls
        List<Call> calls = new ArrayList<>();

        // Formatter for string dates
        DateTimeFormatter formatterString = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Initialize date variables
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        Long id = null;

        // Convert focusID to Long if not empty. This connects with ZOOM functionality
        // on incident/location
        if (query.getFocusID() != null && !query.getFocusID().isEmpty()) {
            id = Long.valueOf(query.getFocusID());
        }

        // Set endDate to current datetime if not provided
        if (query.getEndDate() == null || query.getEndDate().isEmpty()) {
            endDate = LocalDateTime.now();
            query.setEndDate(endDate.format(formatterString));
        } else {
            // Parse provided endDate
            LocalDate endDateDate = LocalDate.parse(query.getEndDate(), DateTimeFormatter.ISO_DATE);
            endDate = LocalDateTime.of(endDateDate, LocalTime.MIDNIGHT);
        }

        // Set startDate to yesterday if not provided
        if (query.getStartDate() == null || query.getStartDate().isEmpty()) {
            startDate = endDate.minusDays(1);
            query.setStartDate(startDate.format(formatterString));
        } else {
            // Parse provided startDate
            LocalDate startDateDate = LocalDate.parse(query.getStartDate(), DateTimeFormatter.ISO_DATE);
            startDate = LocalDateTime.of(startDateDate, LocalTime.MIDNIGHT);
        }

        // Retrieve parameters from query
        String type = query.getOffense();
        String location = query.getLocation();
        String neighborhood = query.getNeighborhood();

        // Convert neighborhood to number
        neighborhood = neighborhoodService.getNeighborhoodAsNumber(neighborhood);

        // Retrieve calls based on dynamic parameters
        calls = callRepository.findByDynamicParameters(id, type, location, neighborhood, startDate, endDate);

        // Convert calls to display DTOs
        for (Call call : calls) {
            DisplayDTO displayDTO = new DisplayDTO(call);
            displayDTOs.add(displayDTO);
        }

        return displayDTOs; // Return display DTOs
    }
}
