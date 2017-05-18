package com.pixis.traktTV.base.injection;

import com.pixis.traktTV.base.BaseApplication;
import com.pixis.traktTV.screen_main.ShowsFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { NetworkModule.class })
public interface ApplicationComponent {
    void inject(@NotNull BaseApplication baseApplication);
    void inject(@NotNull ShowsFragment showsFragment);
}
