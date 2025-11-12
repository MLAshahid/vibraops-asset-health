package com.vibraops.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

/**
 * Represents a monitored machine or asset.  Each device has a name,
 * operational status, optional location and timestamp of last activity.
 */
@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    private String status = "OK";
    private String location;
    private Instant lastSeen;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Instant getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(Instant lastSeen) {
        this.lastSeen = lastSeen;
    }
}