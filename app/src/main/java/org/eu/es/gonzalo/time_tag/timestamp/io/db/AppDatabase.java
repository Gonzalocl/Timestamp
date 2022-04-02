package org.eu.es.gonzalo.time_tag.timestamp.io.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.eu.es.gonzalo.time_tag.timestamp.io.db.dao.SettingDao;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.entity.Setting;

@Database(entities = {Setting.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SettingDao settingDao();
}
