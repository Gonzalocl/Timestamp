package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        ConfigurationConfiguration.getConfigurationUseCase().get(settings -> {

            // set settings in ui
            LinearLayout settingsLinearLayout = findViewById(R.id.configuration_activity_linear_layout);
            settings.forEach((settingName, settingValue) -> addSetting(settingsLinearLayout, settingName, settingValue));
            findViewById(R.id.configuration_activity_save_button).setClickable(true);

        }, throwable -> UiUtil.setErrorView(ConfigurationActivity.this, throwable.getMessage()));
    }

    public void onClickSaveButton(View view) {
        // TODO get settings from ui
        Map<String, String> settings = new HashMap<>();

        // save configuration
        ConfigurationConfiguration.getConfigurationUseCase().save(settings);

        // finish this activity and return to main activity
        finish();
    }

    private void addSetting(LinearLayout linearLayout, String settingName, String settingValue) {
        addTextView(linearLayout, settingName);
        addEditText(linearLayout, settingValue);
    }

    private void addTextView(LinearLayout linearLayout, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        linearLayout.addView(textView);
    }

    private void addEditText(LinearLayout linearLayout, String text) {
        EditText editText = new EditText(this);
        editText.setText(text);
        linearLayout.addView(editText);
    }
}
