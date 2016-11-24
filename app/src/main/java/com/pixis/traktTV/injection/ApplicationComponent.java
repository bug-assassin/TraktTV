package com.pixis.traktTV.injection;

import com.pixis.traktTV.screen_login.LoginActivity;
import com.pixis.traktTV.screen_main.MainActivity;
import com.pixis.traktTV.base.BaseApplication;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dan on 11/9/2016.
 */
@Singleton
@Component(modules = { AppModule.class, NetworkModule.class })
public interface ApplicationComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity loginActivity);

    void inject(@NotNull BaseApplication baseApplication);
}
