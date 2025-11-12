package com.vibraops.sim;

import com.vibraops.entity.Device;
import com.vibraops.entity.Sample;
import com.vibraops.entity.Anomaly;
import com.vibraops.repo.DeviceRepository;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

/**
 * Generates pseudo‑random vibration samples and determines when an anomaly
 * occurs based on simple threshold rules.  In a real system this would be
 * replaced by more sophisticated analytics.
 */
@Component
public class SimRules {
    private final DeviceRepository devices;
    private final Random random = new Random();
    public SimRules(DeviceRepository devices) {
        this.devices = devices;
    }
    /**
     * Create a new sample for the given device.  Values are random with
     * occasional spikes to simulate anomalies.
     */
    public Sample nextSampleFor(Device device) {
        double rms = 0.8 + random.nextGaussian() * 0.2;
        double peak = 1.5 + random.nextGaussian() * 0.5;
        // Introduce occasional spikes
        if (random.nextDouble() < 0.05) {
            rms += 1.0 + random.nextDouble();
            peak += 2.0 + random.nextDouble();
        }
        double kurtosis = 3.0 + random.nextGaussian() * 0.5;
        Sample sample = new Sample();
        sample.setDevice(device);
        sample.setTs(Instant.now());
        sample.setRms(rms);
        sample.setPeak(peak);
        sample.setKurtosis(kurtosis);
        return sample;
    }
    /**
     * Check a sample against simple rules.  If either the rms exceeds 1.5 or
     * the peak exceeds 3.0 we flag an anomaly.  Returns an Optional
     * containing the anomaly if triggered.
     */
    public Optional<Anomaly> check(Sample sample) {
        if (sample.getRms() > 1.5) {
            Anomaly a = new Anomaly();
            a.setDevice(sample.getDevice());
            a.setTs(sample.getTs());
            a.setRule("RMS_THRESHOLD");
            a.setScore(sample.getRms());
            a.setNote("rms > 1.5");
            return Optional.of(a);
        }
        if (sample.getPeak() > 3.0) {
            Anomaly a = new Anomaly();
            a.setDevice(sample.getDevice());
            a.setTs(sample.getTs());
            a.setRule("PEAK_THRESHOLD");
            a.setScore(sample.getPeak());
            a.setNote("peak > 3.0");
            return Optional.of(a);
        }
        return Optional.empty();
    }
}