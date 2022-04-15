package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import java.util.Optional;

public interface PreferenceRepository {

    Optional<String> getString(String preference);
    Optional<Long> getLong(String preference);
    void setString(String preference, String value);

}
