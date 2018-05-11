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

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Gson gson = new Gson();
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = retrofitBuilder.build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<DataWrapper<Banner>> call = apiService.getBanner("3","0");
        call.enqueue(new Callback<DataWrapper<Banner>>() {
            @Override
            public void onResponse(Call<DataWrapper<Banner>> call, Response<DataWrapper<Banner>> response) {
                Log.d(TAG,"message = "+response.message());
                Log.d(TAG,"message = "+response.body().focus.size());

            }

            @Override
            public void onFailure(Call<DataWrapper<Banner>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
