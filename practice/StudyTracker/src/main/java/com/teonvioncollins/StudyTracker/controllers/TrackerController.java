package com.teonvioncollins.StudyTracker.controllers;

import com.teonvioncollins.StudyTracker.services.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    @PostMapping("/add-session")
    public String addSession(@RequestParam String topic, @RequestParam int minutes, @RequestParam LocalDate date) {
        trackerService.addSession(topic, minutes, date);
        return "redirect:/tracker";
    }

    @PostMapping("/delete-session")
    public String deleteSession(@RequestParam Long id) {
        trackerService.deleteSession(id);
        return "redirect:/tracker";
    }
}
