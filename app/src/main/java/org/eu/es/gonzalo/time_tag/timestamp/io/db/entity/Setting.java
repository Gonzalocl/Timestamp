package org.eu.es.gonzalo.time_tag.timestamp.io.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Setting {

    @PrimaryKey
    @NonNull
    private String setting;
    private String value;

    public Setting(@NonNull String setting, String value) {
        this.setting = setting;
        this.value = value;
    }

    @NonNull
    public String getSetting() {
        return setting;
    }

    public void setSetting(@NonNull String setting) {
        this.setting = setting;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
