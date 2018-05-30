package com.bethena.magazinefans.di;

import android.content.Context;
import android.os.Environment;

import com.bethena.magazinefans.BuildConfig;
import com.bethena.magazinefans.data.ApiService;
import com.bethena.magazinefans.data.Repository;
import com.bethena.magazinefans.data.RxCacheProviders;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder httpBuilder) {
        return httpBuilder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(BuildConfig.BASE_URL);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        return retrofitBuilder;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiService apiService, RxCacheProviders rxCacheProviders) {
        return new Repository(apiService, rxCacheProviders);
    }

    @Provides
    @Singleton
    RxCacheProviders provideRxCacheProviders(Context context) {
        File cacheDir = context.getCacheDir();
        RxCacheProviders providers = new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())//配置缓存的文件存放路径，以及数据的序列化和反序列化
                .using(RxCacheProviders.class);
        return providers;
    }
}
