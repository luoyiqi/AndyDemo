package com.andy.a.demo.di.modules;


import android.os.Build;

import com.andy.a.demo.BuildConfig;
import com.andy.a.demo.context.api.ApiService;
import com.andy.a.demo.context.config.BaseHttpURL;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {

    private static final String URL = BaseHttpURL.BESTKEEP_BASE_URL;

    private final HashMap<HttpUrl, List<Cookie>> cookieStore;

    public ApiServiceModule() {
        cookieStore = new HashMap<>();
    }

    @Provides
    @Singleton
    String provideUrl() {
        return URL;
    }

    @Provides
    @Singleton
    HashMap<HttpUrl, List<Cookie>> provideCookieStore() {
        return cookieStore;
    }


    private static final String HTTP_ACCEPT = "text/html;charset=UTF-8,application/json";
    private static final String HTTP_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final int HTTP_REQUEST_TIMEOUT = 20000;
    private static final int HTTP_MAX_CONNECTIONS = 30;
    private static final String HTTP_CONNECTION = "close";

    private String getUserAgent() {
        String httpAgent = System.getProperty("http.agent", Build.FINGERPRINT);
        final int indexOf = httpAgent.indexOf(" ");
        if (indexOf != -1) {
            httpAgent = httpAgent.substring(indexOf);
        }
        String userAgent = "BESTKEEP" + File.separator + BuildConfig.VERSION_NAME + httpAgent;
        return userAgent;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", HTTP_ACCEPT)
                    .addHeader("Content-Type", HTTP_CONTENT_TYPE)
                    .addHeader("Connection", HTTP_CONNECTION)
                    .addHeader("User-Agent", getUserAgent())
                    .addHeader("cas-client-service", chain.request().url().toString())
                    .build();

            long t1 = System.nanoTime();
            LogUtil.i(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            LogUtil.i(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        };
    }

    @Provides
    @Singleton
    CookieJar provideCookieJar(final HashMap<HttpUrl, List<Cookie>> cookieStore) {
        return new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(getHttpUrl(url), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(getHttpUrl(url));
                return cookies != null ? cookies : new ArrayList<>();
            }

            private HttpUrl getHttpUrl(HttpUrl url) {
                return HttpUrl.parse(url.host());
            }
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor,
                                     CookieJar cookieJar) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cookieJar(cookieJar)
                .build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String baseUrl,
                             OkHttpClient client,
                             GsonConverterFactory converterFactory,
                             RxJavaCallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
