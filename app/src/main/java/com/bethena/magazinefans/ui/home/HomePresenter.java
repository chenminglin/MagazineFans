package com.bethena.magazinefans.ui.home;

import android.util.Log;

import com.bethena.magazinefans.BuildConfig;
import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.data.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void loadData() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder.build();


        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(BuildConfig.BASE_URL);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Gson gson = new Gson();
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = retrofitBuilder.build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<DataWrapper<Banner>> observable = apiService.getBanner("3", "0");
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DataWrapper<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataWrapper<Banner> bannerDataWrapper) {
                        Timber.tag(TAG).d("banner size = %d", bannerDataWrapper.focus.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.tag(TAG).e(e);
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("oncomplete....");
                    }
                });


    }
}
