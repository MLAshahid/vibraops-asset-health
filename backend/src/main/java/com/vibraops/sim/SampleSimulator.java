package com.vibraops.sim;

import com.vibraops.entity.Device;
import com.vibraops.entity.Sample;
import com.vibraops.repo.AnomalyRepository;
import com.vibraops.repo.DeviceRepository;
import com.vibraops.repo.SampleRepository;
import com.vibraops.ws.SimNotifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Periodically generates vibration samples for all devices and records
 * anomalies via the {@link SimRules}.  Runs every two seconds.
 */
@Component
public class SampleSimulator {
    private final DeviceRepository devices;
    private final SampleRepository samples;
    private final AnomalyRepository anomalies;
    private final SimRules rules;
    private final SimNotifier notifier;
    public SampleSimulator(DeviceRepository devices, SampleRepository samples,
                           AnomalyRepository anomalies, SimRules rules, SimNotifier notifier) {
        this.devices = devices;
        this.samples = samples;
        this.anomalies = anomalies;
        this.rules = rules;
        this.notifier = notifier;
    }
    @Scheduled(fixedDelay = 2000)
    public void tick() {
        List<Device> all = devices.findAll();
        for (Device d : all) {
            Sample sample = rules.nextSampleFor(d);
            samples.save(sample);
            d.setLastSeen(sample.getTs());
            devices.save(d);
            rules.check(sample).ifPresent(anomaly -> {
                anomalies.save(anomaly);
                notifier.broadcast(anomaly);
            });
        }
    }
}