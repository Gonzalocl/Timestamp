package org.eu.es.gonzalo.time_tag.timestamp.app.use_case;

import org.eu.es.gonzalo.time_tag.timestamp.app.controller.ConfigurationController;
import org.eu.es.gonzalo.time_tag.timestamp.app.model.Configuration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.ConfigurationRepository;

import java.util.Map;
import java.util.function.Consumer;

public class ConfigurationUseCase {

    private final ConfigurationController configurationController;

    // TODO inject controller
    public ConfigurationUseCase(ConfigurationRepository configurationRepository) {
        configurationController = new ConfigurationController(configurationRepository);
    }

    public void get(Consumer<Map<String, String>> success, Consumer<Throwable> error) {
        configurationController.getConfiguration(configuration -> success.accept(configuration.getSettings()), error);
    }

    public void save(Map<String, String> settings) {
        configurationController.saveConfiguration(new Configuration(settings));
    }
}
