package org.eu.es.gonzalo.time_tag.timestamp.io.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import org.eu.es.gonzalo.time_tag.timestamp.io.db.entity.Setting;

@Dao
public interface SettingDao {

    @Query("SELECT * FROM setting WHERE setting=:setting")
    Setting findBySetting(String setting);
}
