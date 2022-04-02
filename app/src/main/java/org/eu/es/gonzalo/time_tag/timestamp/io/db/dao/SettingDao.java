package org.eu.es.gonzalo.time_tag.timestamp.io.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.eu.es.gonzalo.time_tag.timestamp.io.db.entity.Setting;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SettingDao {

    @Query("SELECT * FROM setting")
    public Single<List<Setting>> getAll();

    @Insert
    public Completable insertAll(Setting... settings);
}
