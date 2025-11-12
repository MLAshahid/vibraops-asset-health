package com.vibraops.ws;

import com.vibraops.entity.Anomaly;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Helper component for broadcasting anomalies to STOMP subscribers.  Sends
 * JSON payloads to `/topic/anomalies` whenever a new anomaly is detected.
 */
@Component
public class SimNotifier {
    private final SimpMessagingTemplate messaging;
    public SimNotifier(SimpMessagingTemplate messaging) {
        this.messaging = messaging;
    }
    public void broadcast(Anomaly anomaly) {
        messaging.convertAndSend("/topic/anomalies", anomaly);
    }
}