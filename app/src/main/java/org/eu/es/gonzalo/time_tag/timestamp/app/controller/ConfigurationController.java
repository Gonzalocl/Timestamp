package org.eu.es.gonzalo.time_tag.timestamp.app.controller;

import org.eu.es.gonzalo.time_tag.timestamp.app.model.Configuration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.ConfigurationRepository;

public class ConfigurationController {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationController(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Configuration getConfiguration() {
        return configurationRepository.get();
    }

    public void saveConfiguration(Configuration configuration) {
        configurationRepository.save(configuration);
    }
}
