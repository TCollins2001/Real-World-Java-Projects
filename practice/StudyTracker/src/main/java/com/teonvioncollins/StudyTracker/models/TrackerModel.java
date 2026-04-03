package com.teonvioncollins.StudyTracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class TrackerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String topic;
    public int minutes;

    public LocalDate date;

    public TrackerModel() {}

    public TrackerModel(Long id, String topic, int minutes, LocalDate date) {
        this.id = id;
        this.topic = topic;
        this.minutes = minutes;
        this.date = date;
    }

    public TrackerModel(String topic, int minutes, LocalDate date) {
        this.topic = topic;
        this.minutes = minutes;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic() {
        this.topic = topic;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
