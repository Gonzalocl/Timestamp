package org.eu.es.gonzalo.time_tag.timestamp.io.telegram;

public class TelegramBotAPI {

    private static final String TELEGRAM_BOT_API_URL = "https://api.telegram.org/bot";

    private final String BOT_API_TOKEN;
    private final String BOT_USER_CHAT_ID;

    public TelegramBotAPI(String botAPIToken, String botUserChatId) {
        BOT_API_TOKEN = botAPIToken;
        BOT_USER_CHAT_ID = botUserChatId;
    }

    public void sendMessageNotificationDisabled(String message) {
    }
}
