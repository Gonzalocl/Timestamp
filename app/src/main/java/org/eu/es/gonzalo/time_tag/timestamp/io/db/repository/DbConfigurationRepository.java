package org.eu.es.gonzalo.time_tag.timestamp.io.db.repository;

import org.eu.es.gonzalo.time_tag.timestamp.app.model.Configuration;
import org.eu.es.gonzalo.time_tag.timestamp.app.repository.ConfigurationRepository;
import org.eu.es.gonzalo.time_tag.timestamp.io.context.DatabaseContext;
import org.eu.es.gonzalo.time_tag.timestamp.io.db.entity.Setting;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DbConfigurationRepository implements ConfigurationRepository {

    @Override
    public void save(Configuration configuration) {

    }

    @Override
    public void get(Consumer<Configuration> success, Consumer<Throwable> error) {
        DatabaseContext.getInstance()
                .getAppDatabase()
                .settingDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        settings -> success.accept(new Configuration(settings.stream().collect(Collectors.toMap(Setting::getSetting, Setting::getValue)))),
                        error::accept
                );
    }
}
