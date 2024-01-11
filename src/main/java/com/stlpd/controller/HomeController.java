package com.stlpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stlpd.dto.DisplayDTO;
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
            @RequestParam(name = "source", required = false, defaultValue = "call") String source,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "sortDirection", required = false) String sortDirection,
            @RequestParam(name = "sortMethod", required = false) String sortMethod,
            Model model) {

        List<DisplayDTO> items = new ArrayList<>();

        String errorMessage = "";

        try {
            items = combinedService.getDTOs(source, type, location, startDate, endDate, sortDirection, sortMethod);

        } catch (Exception e) {
            errorMessage = ErrorDisplayHandler.GetErrorString(e);
        }

        model.addAttribute("items", items);
        model.addAttribute("source", source);
        model.addAttribute("type", type);
        model.addAttribute("location", location);
        model.addAttribute("startDate", location);
        model.addAttribute("endDate", location);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortMethod", sortMethod);
        model.addAttribute("errorMessage", errorMessage);

        return "index";
    }
}
