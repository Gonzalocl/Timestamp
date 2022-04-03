package org.eu.es.gonzalo.time_tag.timestamp.app.model;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private final Map<String, String> settings;

    public Configuration() {
        // TODO set default settings
        settings = new HashMap<>();
    }

    public Configuration(Map<String, String> settings) {
        this.settings = settings;
    }

    public Map<String, String> getSettings() {
        return settings;
    }
}
