package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import org.eu.es.gonzalo.time_tag.timestamp.app.model.Timestamp;

public interface TimestampRepository {
    // TODO async and get
    void save(Timestamp timestamp);
}
