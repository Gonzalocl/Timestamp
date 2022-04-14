package org.eu.es.gonzalo.time_tag.timestamp.app.repository;

import java.util.Optional;

public interface PreferenceRepository {

    Optional<String> getString(String preference);
    Optional<Long> getLong(String preference);
    void setString(String preference, String value);

    class Preference {
        // TODO should use common reference
        public static final String TELEGRAM_BOT_API_TOKEN = "telegram_bot_api_token";
        public static final String TELEGRAM_BOT_USER_CHAT_ID = "telegram_bot_user_chat_id";
        public static final String TELEGRAM_BOT_DELAY_MILLISECONDS = "telegram_bot_delay_milliseconds";
        public static final String LAST_TIMESTAMPS = "last_timestamps";
        public static final String MAX_LAST_TIMESTAMPS = "max_last_timestamps";
    }
}
