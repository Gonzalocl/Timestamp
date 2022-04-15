package org.eu.es.gonzalo.time_tag.timestamp.io.context;

import android.content.Context;
import android.content.res.Resources;

import org.eu.es.gonzalo.time_tag.timestamp.io.context.exception.AndroidContextNotInitializedException;

public class AndroidContext {

    private final Context applicationContext;

    private static AndroidContext androidContext;

    public static synchronized void initialize(Context context) {
        if (androidContext == null) {
            androidContext = new AndroidContext(context);
        }
    }

    private AndroidContext(Context context) {
        applicationContext = context.getApplicationContext();
    }

    public static AndroidContext getInstance() {
        if (androidContext == null) {
            throw new AndroidContextNotInitializedException();
        }
        return androidContext;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public Resources getResources() {
        return getApplicationContext().getResources();
    }

    public int getInteger(int id) {
        return getResources().getInteger(id);
    }

    public String getString(int id) {
        return getResources().getString(id);
    }

    public String getId(int id) {
        return getString(id);
    }
}
