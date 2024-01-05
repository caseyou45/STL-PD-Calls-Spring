package com.stlpd.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stlpd.dto.CallCountDTO;
import com.stlpd.model.Call;
import com.stlpd.respository.CallRepository;
import com.stlpd.service.CombinedService;

import java.time.LocalDateTime;

import java.util.List;

@Controller
public class HomeController {

    private final CombinedService combinedService;

    public HomeController(CombinedService combinedService) {
        this.combinedService = combinedService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(name = "timeRange", required = false) Integer daysAgo,
            Model model) {

        if (daysAgo == null) {
            daysAgo = 1;
        }

        LocalDateTime date = LocalDateTime.now().minusDays(daysAgo);

        List<CallCountDTO> calls = combinedService.findByDatetimeAfterOrderByDatetimeDesc(date);

        model.addAttribute("calls", calls);
        model.addAttribute("type", "");
        model.addAttribute("location", "");

        return "index";
    }
}
