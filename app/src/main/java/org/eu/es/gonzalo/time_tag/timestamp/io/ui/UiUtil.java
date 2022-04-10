package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.app.configuration.PreferenceConfiguration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.AndroidContext;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.Timestamp;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.Timestamps;
import org.eu.es.gonzalo.time_tag.timestamp.io.telegram.TelegramBotAPI;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UiUtil {

    private static final int MAX_LAST_TIMESTAMPS = 25;

    public static void setErrorView(Activity activity, String message) {

        activity.setContentView(R.layout.error_view);

        TextView errorTextView = activity.findViewById(R.id.error_view_error_text_view);
        errorTextView.setText(message);

        Button closeButton = activity.findViewById(R.id.error_view_close_button);
        closeButton.setOnClickListener(view -> activity.finish());

    }

    public static void sendAndStoreTelegramBotMessageTimestamps() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();

        Optional<String> last_timestamps = preferenceRepository.get(PreferenceRepository.Preference.LAST_TIMESTAMPS);
        Gson gson = new Gson();
        List<Timestamp> timestamps;
        if (last_timestamps.isPresent()) {
            timestamps = gson.fromJson(last_timestamps.get(), Timestamps.class).getTimestamps();
        } else {
            timestamps = new LinkedList<>();
        }

        OffsetDateTime now = ZonedDateTime.now().toOffsetDateTime();
        timestamps.add(new Timestamp() {{
            setOffsetDateTime(now);
            setSent(false);
        }});

        preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS,
                gson.toJson(new Timestamps(){{setTimestamps(getLastElementsSubList(timestamps, MAX_LAST_TIMESTAMPS));}}));

        sendTelegramBotMessageTimestamps(preferenceRepository);
    }

    private static void sendTelegramBotMessageTimestamps(PreferenceRepository preferenceRepository) {
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

    public static void clearLastTimestamps() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();

        Gson gson = new Gson();

        preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS,
                gson.toJson(new Timestamps(){{setTimestamps(new LinkedList<>());}}));

    }

    private static <T> List<T> getLastElementsSubList(List<T> list, int elements) {
        if (list.size() < elements) {
            return list;
        }
        return list.subList(list.size()-elements, list.size());
    }
}
