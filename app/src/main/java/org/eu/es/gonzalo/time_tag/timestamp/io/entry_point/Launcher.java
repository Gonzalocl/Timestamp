package org.eu.es.gonzalo.time_tag.timestamp.io.entry_point;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.room.Room;

import org.eu.es.gonzalo.time_tag.timestamp.R;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.ContextInitializer;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.AppDatabase;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.dao.SettingDao;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.entity.Setting;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Launcher extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextInitializer.initializeAllContexts(this);
        setContentView(R.layout.main_view);

        TextView textView = findViewById(R.id.mainTextView);
        textView.setText("Hola");

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        SettingDao settingDao = db.settingDao();

        try {
            Setting setting = new Setting();
            setting.setting = "aa";
            setting.setting = "cc";
//            settingDao.insertAll(setting)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            () -> textView.setText("OKOKOKOK"),
//                            throwable -> textView.setText(throwable.getMessage())
//                    );

            settingDao.getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            settings -> textView.setText(settings.get(0).setting),
                            throwable -> textView.setText(throwable.getMessage())
                    );

        } catch (Throwable t) {
            textView.setText(t.getMessage());
        }
    }
}
