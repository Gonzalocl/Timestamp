package org.eu.es.gonzalo.time_tag.timestamp.io.entry_point;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.io.telegram.TelegramBotAPI;

import java.time.ZonedDateTime;

public class Launcher extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        TelegramBotAPI telegramBotAPI = new TelegramBotAPI("", "");
        telegramBotAPI.setContext(this);
        telegramBotAPI.sendMessageNotificationDisabled(String.format("/fix %s", ZonedDateTime.now().toOffsetDateTime()));
    }
}
