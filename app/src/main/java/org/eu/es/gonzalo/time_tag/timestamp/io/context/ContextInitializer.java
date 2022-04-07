package org.eu.es.gonzalo.time_tag.timestamp.io.context;

import android.content.Context;

public class ContextInitializer {

    public static void initializeAllContexts(Context context) {
        AndroidContext.initialize(context);
        SharedPreferencesContext.initialize(context);
        DatabaseContext.initialize(context);
    }
}
