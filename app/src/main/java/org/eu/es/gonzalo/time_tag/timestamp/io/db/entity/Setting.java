package org.eu.es.gonzalo.time_tag.timestamp.io.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Setting {

    @PrimaryKey
    private String setting;
    private String value;

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
