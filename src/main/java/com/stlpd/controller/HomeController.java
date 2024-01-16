package com.stlpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.dto.QueryDTO;
import com.stlpd.error.ErrorDisplayHandler;
import com.stlpd.service.CombinedService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CombinedService combinedService;

    public HomeController(CombinedService combinedService) {
        this.combinedService = combinedService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(name = "source", required = false, defaultValue = "calls") String source,
            @RequestParam(name = "offense", required = false) String offense,
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(name = "sortMethod", required = false, defaultValue = "datetimeSort") String sortMethod,
            Model model) {

        List<DisplayDTO> items = new ArrayList<>();

        QueryDTO query = new QueryDTO(source,
                offense, location, startDate, endDate, sortDirection, sortMethod);

        String errorMessage = "";

        try {
            items = combinedService.getDTOs(query);

        } catch (Exception e) {
            errorMessage = ErrorDisplayHandler.GetErrorString(e);
        }

        model.addAttribute("query", query);
        model.addAttribute("items", items);
        model.addAttribute("errorMessage", errorMessage);

        return "index";
    }
}
