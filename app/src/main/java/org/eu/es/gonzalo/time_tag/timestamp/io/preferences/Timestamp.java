package org.eu.es.gonzalo.time_tag.timestamp.io.preferences;

import java.time.OffsetDateTime;

public class Timestamp {
    private OffsetDateTime offsetDateTime;
    private boolean sent;

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }
}
