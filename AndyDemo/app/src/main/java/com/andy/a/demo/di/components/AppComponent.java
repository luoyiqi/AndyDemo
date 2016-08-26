package com.andy.a.demo.di.components;


import com.andy.a.demo.context.AppContext;
import com.andy.a.demo.context.api.ApiService;
import com.andy.a.demo.di.modules.ApiServiceModule;
import com.andy.a.demo.di.modules.AppModule;
import com.andy.a.demo.helper.ImageHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    AppContext appContext();

    ApiService apiService();

    ImageHelper imageHelper();

}
