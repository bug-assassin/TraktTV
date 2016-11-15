package com.pixis.traktTV.injection;

import com.pixis.trakt_api.services.Authentication;
import com.pixis.trakt_api.services.Sync;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Dan on 11/9/2016.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    Authentication providesAuthentication(Retrofit retrofit) {
        return retrofit.create(Authentication.class);
    }

    @Singleton
    @Provides
    Sync providesSync(Retrofit retrofit) {
        return retrofit.create(Sync.class);
    }

}
