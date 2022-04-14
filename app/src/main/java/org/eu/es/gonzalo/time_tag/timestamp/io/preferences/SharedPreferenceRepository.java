package org.eu.es.gonzalo.time_tag.timestamp.io.preferences;

import android.content.SharedPreferences;

import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.SharedPreferencesContext;

import java.util.Optional;

public class SharedPreferenceRepository implements PreferenceRepository {
    @Override
    public Optional<String> getString(String preference) {
        SharedPreferences sharedPreferences = SharedPreferencesContext.getInstance().getSharedPreferences();
        return Optional.ofNullable(sharedPreferences.getString(preference, null));
    }

    @Override
    public Optional<Long> getLong(String preference) {
        SharedPreferences sharedPreferences = SharedPreferencesContext.getInstance().getSharedPreferences();
        long value = sharedPreferences.getLong(preference, Long.MAX_VALUE);
        return Optional.ofNullable(value == Long.MAX_VALUE ? null : value);
    }

    @Override
    public void setString(String preference, String value) {
        SharedPreferences sharedPreferences = SharedPreferencesContext.getInstance().getSharedPreferences();
        sharedPreferences.edit().putString(preference, value).apply();
    }
}
