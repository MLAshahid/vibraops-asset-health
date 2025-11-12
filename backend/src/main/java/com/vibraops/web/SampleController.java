package com.vibraops.web;

import com.vibraops.entity.Sample;
import com.vibraops.repo.SampleRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

/**
 * REST controller for retrieving vibration samples.  Samples are returned in
 * descending order of timestamp.  The client can supply optional `from` and
 * `to` query parameters to bound the time range.
 */
@RestController
@RequestMapping("/api")
public class SampleController {
    private final SampleRepository samples;
    public SampleController(SampleRepository samples) {
        this.samples = samples;
    }
    @GetMapping("/devices/{id}/samples")
    public List<Sample> samplesForDevice(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {
        Instant start = from != null ? from : Instant.EPOCH;
        Instant end = to != null ? to : Instant.now();
        return samples.findSamples(id, start, end);
    }
}