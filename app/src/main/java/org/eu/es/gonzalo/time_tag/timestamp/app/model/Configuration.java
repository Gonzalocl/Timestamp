package org.eu.es.gonzalo.time_tag.timestamp.app.model;

public class Configuration {

    public static final String BOT_API_TOKEN_SETTING_ID = "BOT_API_TOKEN";
    public static final String BOT_USER_CHAT_ID_SETTING_ID = "BOT_USER_CHAT_ID";

    private String botApiToken;
    private String botUserChatId;

    public String getBotApiToken() {
        return botApiToken;
    }

    public void setBotApiToken(String botApiToken) {
        this.botApiToken = botApiToken;
    }

    public String getBotUserChatId() {
        return botUserChatId;
    }

    public void setBotUserChatId(String botUserChatId) {
        this.botUserChatId = botUserChatId;
    }
}
