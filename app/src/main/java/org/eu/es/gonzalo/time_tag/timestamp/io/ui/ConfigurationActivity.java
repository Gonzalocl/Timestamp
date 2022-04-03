package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.app.configuration.ConfigurationConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_activity);

        // get settings
        Map<String, String> settings = ConfigurationConfiguration.getConfigurationUseCase().get();

        // TODO set settings in ui
    }

    public void onClickSaveButton(View view) {
        // TODO get settings from ui
        Map<String, String> settings = new HashMap<>();

        // save configuration
        ConfigurationConfiguration.getConfigurationUseCase().save(settings);

        // finish this activity and return to main activity
        finish();
    }
}
