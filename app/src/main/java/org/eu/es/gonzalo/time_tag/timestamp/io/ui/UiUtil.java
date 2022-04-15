package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eu.es.gonzalo.time_tag.timestamp.R;
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

public class UiUtil {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

    public static void storeTimestamp() {
        AndroidContext androidContext = AndroidContext.getInstance();
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Optional<String> last_timestamps = preferenceRepository.getString(androidContext.getId(R.string.id_last_timestamps));
        long max_last_timestamps = preferenceRepository.getLong(androidContext.getId(R.string.id_max_last_timestamps))
                .orElse((long) AndroidContext.getInstance().getInteger(R.integer.max_last_timestamps));

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
        timestamps.setTimestamps(getLastElementsSubList(timestamps.getTimestamps(), (int) max_last_timestamps));

        preferenceRepository.setString(androidContext.getId(R.string.id_last_timestamps), gson.toJson(timestamps));

    }

    public static void sendTelegramBotMessageTimestamps(Runnable step, Runnable end) {

        AndroidContext androidContext = AndroidContext.getInstance();
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
        Gson gson = new Gson();

        Optional<String> last_timestamps = preferenceRepository.getString(androidContext.getId(R.string.id_last_timestamps));

        Timestamps timestamps;
        if (!last_timestamps.isPresent()
                || (timestamps = gson.fromJson(last_timestamps.get(), Timestamps.class)) == null
                || timestamps.getTimestamps().isEmpty()) {
            Toast.makeText(androidContext.getApplicationContext(), "INFO: No Timestamps to send", Toast.LENGTH_SHORT).show();
            end.run();
            return;
        }

        Optional<String> telegram_bot_api_token = preferenceRepository.getString(androidContext.getId(R.string.id_preference_telegram_bot_api_token));
        Optional<String> telegram_bot_user_chat_id = preferenceRepository.getString(androidContext.getId(R.string.id_telegram_bot_user_chat_id));
        if (!(telegram_bot_api_token.isPresent() && telegram_bot_user_chat_id.isPresent())) {
            Toast.makeText(androidContext.getApplicationContext(), "ERROR: token or chat id not present", Toast.LENGTH_SHORT).show();
            end.run();
            return;
        }

        long telegram_bot_delay_milliseconds = preferenceRepository.getLong(androidContext.getId(R.string.id_telegram_bot_delay_milliseconds))
                .orElse((long) androidContext.getInteger(R.integer.telegram_bot_delay_milliseconds));

        TelegramBotAPI telegramBotAPI = new TelegramBotAPI(telegram_bot_api_token.get(), telegram_bot_user_chat_id.get());
        telegramBotAPI.setContext(androidContext.getApplicationContext());

        String lastTimestamp = timestamps.getTimestamps().get(0).getTimestamp();
        Iterator<Timestamp> it = timestamps.getTimestamps().iterator();
        sendTimestamp(it, telegramBotAPI, lastTimestamp, step, end, timestamps, telegram_bot_delay_milliseconds);
    }

    private static void sendTimestamp(Iterator<Timestamp> it, TelegramBotAPI telegramBotAPI,
                                      String lastTimestamp, Runnable step, Runnable end,
                                      Timestamps timestamps, long delay) {
        if (!it.hasNext()) {
            end.run();
            return;
        }
        Timestamp timestamp = it.next();
        if (timestamp.isSent()) {
            sendTimestamp(it, telegramBotAPI, timestamp.getTimestamp(), step, end, timestamps, delay);
            return;
        }
        ZonedDateTime lastDate = ZonedDateTime.parse(lastTimestamp, format);
        ZonedDateTime currentDate = ZonedDateTime.parse(timestamp.getTimestamp(), format);
        Duration elapsed = Duration.between(lastDate, currentDate);
        String elapsedMessage = String.format("%dh %02dm", elapsed.toHours(), elapsed.minusHours(elapsed.toHours()).toMinutes());
        telegramBotAPI.sendMessageNotificationDisabled(elapsedMessage,
                () -> new Handler(Looper.getMainLooper()).postDelayed(
                        () -> telegramBotAPI.sendMessageNotificationDisabled(String.format("/fix %s", timestamp.getTimestamp()),
                                () -> {
                                    AndroidContext androidContext = AndroidContext.getInstance();
                                    PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();
                                    Gson gson = new Gson();

                                    timestamp.setSent(true);

                                    preferenceRepository.setString(androidContext.getId(R.string.id_last_timestamps),
                                            gson.toJson(timestamps));

                                    step.run();
                                    new Handler(Looper.getMainLooper()).postDelayed(
                                            () -> sendTimestamp(it, telegramBotAPI, timestamp.getTimestamp(), step, end, timestamps, delay), delay);
                                }, end),
                        delay),
                end);

    }

    public static void clearLastTimestamps() {
        AndroidContext androidContext = AndroidContext.getInstance();
        PreferenceRepository preferenceRepository = PreferenceConfiguration.getPreferenceRepository();

        Gson gson = new Gson();

        Timestamps emptyTimestamps = new Timestamps();
        emptyTimestamps.setTimestamps(new LinkedList<>());

        preferenceRepository.setString(androidContext.getId(R.string.id_last_timestamps),
                gson.toJson(emptyTimestamps));

    }

    private static <T> List<T> getLastElementsSubList(List<T> list, int elements) {
        if (list.size() < elements) {
            return list;
        }
        return list.subList(list.size() - elements, list.size());
    }
}
