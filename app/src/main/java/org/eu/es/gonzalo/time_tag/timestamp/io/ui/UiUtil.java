package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.app.configuration.PreferenceConfiguration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.AndroidContext;
import org.eu.es.gonzalo.time_tag.timestamp.io.telegram.TelegramBotAPI;

import java.time.ZonedDateTime;
import java.util.Optional;

public class UiUtil {

    public static void setErrorView(Activity activity, String message) {

        activity.setContentView(R.layout.error_view);

        TextView errorTextView = activity.findViewById(R.id.error_view_error_text_view);
        errorTextView.setText(message);

        Button closeButton = activity.findViewById(R.id.error_view_close_button);
        closeButton.setOnClickListener(view -> activity.finish());

    }

    public static void sendTelegramBotMessageTimestamp() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Optional<String> telegram_bot_api_token = preferenceRepository.get(PreferenceRepository.Preference.TELEGRAM_BOT_API_TOKEN);
        Optional<String> telegram_bot_user_chat_id = preferenceRepository.get(PreferenceRepository.Preference.TELEGRAM_BOT_USER_CHAT_ID);
        if (!(telegram_bot_api_token.isPresent() && telegram_bot_user_chat_id.isPresent())) {
            Toast.makeText(AndroidContext.getInstance().getApplicationContext(), "ERROR: token or chat id not present", Toast.LENGTH_SHORT).show();
            return;
        }

        TelegramBotAPI telegramBotAPI = new TelegramBotAPI(telegram_bot_api_token.get(), telegram_bot_user_chat_id.get());
        telegramBotAPI.setContext(AndroidContext.getInstance().getApplicationContext());
        telegramBotAPI.sendMessageNotificationDisabled(String.format("/fix %s", ZonedDateTime.now().toOffsetDateTime()));
    }
}
