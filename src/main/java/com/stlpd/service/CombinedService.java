package com.stlpd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stlpd.dto.CallCountDTO;
import com.stlpd.model.Call;
import com.stlpd.model.Count;
import com.stlpd.respository.CallRepository;
import com.stlpd.respository.CountRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class CombinedService {

    @Autowired
    private CallRepository callRepository;

    @Autowired
    private CountRepository countRepository;

    public List<CallCountDTO> createDTOS(String daysAgo, String type, String location, String sortDirection,
            String sortMethod) throws Exception {

        // Make sure day span can be parsed without disruption
        int daysAgoAsInt = 1;

        daysAgoAsInt = Integer.valueOf(daysAgo);

        LocalDateTime date = LocalDateTime.now().minusDays(daysAgoAsInt);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a   EEE, MM/dd/yyyy ");

        List<CallCountDTO> callCountDTOs = new ArrayList<>();

        List<Call> calls = new ArrayList<>();

        if (type != null && !type.isEmpty() && location != null && !location.isEmpty()) {
            calls = callRepository.findByLocationAndTypeAndDatetimeAfterOrderByDatetimeDesc(location, type, date);
        } else if (type == null && location == null) {

            calls = callRepository.findByDatetimeAfterOrderByDatetimeDesc(date);

        } else if (location != null && !location.isEmpty()) {
            calls = callRepository.findByLocationAndDatetimeAfterOrderByDatetimeDesc(location, date);
        } else if (type != null && !type.isEmpty()) {
            calls = callRepository.findByTypeAndDatetimeAfterOrderByDatetimeDesc(type, date);

        }

        for (Call call : calls) {
            CallCountDTO dto = new CallCountDTO(call);
            // dto.setCountLocation(
            // countRepository.findSumByLocationAndDatetimeBetween(call.getLocation(), date,
            // LocalDateTime.now()));
            // dto.setCountType(countRepository.findSumByTypeAndDatetimeBetween(call.getType(),
            // date, LocalDateTime.now()));

            dto.setDisplayDateTime(dto.getCallDatetime().format(dateTimeFormatter).toString());
            callCountDTOs.add(dto);
        }

        CallCountDTOSorting.sort(sortMethod, sortDirection, callCountDTOs);

        return callCountDTOs;

    }

}
