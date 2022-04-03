package org.eu.es.gonzalo.time_tag.timestamp.app.configuration;

import org.eu.es.gonzalo.time_tag.timestamp.app.use_case.ConfigurationUseCase;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.repository.DbConfigurationRepository;

public class ConfigurationConfiguration {

    public static ConfigurationUseCase getConfigurationUseCase() {
        return new ConfigurationUseCase(new DbConfigurationRepository());
    }
}
