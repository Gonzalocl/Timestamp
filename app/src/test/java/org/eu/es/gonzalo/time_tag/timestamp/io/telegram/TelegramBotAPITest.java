package org.eu.es.gonzalo.time_tag.timestamp.io.telegram;

import android.app.Activity;
import android.content.Context;

import org.junit.BeforeClass;
import org.junit.Test;

public class TelegramBotAPITest {

    private static final String BOT_API_TOKEN = "";
    private static final String BOT_USER_CHAT_ID = "";

    private static TelegramBotAPI telegramBotAPI;
    private static Context context;

    @BeforeClass
    public static void setUp() {
        context = new Activity();
        telegramBotAPI = new TelegramBotAPI(BOT_API_TOKEN, BOT_USER_CHAT_ID);
        telegramBotAPI.setContext(context);
    }

    @Test
    public void sendMessageNotificationDisabledTest() {
        telegramBotAPI.sendMessageNotificationDisabled("TEST");
    }
}