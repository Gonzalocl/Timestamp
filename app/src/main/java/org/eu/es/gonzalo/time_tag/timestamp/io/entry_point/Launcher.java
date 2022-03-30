package org.eu.es.gonzalo.time_tag.timestamp.io.entry_point;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eu.es.gonzalo.time_tag.timestamp.R;

public class Launcher extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        TextView textView = findViewById(R.id.mainTextView);
        textView.setText("Hello baby");
        try {
//            String url = String.format("http://192.168.1.11:5555/%s", "message");
            String url = "https://www.google.com";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.GET,
                    url,
                    response -> textView.setText("Response is: " + response.substring(0, 500)),
                    error -> textView.setText("That didn't work!")));
        } catch (Throwable t) {
            textView.setText("e");
        }

    }
}
