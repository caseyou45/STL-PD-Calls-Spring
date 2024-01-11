package com.stlpd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.model.Call;
import com.stlpd.respository.CallRepository;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepository;

    public List<DisplayDTO> getCallDtos(String soruce, String type, String location, String startDateString,
            String endDateString,
            String sortDirection, String sortMethod) throws Exception {

        List<DisplayDTO> displayDTOs = new ArrayList<>();
        List<Call> calls = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        System.out.println(startDateString);
        if (endDateString == null || endDateString.isEmpty()) {
            endDate = LocalDateTime.now();
        } else {
            endDateString += " 00:00:00";
            endDate = LocalDateTime.parse(endDateString, dateTimeFormatter);
        }

        if (startDateString == null || startDateString.isEmpty()) {
            startDate = endDate.minusDays(1);
        } else {
            startDateString += " 00:00:00";
            startDate = LocalDateTime.parse(startDateString, dateTimeFormatter);
        }
        System.out.println(startDate);

        if ((type == null || type.isEmpty()) && (location == null || location.isEmpty())) {
            calls = callRepository.findByDatetimeBetweenOrderByDatetimeDesc(startDate,
                    endDate);
        } else if (type == null || type.isEmpty()) {
            calls = callRepository.findByLocationAndDatetimeBetweenOrderByDatetimeDesc(location, startDate,
                    endDate);
        } else if (location == null || location.isEmpty()) {
            calls = callRepository.findByTypeAndDatetimeBetweenOrderByDatetimeDesc(type, startDate,
                    endDate);
        } else {
            calls = callRepository.findByTypeAndLocationAndDatetimeBetweenOrderByDatetimeDesc(type, location, startDate,
                    endDate);
        }

        for (Call call : calls) {

            DisplayDTO displayDTO = new DisplayDTO(call);

            displayDTOs.add(displayDTO);

        }

        return displayDTOs;
    }

}
