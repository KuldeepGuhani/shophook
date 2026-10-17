package com.shophook;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class WebhookEvent {
    @Id
    private String eventId;            // "evt_1001" (unique from provider)

    @Column(length = 10000)
    private String payload;            // the raw JSON

    private String status;             // RECEIVED, PROCESSED, FAILED
    private Instant receivedAt;

    protected WebhookEvent() {}        // JPA needs this

    public WebhookEvent(String eventId, String payload) {
        this.eventId = eventId;
        this.payload = payload;
        this.status = "SUCESS";
        this.receivedAt = Instant.now();
    }

    public String getEventId() { return eventId; }
    public String getPayload() { return payload; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}