package org.eu.es.gonzalo.time_tag.timestamp.app.configuration;

import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.SharedPreferenceRepository;

public class PreferenceConfiguration {

    private static final PreferenceRepository preferenceRepository = new SharedPreferenceRepository();

    public static PreferenceRepository getPreferenceRepository() {
        return preferenceRepository;
    }
}
