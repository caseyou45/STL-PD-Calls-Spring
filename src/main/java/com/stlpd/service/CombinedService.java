package com.stlpd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stlpd.dto.DisplayDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombinedService {

    private CallService callService;

    @Autowired
    public CombinedService(CallService callService) {
        this.callService = callService;
    }

    public List<DisplayDTO> getDTOs(String source, String type, String location, String startDateString,
            String endDateString,
            String sortDirection, String sortMethod) throws Exception {

        List<DisplayDTO> items = new ArrayList<>();
        if (source.equals("call")) {
            items = callService.getCallDtos(source, type, location, startDateString, endDateString, sortDirection,
                    sortMethod);
        }

        return items;

    }
}
