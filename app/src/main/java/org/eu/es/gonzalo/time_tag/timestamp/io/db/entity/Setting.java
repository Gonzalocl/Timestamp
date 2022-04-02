package org.eu.es.gonzalo.time_tag.timestamp.io.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Setting {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "setting")
    public String setting;

    @ColumnInfo(name = "value")
    public String value;
}
