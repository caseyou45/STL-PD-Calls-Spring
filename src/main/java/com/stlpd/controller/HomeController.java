package com.stlpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stlpd.dto.CallCountDTO;
import com.stlpd.error.ErrorDisplayHandler;
import com.stlpd.service.CombinedService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final CombinedService combinedService;

    public HomeController(CombinedService combinedService) {
        this.combinedService = combinedService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(name = "daysAgo", required = false, defaultValue = "1") String daysAgo,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "DESC") String sortDirection,
            @RequestParam(name = "sortMethod", required = false, defaultValue = "datetimeSort") String sortMethod,

            Model model) {

        List<CallCountDTO> calls = new ArrayList<>();
        String errorMessage = "";

        try {
            calls = combinedService.createDTOS(daysAgo,
                    type, location, sortDirection, sortMethod);

        } catch (Exception e) {
            errorMessage = ErrorDisplayHandler.GetErrorString(e);
        }

        model.addAttribute("calls", calls);
        model.addAttribute("type", type);
        model.addAttribute("location", location);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortMethod", sortMethod);
        model.addAttribute("error", errorMessage);

        return "index";
    }
}
