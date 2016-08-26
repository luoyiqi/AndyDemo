package com.andy.a.demo.di.modules;

import com.andy.a.demo.context.api.ApiService;
import com.andy.a.demo.presenter.GoodsPresenter;
import com.andy.a.demo.respository.impl.GoodsRepository;

import cn.bestkeep.android.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class GoodsModule {

    @PerActivity
    @Provides
    GoodsRepository provideRepository(ApiService apiService) {
        return new GoodsRepository(apiService);
    }


    @PerActivity
    @Provides
    GoodsPresenter providePresenter(GoodsRepository repository) {
        return new GoodsPresenter(repository);
    }
}
