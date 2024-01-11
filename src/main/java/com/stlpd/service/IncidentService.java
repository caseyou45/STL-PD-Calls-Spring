package com.stlpd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.model.Incident;
import com.stlpd.respository.IncidentRepository;

@Service
public class IncidentService {

    @Autowired
    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<DisplayDTO> createDTOS(String startDateString, String endDateString) {
        startDateString += " 00:00:00";
        endDateString += " 00:00:00";

        List<DisplayDTO> list = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(startDateString, dateTimeFormatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateString, dateTimeFormatter);

        for (Incident incident : incidentRepository.findByConvertedDateBetweenOrderByConvertedDateDesc(startDate,
                endDate)) {
            list.add(new DisplayDTO(incident));

        }

        return list;

    }

}
