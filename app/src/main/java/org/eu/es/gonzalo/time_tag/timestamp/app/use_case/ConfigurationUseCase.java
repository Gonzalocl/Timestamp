package org.eu.es.gonzalo.time_tag.timestamp.app.use_case;

import org.eu.es.gonzalo.time_tag.timestamp.app.controller.ConfigurationController;
import org.eu.es.gonzalo.time_tag.timestamp.app.model.Configuration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.ConfigurationRepository;

import java.util.Map;

public class ConfigurationUseCase {

    private final ConfigurationController configurationController;

    public ConfigurationUseCase(ConfigurationRepository configurationRepository) {
        configurationController = new ConfigurationController(configurationRepository);
    }

    public Map<String, String> get() {
        return configurationController.getConfiguration().getSettings();
    }

    public void save(Map<String, String> settings) {
        configurationController.saveConfiguration(new Configuration(settings));
    }
}
