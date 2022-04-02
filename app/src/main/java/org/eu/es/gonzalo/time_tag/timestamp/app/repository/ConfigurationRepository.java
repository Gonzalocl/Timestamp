package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import org.eu.es.gonzalo.time_tag.timestamp.app.domain.Configuration;

public interface ConfigurationRepository {
    void save(Configuration configuration);
    Configuration get();
}
