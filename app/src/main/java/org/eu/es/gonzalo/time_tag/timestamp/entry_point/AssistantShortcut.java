package org.eu.es.gonzalo.time_tag.timestamp.entry_point;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;

public class AssistantShortcut extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistant_view);
    }
}
