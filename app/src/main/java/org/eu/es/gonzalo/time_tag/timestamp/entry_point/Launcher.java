package org.eu.es.gonzalo.time_tag.timestamp.entry_point;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;

public class Launcher extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
//        if (AndroidContext.context == null) {
//            AndroidContext.context = this;
//            AndroidContext.sendNotification(this, "Launcher: CTX was not set");
//            Toast.makeText(this, "Launcher: CTX was not set", Toast.LENGTH_SHORT).show();
//        } else {
//            AndroidContext.sendNotification(this, "Launcher: CTX was set");
//            Toast.makeText(this, "Launcher: CTX was set", Toast.LENGTH_SHORT).show();
//        }
    }
}
