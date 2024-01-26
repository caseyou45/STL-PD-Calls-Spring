package com.stlpd.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.model.Call;
import com.stlpd.model.Neighborhood;
import com.stlpd.respository.CallRepository;
import com.stlpd.respository.NeighborhoodRepository;

@Service
public class CallService {

    private CallRepository callRepository;

    private final NeighborhoodRepository neighborhoodRepository;

    public CallService(CallRepository callRepository, NeighborhoodRepository neighborhoodRepository) {
        this.callRepository = callRepository;
        this.neighborhoodRepository = neighborhoodRepository;

    }

    public List<DisplayDTO> getCalls(QueryDTO query) throws Exception {

        List<DisplayDTO> displayDTOs = new ArrayList<>();
        List<Call> calls = new ArrayList<>();
        DateTimeFormatter formatterString = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        Long id = null;

        if (query.getFocusID() != null && !query.getFocusID().isEmpty()) {
            id = Long.valueOf(query.getFocusID());
        }

        if (query.getEndDate() == null || query.getEndDate().isEmpty()) {
            endDate = LocalDateTime.now();
            query.setEndDate(endDate.format(formatterString));
        } else {
            LocalDate endDateDate = LocalDate.parse(query.getEndDate(), DateTimeFormatter.ISO_DATE);
            endDate = LocalDateTime.of(endDateDate, LocalTime.MIDNIGHT);
        }

        if (query.getStartDate() == null || query.getStartDate().isEmpty()) {
            startDate = endDate.minusDays(1);
            query.setStartDate(startDate.format(formatterString));

        } else {
            LocalDate startDateDate = LocalDate.parse(query.getStartDate(), DateTimeFormatter.ISO_DATE);
            startDate = LocalDateTime.of(startDateDate, LocalTime.MIDNIGHT);
        }

        String type = query.getOffense();
        String location = query.getLocation();
        String neighborhood = query.getNeighborhood();

        if (neighborhood != null && !neighborhood.isEmpty()) {
            Optional<Neighborhood> neighborhoodEntity = neighborhoodRepository.findByNeighborhood(neighborhood);
            if (neighborhoodEntity.isPresent()) {
                neighborhood = neighborhoodEntity.get().getNumber();
            }
        }

        calls = callRepository.findByDynamicParameters(id, type, location, neighborhood, startDate, endDate);

        for (Call call : calls) {

            DisplayDTO displayDTO = new DisplayDTO(call);

            displayDTOs.add(displayDTO);

        }

        return displayDTOs;
    }

}
