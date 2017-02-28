package com.pixis.traktTV.base;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.pixis.traktTV.injection.AppModule;
import com.pixis.traktTV.injection.ApplicationComponent;
import com.pixis.traktTV.injection.DaggerApplicationComponent;
import com.pixis.traktTV.injection.NetworkModule;
import com.pixis.trakt_api.realmmodule.TraktRealmLibraryModule;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.functions.Action1;
import rx.plugins.RxJavaHooks;
import timber.log.Timber;

public class BaseApplication extends Application {
    private ApplicationComponent daggerApplicationComponent;

    @Inject
    Timber.Tree tree;

    @Override
    public void onCreate() {
        super.onCreate();

        Bundle manifestBundle = getManifestBundle();
        String client_id = manifestBundle.getString("TRAKT_CLIENT_ID");
        String api_target = manifestBundle.getString("TRAKT_API_TARGET");
        String fanart_api_key = manifestBundle.getString("FAN_ART_API_KEY");

        //Injection
        daggerApplicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this, api_target, client_id))
                .networkModule(new NetworkModule(fanart_api_key))
                .build();
        daggerApplicationComponent.inject(this);

        //Timber
        Timber.plant(tree);

        //Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("trakt_data.realm")
                .modules(Realm.getDefaultModule(), new TraktRealmLibraryModule())
                .deleteRealmIfMigrationNeeded()//TODO proper migration
                .build();
        Realm.setDefaultConfiguration(config);

        //RxJava Error Logging
        RxJavaHooks.setOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Timber.e(throwable);
            }
        });
    }

    public ApplicationComponent getComponent() {
        return daggerApplicationComponent;
    }

    public Bundle getManifestBundle() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
