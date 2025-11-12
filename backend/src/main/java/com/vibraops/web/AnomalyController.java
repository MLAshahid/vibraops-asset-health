package com.vibraops.web;

import com.vibraops.entity.Anomaly;
import com.vibraops.repo.AnomalyRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

/**
 * REST controller for retrieving anomalies.  Clients may filter by device
 * and time range using optional query parameters.
 */
@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {
    private final AnomalyRepository anomalies;
    public AnomalyController(AnomalyRepository anomalies) {
        this.anomalies = anomalies;
    }
    @GetMapping
    public List<Anomaly> anomalies(
            @RequestParam(required = false) Long deviceId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {
        Instant start = from != null ? from : Instant.EPOCH;
        Instant end = to != null ? to : Instant.now();
        return anomalies.findAnomalies(deviceId, start, end);
    }
}