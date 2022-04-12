package org.eu.es.gonzalo.time_tag.timestamp.io.telegram;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;

public class TelegramBotAPI {

    private static final String TELEGRAM_BOT_API_URL = "https://api.telegram.org/bot";
    private static final String TELEGRAM_BOT_API_URL_FORMAT = "%s%s/%s";
    private static final String BOT_API_METHOD_SEND_MESSAGE = "sendMessage";

    private final String BOT_API_TOKEN;
    private final String BOT_USER_CHAT_ID;

    private final String BOT_API_URL_SEND_MESSAGE;

    private Context context;
    private RequestQueue requestQueue;

    public TelegramBotAPI(String botAPIToken, String botUserChatId) {
        BOT_API_TOKEN = botAPIToken;
        BOT_USER_CHAT_ID = botUserChatId;

        BOT_API_URL_SEND_MESSAGE = String.format(TELEGRAM_BOT_API_URL_FORMAT,
                TELEGRAM_BOT_API_URL,
                BOT_API_TOKEN,
                BOT_API_METHOD_SEND_MESSAGE);
    }

    public void sendMessageNotificationDisabled(String message, Consumer<Void> successCallBack, Consumer<Void> errorCallBack) {
        JSONObject jsonObject = new JSONObject() {{
            try {
                put("chat_id", BOT_USER_CHAT_ID);
                put("text", message);
                put("disable_notification", true);
            } catch (JSONException e) {
                Toast.makeText(context, "JSON ERROR", Toast.LENGTH_SHORT).show();
            }
        }};
        requestQueue.add(new JsonObjectRequest(Request.Method.POST,
                BOT_API_URL_SEND_MESSAGE,
                jsonObject,
                response -> successCallBack.accept(null),
                error -> {
                    Toast.makeText(context, "ERROR: telegram bot api call", Toast.LENGTH_SHORT).show();
                    errorCallBack.accept(null);
                }));

    }

    public void setContext(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
}
