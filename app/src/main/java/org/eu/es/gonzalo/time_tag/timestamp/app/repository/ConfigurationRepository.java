package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import org.eu.es.gonzalo.time_tag.timestamp.app.model.Configuration;

import java.util.function.Consumer;

public interface ConfigurationRepository {
    void save(Configuration configuration);
    void get(Consumer<Configuration> success, Consumer<Throwable> error);
}
