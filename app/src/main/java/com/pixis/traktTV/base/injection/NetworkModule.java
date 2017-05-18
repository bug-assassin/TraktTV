package com.pixis.traktTV.base.injection;

import android.app.Application;

import com.pixis.traktTV.base.Repository;
import com.pixis.trakt_api.BuildConfig;
import com.pixis.trakt_api.FanArtAPI;
import com.pixis.trakt_api.RestAPI;
import com.pixis.trakt_api.TraktAPI;
import com.pixis.trakt_api.services_fanart.ImageService;
import com.pixis.trakt_api.services_trakt.MovieService;
import com.pixis.trakt_api.services_trakt.ShowService;
import com.pixis.trakt_api.utils.Token.TokenDatabase;
import com.pixis.trakt_api.utils.Token.TokenStorage;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    /*@Singleton
    @Provides
    Authentication providesAuthentication(Retrofit retrofit) {
        return retrofit.create(Authentication.class);
    }*/

    private Application application;
    public NetworkModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    @Named("FanArt")
    OkHttpClient providesFanArtOkHttp() {
        return FanArtAPI.INSTANCE.createOkHttpClient(BuildConfig.fanart_ApiKey).build();
    }

    @Singleton
    @Provides
    @Named("FanArt")
    Retrofit providesFanArtRetrofit(@Named("FanArt") OkHttpClient okHttpClient) {
        return RestAPI.INSTANCE.createRetrofit(BuildConfig.fanart_baseURL, okHttpClient);
    }

    @Singleton
    @Provides
    ImageService providesImageLoadingAPI(@Named("FanArt") Retrofit retrofit) {
        return retrofit.create(ImageService.class);
    }

    @Singleton
    @Provides
    TokenStorage providesTokenDatabase() {
        return new TokenDatabase(application);
    }

    @Singleton
    @Provides
    @Named("Trakt")
    OkHttpClient providesOkHttp(TokenStorage tokenStorage) {
        return TraktAPI.INSTANCE.createOkHttpClient(BuildConfig.trakt_client_id, tokenStorage).build();
    }

    @Singleton
    @Provides
    @Named("Trakt")
    Retrofit providesRetrofit(@Named("Trakt") OkHttpClient okHttpClient) {
        return RestAPI.INSTANCE.createRetrofit(BuildConfig.trakt_baseURL, okHttpClient);
    }

    @Singleton
    @Provides
    Repository providesRepository(MovieService movieService, ShowService showService, ImageService imageService) {
        return new Repository(movieService, showService, imageService);
    }

    @Singleton
    @Provides
    MovieService providesMovieService(@Named("Trakt") Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Singleton
    @Provides
    ShowService providesShowService(@Named("Trakt") Retrofit retrofit) {
        return retrofit.create(ShowService.class);
    }
}