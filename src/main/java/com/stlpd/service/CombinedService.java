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

@Service
public class CombinedService {

    @Autowired
    private CallRepository callRepository;

    @Autowired
    private CountRepository countRepository;

    public List<CallCountDTO> findByDatetimeAfterOrderByDatetimeDesc(LocalDateTime date) {
        List<CallCountDTO> list = new ArrayList<>();

        List<Call> calls = callRepository.findByDatetimeAfterOrderByDatetimeDesc(date);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a   EEE, MM/dd/yyyy ");

        for (Call call : calls) {

            CallCountDTO dto = new CallCountDTO(call);
            dto.setDisplayDateTime(dto.getCallDatetime().format(dateTimeFormatter).toString());
            list.add(dto);
        }
        return list;

    }

}
