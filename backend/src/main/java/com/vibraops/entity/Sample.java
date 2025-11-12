package com.vibraops.entity;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * A vibration sample recorded for a device.  Stores root mean square (rms),
 * peak amplitude and kurtosis metrics along with a timestamp.
 */
@Entity
public class Sample {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    private Device device;
    private Instant ts;
    private double rms;
    private double peak;
    private double kurtosis;

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
    public double getRms() {
        return rms;
    }
    public void setRms(double rms) {
        this.rms = rms;
    }
    public double getPeak() {
        return peak;
    }
    public void setPeak(double peak) {
        this.peak = peak;
    }
    public double getKurtosis() {
        return kurtosis;
    }
    public void setKurtosis(double kurtosis) {
        this.kurtosis = kurtosis;
    }
}