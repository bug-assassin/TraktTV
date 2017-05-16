package com.pixis.traktTV.injection;
/*
import com.pixis.trakt_api.FanArtAPI;
import com.pixis.trakt_api.services_fanart.ImageService;
import com.pixis.trakt_api.services_trakt.Authentication;
import com.pixis.trakt_api.services_trakt.Sync;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
    ImageService providesImageLoadingAPI() {
        FanArtAPI fanArtApi = new FanArtAPI(fanArtApiKey);
        OkHttpClient okHttp = fanArtApi.createOkHttpClient().build();
        Retrofit imageRetrofit = fanArtApi.createRetrofit(okHttp).build();

        return imageRetrofit.create(ImageService.class);
    }
}
*/