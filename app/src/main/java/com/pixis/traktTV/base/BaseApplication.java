package com.pixis.traktTV.base;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.pixis.traktTV.injection.AppModule;
import com.pixis.traktTV.injection.ApplicationComponent;
import com.pixis.traktTV.injection.DaggerApplicationComponent;
import com.pixis.traktTV.injection.NetworkModule;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Dan on 11/9/2016.
 */

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

        daggerApplicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this, api_target, client_id))
                .networkModule(new NetworkModule())
                .build();
        daggerApplicationComponent.inject(this);

        Timber.plant(tree);
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

    @NotNull
    public static String getAccessToken() {
        return null;
    }
}
