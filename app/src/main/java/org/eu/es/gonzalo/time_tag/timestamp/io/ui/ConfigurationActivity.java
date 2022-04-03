package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;

public class ConfigurationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_activity);
    }

    public void onClickSaveButton(View view) {
        Toast.makeText(this, "onClickSaveButton", Toast.LENGTH_SHORT).show();
    }
}
