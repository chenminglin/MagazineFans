package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.Category;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.bean.MagazineDetail;

import javax.annotation.Nullable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("index.php")
    Flowable<DataWrapper<Banner>> getBanner(@Query("s") int size, @Query("p") int page);

    @POST("index.php")
    Observable<DataWrapper<Banner>> getBannerObs(@Query("s") int size, @Query("p") int page);

    @POST("index.php")
    Flowable<DataWrapper<MagazineConcept>> getHomeList(@Query("s") int size, @Query("p") int page);

    @POST("cate.php")
    Flowable<DataWrapper<Category>> getCategory();

    @POST("lists.php")
    Flowable<DataWrapper<MagazineConcept>> getMagazinesByCate(
            @Query("s") int size,
            @Query("p") int page,
            @Query("c") int cate,
            @Nullable @Query("m") Integer brand);

    @POST("recommend.php")
    Flowable<DataWrapper<MagazineConcept>> getMagzinesByLetter(
            @Query("s") int size,
            @Query("p") int page,
            @Query("l") String letter);

    @POST("show.php")
    Flowable<DataWrapper<MagazineDetail>> getMagzine(@Query("a") int magaId);
}
