package com.pixis.traktTV.injection;

import com.pixis.trakt_api.FanArtAPI;
import com.pixis.trakt_api.image_api.ImageLoading;
import com.pixis.trakt_api.services.Authentication;
import com.pixis.trakt_api.services.ServiceCalendars;
import com.pixis.trakt_api.services.Sync;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
    final String fanArtApiKey;

    public NetworkModule(String fanArtApiKey) {
        this.fanArtApiKey = fanArtApiKey;
    }

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

    @Singleton
    @Provides
    ServiceCalendars providesCalendars(Retrofit retrofit) {
        return retrofit.create(ServiceCalendars.class);
    }

    @Singleton
    @Provides
    ImageLoading providesImageLoadingAPI() {
        FanArtAPI fanArtApi = new FanArtAPI(fanArtApiKey);
        OkHttpClient okHttp = fanArtApi.createOkHttpClient(HttpLoggingInterceptor.Level.NONE).build();
        Retrofit imageRetrofit = fanArtApi.createRetrofit(okHttp).build();

        return imageRetrofit.create(ImageLoading.class);
    }
}
