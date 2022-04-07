package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import java.util.Optional;

public interface PreferenceRepository {
    Optional<String> get(String preference);
}
