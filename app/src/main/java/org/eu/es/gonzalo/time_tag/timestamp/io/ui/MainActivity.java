package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.app.configuration.PreferenceConfiguration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.ContextInitializer;

import java.util.Optional;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextInitializer.initializeAllContexts(this);
        setContentView(R.layout.main_activity);

        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Optional<String> last_timestamps = preferenceRepository.get(PreferenceRepository.Preference.LAST_TIMESTAMPS);
        String timestampsText = last_timestamps.orElse("NO TIMESTAMPS");

        TextView textView = findViewById(R.id.main_activity_text_view);
        textView.setText(timestampsText);
    }

    public void onClickTimestampButton(View view) {
        UiUtil.sendAndStoreTelegramBotMessageTimestamps();
    }

    public void onClickConfigurationButton(View view) {
        startActivity(new Intent(this, PreferencesActivity.class));
    }

    public void onClickClearLastTimestamps(View view) {
        UiUtil.clearLastTimestamps();
    }

}
