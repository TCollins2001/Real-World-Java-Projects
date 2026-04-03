package com.teonvioncollins.StudyTracker.services;

import com.teonvioncollins.StudyTracker.models.TrackerModel;
import com.teonvioncollins.StudyTracker.repos.TrackerRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrackerService {

    private final TrackerRepo trackerRepo;

    public TrackerService(TrackerRepo trackerRepo) {
        this.trackerRepo = trackerRepo;
    }

    public void addSession(String topic, int minutes, LocalDate date) {
        trackerRepo.save(new TrackerModel(topic, minutes, date));
    }

    public List<TrackerModel> getAllSessions() {
        return trackerRepo.findAll();
    }

    public void deleteSession(Long id) {
        trackerRepo.deleteById(id);
    }

    public int getTotalMinutes() {
        return trackerRepo.findAll().stream().mapToInt(TrackerModel::getMinutes).sum();
    }

    public Long getSessionCount() {
        return trackerRepo.count();
    }
}
