package com.teonvioncollins.StudyTracker.controllers;

import com.teonvioncollins.StudyTracker.services.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private TrackerService trackerService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tracker")
    public String tracker(Model model) {
        model.addAttribute("sessions", trackerService.getAllSessions());
        model.addAttribute("totalMinutes", trackerService.getTotalMinutes());
        model.addAttribute("sessionCount", trackerService.getSessionCount());
        return "tracker";
    }
}
