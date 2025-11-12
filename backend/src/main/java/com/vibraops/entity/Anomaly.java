package com.vibraops.entity;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Records an anomaly detected in vibration data.  Stores the device,
 * timestamp, which rule triggered the anomaly and the calculated score.
 */
@Entity
public class Anomaly {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    private Device device;
    private Instant ts;
    private String rule;
    private double score;
    private String note;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Device getDevice() {
        return device;
    }
    public void setDevice(Device device) {
        this.device = device;
    }
    public Instant getTs() {
        return ts;
    }
    public void setTs(Instant ts) {
        this.ts = ts;
    }
    public String getRule() {
        return rule;
    }
    public void setRule(String rule) {
        this.rule = rule;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}