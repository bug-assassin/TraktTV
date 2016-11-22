package com.pixis.traktTV.injection;

import android.app.Application;

import com.pixis.traktTV.repository.RemoteRepository;
import com.pixis.trakt_api.Token.TokenDatabase;
import com.pixis.trakt_api.TraktAPI;
import com.pixis.trakt_api.image_api.ImageLoading;
import com.pixis.trakt_api.services.Sync;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by Dan on 11/11/2016.
 */
@Module
public class AppModule {

    final String baseUrl;
    final String client_id;
    final Application application;

    public AppModule(Application application, String baseUrl, String client_id) {
        this.application = application;
        this.baseUrl = baseUrl;
        this.client_id = client_id;
    }

    @Provides
    public Timber.Tree providesTree() {
        return new Timber.DebugTree();
        /*if(BuildConfig.DEBUG) {
            return new Timber.DebugTree();
        }
        else {
            //TODO
            throw new RuntimeException("IMPLEMENT ME");
        }*/
    }


    @Singleton
    @Provides
    TokenDatabase providesTokenDatabase() {
        return new TokenDatabase(application);
    }


    @Singleton
    @Provides
    TraktAPI providesNetworkServiceFactory(TokenDatabase tokenDatabase) {
        return new TraktAPI(tokenDatabase);
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttp(TraktAPI traktAPI) {
        return traktAPI.createOkHttpClient(client_id, HttpLoggingInterceptor.Level.NONE)
                //.addInterceptor(new AuthenticationInterceptor())
                .build();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(TraktAPI factory, OkHttpClient okHttpClient) {
        return factory.createRetrofit(okHttpClient, baseUrl).build();
    }

    @Singleton
    @Provides
    RemoteRepository providesRemoteRepo(Sync sync, ImageLoading imageLoading) {
        return new RemoteRepository(sync, imageLoading);
    }
}
