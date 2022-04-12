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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class UiUtil {

    private static final String DEFAULT_MAX_LAST_TIMESTAMPS_STRING = "25";
    private static final int DEFAULT_MAX_LAST_TIMESTAMPS_INT = 25;
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

    public static void storeTimestamp() {
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Optional<String> last_timestamps = preferenceRepository.get(PreferenceRepository.Preference.LAST_TIMESTAMPS);
        Optional<String> max_last_timestamps_string = preferenceRepository.get(PreferenceRepository.Preference.MAX_LAST_TIMESTAMPS);
        int max_last_timestamps;
        try {
            max_last_timestamps = Integer.parseInt(max_last_timestamps_string.orElse(DEFAULT_MAX_LAST_TIMESTAMPS_STRING));
        } catch (NumberFormatException e) {
            Toast.makeText(AndroidContext.getInstance().getApplicationContext(), "ERROR: wrong MAX_LAST_TIMESTAMPS, using default " + DEFAULT_MAX_LAST_TIMESTAMPS_INT, Toast.LENGTH_SHORT).show();
            max_last_timestamps = DEFAULT_MAX_LAST_TIMESTAMPS_INT;
        }

        Gson gson = new Gson();
        Timestamps timestamps;
        if (!last_timestamps.isPresent() || (timestamps = gson.fromJson(last_timestamps.get(), Timestamps.class)) == null) {
            timestamps = new Timestamps();
            timestamps.setTimestamps(new LinkedList<>());
        }

        Timestamp timestamp = new Timestamp();
        timestamp.setTimestamp(ZonedDateTime.now().format(format));
        timestamp.setSent(false);

        timestamps.getTimestamps().add(timestamp);
        timestamps.setTimestamps(getLastElementsSubList(timestamps.getTimestamps(), max_last_timestamps));

        preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS, gson.toJson(timestamps));

    }

    public static void sendTelegramBotMessageTimestamps(Consumer<Void> step, Consumer<Void> end) {

        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Gson gson = new Gson();

        Optional<String> last_timestamps = preferenceRepository.get(PreferenceRepository.Preference.LAST_TIMESTAMPS);

        Timestamps timestamps;
        if (!last_timestamps.isPresent() || (timestamps = gson.fromJson(last_timestamps.get(), Timestamps.class)) == null || timestamps.getTimestamps().isEmpty()) {
            Toast.makeText(AndroidContext.getInstance().getApplicationContext(), "INFO: No Timestamps to send", Toast.LENGTH_SHORT).show();
            return;
        }

        Optional<String> telegram_bot_api_token = preferenceRepository.get(PreferenceRepository.Preference.TELEGRAM_BOT_API_TOKEN);
        Optional<String> telegram_bot_user_chat_id = preferenceRepository.get(PreferenceRepository.Preference.TELEGRAM_BOT_USER_CHAT_ID);
        if (!(telegram_bot_api_token.isPresent() && telegram_bot_user_chat_id.isPresent())) {
            Toast.makeText(AndroidContext.getInstance().getApplicationContext(), "ERROR: token or chat id not present", Toast.LENGTH_SHORT).show();
            return;
        }

        TelegramBotAPI telegramBotAPI = new TelegramBotAPI(telegram_bot_api_token.get(), telegram_bot_user_chat_id.get());
        telegramBotAPI.setContext(AndroidContext.getInstance().getApplicationContext());

        String lastTimestamp = timestamps.getTimestamps().get(0).getTimestamp();
        Iterator<Timestamp> it = timestamps.getTimestamps().iterator();
        sendTimestamp(it, telegramBotAPI, lastTimestamp, step, end, timestamps);
    }

    private static void sendTimestamp(Iterator<Timestamp> it, TelegramBotAPI telegramBotAPI, String lastTimestamp, Consumer<Void> step, Consumer<Void> end, Timestamps timestamps) {
        if (!it.hasNext()) {
            end.accept(null);
        }
        Timestamp timestamp = it.next();
        if (timestamp.isSent()) {
            sendTimestamp(it, telegramBotAPI, timestamp.getTimestamp(), step, end, timestamps);
            return;
        }
        ZonedDateTime lastDate = ZonedDateTime.parse(lastTimestamp, format);
        ZonedDateTime currentDate = ZonedDateTime.parse(timestamp.getTimestamp(), format);
        telegramBotAPI.sendMessageNotificationDisabled(String.format("Elapsed: %s", Duration.between(lastDate, currentDate)), unused -> {
            telegramBotAPI.sendMessageNotificationDisabled(String.format("/fix %s", timestamp.getTimestamp()), unused1 -> {
                PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
                Gson gson = new Gson();

                timestamp.setSent(true);

                preferenceRepository.set(PreferenceRepository.Preference.LAST_TIMESTAMPS,
                        gson.toJson(timestamps));

                step.accept(null);
                sendTimestamp(it, telegramBotAPI, timestamp.getTimestamp(), step, end, timestamps);
            }, end);
        }, end);

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
