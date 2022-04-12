package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.widget.Toast;

import com.google.gson.Gson;

import org.eu.es.gonzalo.time_tag.timestamp.app.configuration.PreferenceConfiguration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.PreferenceRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.AndroidContext;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.Timestamp;
import org.eu.es.gonzalo.time_tag.timestamp.io.preferences.Timestamps;
import org.eu.es.gonzalo.time_tag.timestamp.io.telegram.TelegramBotAPI;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UiUtil {

    private static final int MAX_LAST_TIMESTAMPS = 25;
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

    public static void storeTimestamp() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();

        Optional<String> last_timestamps = preferenceRepository.get(PreferenceRepository.Preference.LAST_TIMESTAMPS);
        Gson gson = new Gson();
        Timestamps timestamps;
        if (last_timestamps.isPresent()) {
            timestamps = gson.fromJson(last_timestamps.get(), Timestamps.class);
        } else {
            timestamps = new Timestamps();
            timestamps.setTimestamps(new LinkedList<>());
        }

        Timestamp timestamp = new Timestamp();
        timestamp.setTimestamp(ZonedDateTime.now().format(format));
        timestamp.setSent(false);

        timestamps.getTimestamps().add(timestamp);
        timestamps.setTimestamps(getLastElementsSubList(timestamps.getTimestamps(), MAX_LAST_TIMESTAMPS));

        preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS, gson.toJson(timestamps));

    }

    public static void sendTelegramBotMessageTimestamps(List<Timestamp> timestamps) {

        if (timestamps.isEmpty()) {
            Toast.makeText(AndroidContext.getInstance().getApplicationContext(), "INFO: No Timestamps to send", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Optional<String> telegram_bot_api_token = preferenceRepository.get(PreferenceRepository.Preference.TELEGRAM_BOT_API_TOKEN);
        Optional<String> telegram_bot_user_chat_id = preferenceRepository.get(PreferenceRepository.Preference.TELEGRAM_BOT_USER_CHAT_ID);
        if (!(telegram_bot_api_token.isPresent() && telegram_bot_user_chat_id.isPresent())) {
            Toast.makeText(AndroidContext.getInstance().getApplicationContext(), "ERROR: token or chat id not present", Toast.LENGTH_SHORT).show();
            return;
        }

        TelegramBotAPI telegramBotAPI = new TelegramBotAPI(telegram_bot_api_token.get(), telegram_bot_user_chat_id.get());
        telegramBotAPI.setContext(AndroidContext.getInstance().getApplicationContext());

        String lastTimestamp = timestamps.get(0).getTimestamp();
        boolean anySent = false;
        for (Timestamp timestamp : timestamps) {
            if (!timestamp.isSent()) {
                ZonedDateTime lastDate = ZonedDateTime.parse(lastTimestamp, format);
                ZonedDateTime currentDate = ZonedDateTime.parse(timestamp.getTimestamp(), format);
                telegramBotAPI.sendMessageNotificationDisabled(String.format("Elapsed: %s", Duration.between(lastDate, currentDate)));
                telegramBotAPI.sendMessageNotificationDisabled(String.format("/fix %s", timestamp.getTimestamp()));

                anySent = true;
            }
            lastTimestamp = timestamp.getTimestamp();
        }

        if (anySent) {
            Gson gson = new Gson();
            preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS,
                    gson.toJson(new Timestamps(){{setTimestamps(getLastElementsSubList(timestamps, MAX_LAST_TIMESTAMPS));}}));

        }
    }

    public static void clearLastTimestamps() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();

        Gson gson = new Gson();

        Timestamps emptyTimestamps = new Timestamps();
        emptyTimestamps.setTimestamps(new LinkedList<>());

        preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS,
                gson.toJson(emptyTimestamps));

    }

    private static <T> List<T> getLastElementsSubList(List<T> list, int elements) {
        if (list.size() < elements) {
            return list;
        }
        return list.subList(list.size()-elements, list.size());
    }
}
