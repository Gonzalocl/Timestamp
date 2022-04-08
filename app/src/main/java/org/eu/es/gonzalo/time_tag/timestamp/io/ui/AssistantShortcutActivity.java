package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.ContextInitializer;

public class AssistantShortcutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextInitializer.initializeAllContexts(this);
        setContentView(R.layout.assistant_view);
        UiUtil.sendTelegramBotMessageTimestamp();
    }
}
