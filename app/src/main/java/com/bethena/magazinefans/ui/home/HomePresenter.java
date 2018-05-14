package com.bethena.magazinefans.ui.home;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.HomeData;
import com.bethena.magazinefans.core.BaseObserver;
import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.data.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    Repository mRepository;


    List<Banner> mBanners;
    List<HomeData> mHomeDatas;

    int mPage = 1;


    @Inject
    public HomePresenter(HomeContract.View view, Repository repository) {
        super(view);
        mRepository = repository;

        mBanners = new ArrayList<>();
        mHomeDatas = new ArrayList<>();
    }




    @Override
    public void loadBanner() {

    }

    @Override
    public void loadList() {
        mRepository.getHomeList(mPage)
                .compose(this.<DataWrapper<HomeData>>applySchedulers())
                .subscribe(new BaseObserver<DataWrapper<HomeData>>(mView, this) {

                    @Override
                    public void onNext(DataWrapper<HomeData> homeDataDataWrapper) {
                        if (mPage == 1) {
                            mHomeDatas.clear();
                            mHomeDatas.addAll(homeDataDataWrapper.data);
                        }


                        if (homeDataDataWrapper.data.size() == 0 && mHomeDatas.size() == 0) {
                            mView.onEmpty();
                            return;
                        } else if (homeDataDataWrapper.data.size() == 0 && mHomeDatas.size() != 0) {
                            mView.onLoadNoMore();
                            return;
                        } else if (mPage == 1) {
                            mView.onLoadListComplete(mHomeDatas, true);
                        } else {
                            mView.onLoadListComplete(homeDataDataWrapper.data, false);
                        }


                    }
                });
    }

    @Override
    public void loadMoreList() {
        mPage++;
        loadList();
    }

    @Override
    public List<HomeData> getHomeDatas() {
        return mHomeDatas;
    }
}
