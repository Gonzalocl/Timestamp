package org.eu.es.gonzalo.time_tag.timestamp.io.preferences;

import android.content.SharedPreferences;

import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.SharedPreferencesContext;

import java.util.Optional;

public class SharedPreferenceRepository implements PreferenceRepository {
    @Override
    public Optional<String> get(String preference) {
        SharedPreferences sharedPreferences = SharedPreferencesContext.getInstance().getSharedPreferences();
        return Optional.ofNullable(sharedPreferences.getString(preference, null));
    }

    @Override
    public void set(String preference, String value) {
        SharedPreferences sharedPreferences = SharedPreferencesContext.getInstance().getSharedPreferences();
        sharedPreferences.edit().putString(preference, value).apply();
    }
}
