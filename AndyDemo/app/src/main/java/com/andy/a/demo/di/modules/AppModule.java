package com.andy.a.demo.di.modules;


import com.andy.a.demo.context.AppContext;
import com.andy.a.demo.helper.ImageHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private AppContext appContext;

    public AppModule(AppContext appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    AppContext provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    ImageHelper provideImageHelper() {
        return new ImageHelper();
    }

}
