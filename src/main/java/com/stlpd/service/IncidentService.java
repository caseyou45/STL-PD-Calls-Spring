package com.stlpd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.model.Incident;
import com.stlpd.respository.IncidentRepository;

@Service
public class IncidentService {

    @Autowired
    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<DisplayDTO> getIncidents(QueryDTO query) throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startDateString = query.getStartDate();
        String endDateString = query.getEndDate();

        List<DisplayDTO> displayDTOs = new ArrayList<>();

        if (startDateString == null || startDateString.isEmpty()) {
            startDateString = "2023-12-30";
            query.setStartDate(startDateString);

        }

        if (endDateString == null || endDateString.isEmpty()) {
            endDateString = "2023-12-31";
            query.setEndDate(endDateString);

        }

        LocalDateTime startDate = LocalDateTime.parse(startDateString + " 00:00:00", dateTimeFormatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateString + " 00:00:00", dateTimeFormatter);

        for (Incident incident : incidentRepository.findByConvertedDateBetweenOrderByConvertedDateDesc(startDate,
                endDate)) {

            DisplayDTO displayDTO = new DisplayDTO(incident);
            displayDTOs.add(displayDTO);
        }

        return displayDTOs;

    }

}
