package org.eu.es.gonzalo.time_tag.timestamp.io.context;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import org.eu.es.gonzalo.time_tag.timestamp.io.context.exception.SharedPreferencesContextNotInitializedException;

public class SharedPreferencesContext {

    private final SharedPreferences sharedPreferences;

    private static SharedPreferencesContext sharedPreferencesContext;

    public static synchronized void initialize(Context context) {
        if (sharedPreferencesContext == null) {
            sharedPreferencesContext = new SharedPreferencesContext(context);
        }
    }

    private SharedPreferencesContext(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public static SharedPreferencesContext getInstance() {
        if (sharedPreferencesContext == null) {
            throw new SharedPreferencesContextNotInitializedException();
        }
        return sharedPreferencesContext;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
