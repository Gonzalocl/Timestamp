package org.eu.es.gonzalo.time_tag.timestamp.io.context;

import android.content.Context;

import androidx.room.Room;

import org.eu.es.gonzalo.time_tag.timestamp.io.context.exception.DatabaseContextNotInitializedException;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.AppDatabase;

public class DatabaseContext {

    private static final String DATABASE_NAME = "timestamp-database";

    private final AppDatabase appDatabase;

    private static DatabaseContext databaseContext;

    public static synchronized void initialize(Context context) {
        if (databaseContext == null) {
            databaseContext = new DatabaseContext(context);
        }
    }

    private DatabaseContext(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                DATABASE_NAME).build();
    }

    public static DatabaseContext getInstance() {
        if (databaseContext == null) {
            throw new DatabaseContextNotInitializedException();
        }
        return databaseContext;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
