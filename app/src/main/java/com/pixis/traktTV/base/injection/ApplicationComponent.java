package com.pixis.traktTV.base.injection;

import com.pixis.traktTV.base.BaseApplication;
import com.pixis.traktTV.screen_main.MoviesFragment;
import com.pixis.traktTV.screen_main.ShowsFragment;
import com.pixis.traktTV.screen_movies_detail.MovieDetailViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { NetworkModule.class })
public interface ApplicationComponent {
    void inject(@NotNull BaseApplication baseApplication);
    void inject(@NotNull ShowsFragment showsFragment);
    void inject(@NotNull MoviesFragment moviesFragment);
    void inject(@NotNull MovieDetailViewModel movieDetailViewModel);
}
