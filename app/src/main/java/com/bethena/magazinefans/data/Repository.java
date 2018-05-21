package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.Category;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.bean.MagazineDetail;

import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class Repository extends BaseRepository {

    ApiService mApiService;

    public Repository(ApiService apiService) {
        mApiService = apiService;
    }


    public Flowable<List<Banner>> getBanner() {
        return mApiService.getBanner(3, 0)
                .compose(this.<Banner>transResult());
    }

    public Flowable<List<MagazineConcept>> getHomeList(int page) {
        return mApiService.getHomeList(30, page)
                .compose(this.<MagazineConcept>transResult());
    }

    public Flowable<List<Category>> getCategory() {
        return mApiService.getCategory()
                .compose(this.<Category>transResult());
    }

    public Flowable<List<MagazineConcept>> getMagazinesByCate(int size,
                                                                int page,
                                                                int cate,
                                                                @Nullable Integer brand) {
        return mApiService.getMagazinesByCate(size, page, cate, brand)
                .compose(this.<MagazineConcept>transResult());
    }

    public Flowable<List<MagazineConcept>> getMagzinesByLetter(int size,
                                                               int page,
                                                               String letter) {
        return mApiService.getMagzinesByLetter(size, page, letter)
                .compose(this.<MagazineConcept>transResult());
    }


    public Flowable<List<MagazineDetail>> getMagzine(int magaId) {
        return mApiService.getMagzine(magaId)
                .compose(this.<MagazineDetail>transResult());
    }
}
