package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.eu.es.gonzalo.time_tag.timestamp.R;

public class UiUtil {

    public static void setErrorView(Activity activity, String message) {

        activity.setContentView(R.layout.error_view);

        TextView errorTextView = activity.findViewById(R.id.error_view_error_text_view);
        errorTextView.setText(message);

        Button closeButton = activity.findViewById(R.id.error_view_close_button);
        closeButton.setOnClickListener(view -> activity.finish());

    }
}
