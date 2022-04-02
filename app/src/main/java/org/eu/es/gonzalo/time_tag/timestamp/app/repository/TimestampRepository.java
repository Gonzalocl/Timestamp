package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import org.eu.es.gonzalo.time_tag.timestamp.app.domain.Timestamp;

public interface TimestampRepository {
    void save(Timestamp timestamp);
}
