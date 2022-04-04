package org.eu.es.gonzalo.time_tag.timestamp.app.controller;

import org.eu.es.gonzalo.time_tag.timestamp.app.model.Configuration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.ConfigurationRepository;

import java.util.function.Consumer;

public class ConfigurationController {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationController(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public void getConfiguration(Consumer<Configuration> success, Consumer<Throwable> error) {
        configurationRepository.get(success, error);
    }

    public void saveConfiguration(Configuration configuration) {
        configurationRepository.save(configuration);
    }
}
