package org.eu.es.gonzalo.time_tag.timestamp.io.telegram;

import org.junit.BeforeClass;
import org.junit.Test;

public class TelegramBotAPITest {

    private static TelegramBotAPI telegramBotAPI;

    @BeforeClass
    public static void setUp() {
        telegramBotAPI = new TelegramBotAPI("", "");
    }

    @Test
    public void sendMessageNotificationDisabledTest() {
        telegramBotAPI.sendMessageNotificationDisabled("TEST");
    }
}