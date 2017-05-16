package com.pixis.traktTV.injection;

import android.app.Application;

import com.pixis.traktTV.repository.RemoteRepository;
import com.pixis.trakt_api.utils.Token.TokenDatabase;
import com.pixis.trakt_api.TraktAPI;
import com.pixis.trakt_api.services_fanart.ImageService;
import com.pixis.trakt_api.services_trakt.Sync;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import timber.log.Timber;

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
        return traktAPI.createOkHttpClient(client_id)
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
    RemoteRepository providesRemoteRepo(Sync sync, ServiceCalendars serviceCalendars, ImageService imageService) {
        return new RemoteRepository(sync, serviceCalendars, imageService);
    }
}
