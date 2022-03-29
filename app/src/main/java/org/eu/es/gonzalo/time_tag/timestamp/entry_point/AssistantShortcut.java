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
//        if (AndroidContext.context == null) {
//            AndroidContext.context = this;
//            AndroidContext.sendNotification(this, "AssistantShortcut: CTX was not set");
//            Toast.makeText(this, "AssistantShortcut: CTX was not set", Toast.LENGTH_SHORT).show();
//        } else {
//            AndroidContext.sendNotification(this, "AssistantShortcut: CTX was set");
//            Toast.makeText(this, "AssistantShortcut: CTX was set", Toast.LENGTH_SHORT).show();
//        }
    }
}
