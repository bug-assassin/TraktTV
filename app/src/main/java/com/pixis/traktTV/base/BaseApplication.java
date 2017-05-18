package com.pixis.traktTV.base;

import android.app.Application;

import com.pixis.traktTV.base.injection.ApplicationComponent;
import com.pixis.traktTV.base.injection.DaggerApplicationComponent;
import com.pixis.traktTV.base.injection.NetworkModule;

import javax.inject.Inject;

import timber.log.Timber;

public class BaseApplication extends Application {
    private ApplicationComponent daggerApplicationComponent;

    /*@Inject
    Timber.Tree tree;*/

    @Override
    public void onCreate() {
        super.onCreate();

        //Injection
        daggerApplicationComponent = DaggerApplicationComponent
                .builder()
                .networkModule(new NetworkModule(this))
                .build();
        daggerApplicationComponent.inject(this);


        //Timber
        //Timber.plant(tree);

        //Realm
        /*Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("trakt_data.realm")
                .modules(Realm.getDefaultModule(), new TraktRealmLibraryModule())
                .deleteRealmIfMigrationNeeded()//TODO proper migration
                .build();
        Realm.setDefaultConfiguration(config);
        */
        //RxJava Error Logging
        //TODO
    }

    public ApplicationComponent getComponent() {
        return daggerApplicationComponent;
    }


}