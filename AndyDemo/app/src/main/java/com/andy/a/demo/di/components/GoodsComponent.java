package com.andy.a.demo.di.components;


import com.andy.a.demo.di.modules.GoodsModule;
import com.andy.a.demo.ui.DaggerTestActivity;

import cn.bestkeep.android.internal.di.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,
        modules = GoodsModule.class)
public interface GoodsComponent {

    void inject(DaggerTestActivity activity);
}
