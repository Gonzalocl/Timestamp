package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.ContextInitializer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextInitializer.initializeAllContexts(this);
        setContentView(R.layout.main_activity);
    }

    public void onClickTimestampButton(View view) {
        Toast.makeText(this, "onClickTimestampButton", Toast.LENGTH_SHORT).show();
    }

    public void onClickConfigurationButton(View view) {
        startActivity(new Intent(this, ConfigurationActivity.class));
    }

}
