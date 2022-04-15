package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.app.configuration.PreferenceConfiguration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.AndroidContext;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.ContextInitializer;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.Timestamp;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.Timestamps;

import java.util.Collections;
import java.util.Optional;

public class MainActivity extends Activity {

    private AndroidContext androidContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextInitializer.initializeAllContexts(this);
        androidContext = AndroidContext.getInstance();
        setContentView(R.layout.main_activity);

        updateMainTextView();
    }

    public void onClickTimestampButton(View view) {
        UiUtil.storeTimestamp();
        updateMainTextView();
    }

    public void onClickConfigurationButton(View view) {
        startActivity(new Intent(this, PreferencesActivity.class));
    }

    public void onClickClearLastTimestampsButton(View view) {
        UiUtil.clearLastTimestamps();
        updateMainTextView();
    }

    public void onClickSendButton(View view) {
        Button sendButton = (Button) view;
        sendButton.setEnabled(false);
        UiUtil.sendTelegramBotMessageTimestamps(this::updateMainTextView,
                () -> sendButton.setEnabled(true));
    }

    private void updateMainTextView() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration
                .getPreferenceRepository();
        Optional<String> last_timestamps = preferenceRepository
                .getString(androidContext.getId(R.string.id_last_timestamps));

        String timestampsText;
        Gson gson = new Gson();
        Timestamps timestamps;
        if (!last_timestamps.isPresent()
                || (timestamps = gson.fromJson(last_timestamps.get(), Timestamps.class)) == null
                || timestamps.getTimestamps().isEmpty()) {
            timestampsText = "NO TIMESTAMPS";
        } else {
            Collections.reverse(timestamps.getTimestamps());
            StringBuilder stringBuilder = new StringBuilder();
            for (Timestamp timestamp : timestamps.getTimestamps()) {
                stringBuilder.append(timestamp.getTimestamp())
                        .append(" - ")
                        .append(timestamp.isSent() ? "sent" : "")
                        .append("\n");
            }
            timestampsText = stringBuilder.toString();
        }
        TextView textView = findViewById(R.id.main_activity_text_view);
        textView.setText(timestampsText);
    }

}
