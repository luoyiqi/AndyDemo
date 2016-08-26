package com.andy.a.demo.context;


import android.content.Context;
import android.graphics.Bitmap;

import com.andy.a.demo.di.components.AppComponent;
import com.andy.a.demo.di.components.DaggerAppComponent;
import com.andy.a.demo.di.modules.ApiServiceModule;
import com.andy.a.demo.di.modules.AppModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import cn.bestkeep.android.base.DMApplication;


public class AppContext extends DMApplication {

    private AppComponent appComponent;

    public static AppContext from(Context context) {
        return (AppContext) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    @Override
    protected void asyncInit(Context context) {
        super.asyncInit(context);
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(context)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .build();
        Fresco.initialize(context, imagePipelineConfig);
        initializeInjector();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
