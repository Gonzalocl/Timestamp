package org.eu.es.gonzalo.time_tag.timestamp.io;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eu.es.gonzalo.time_tag.timestamp.R;

import java.util.Random;

public class AndroidContext {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AndroidContext.context = context;
    }

    public static void sendReq(Context ctx, String message) {
        String url = String.format("http://192.168.1.11:8000/%s", message);
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(new StringRequest(Request.Method.GET, url, response -> {
        }, error -> {
        }));
    }

    private static final String CHANNEL_ID = "C_ID";
    private static final String CHANNEL_NAME = "C_NAME";
    private static final String CHANNEL_DESCRIPTION = "C_DESCRIPTION";
    private static final int NOTIFICATION_ID = 1234;

    public static void sendNotification(Context context, String text) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(text)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(new Random().nextInt(100000), builder.build());
    }

}
