package com.bethena.magazinefans.ui.home;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.core.BaseSubscriber;
import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.data.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    Repository mRepository;


    List<Banner> mBanners;
    List<MagazineConcept> mMagazineConcepts;

    int mPage = 1;


    @Inject
    public HomePresenter(Repository repository) {
        mRepository = repository;

        mBanners = new ArrayList<>();
        mMagazineConcepts = new ArrayList<>();
    }


    @Override
    public void loadBanner() {
        mRepository.getBanner()
                .compose(this.<List<Banner>>applySchedulers())
                .subscribe(new BaseSubscriber<List<Banner>>(mView,this) {
                    @Override
                    public void onNext(List<Banner> banners) {
                        mView.onLoadBannerComplete(banners);
                    }
                });
    }

    private void loadList() {
        mRepository.getHomeList(mPage)
                .compose(this.<List<MagazineConcept>>applySchedulers())
                .subscribe(new BaseSubscriber<List<MagazineConcept>>(mView, this) {

                    @Override
                    public void onNext(List<MagazineConcept> data) {
                        Timber.tag(TAG).d("getHomeList onNext");
                        if (mPage == 1) {
                            mMagazineConcepts.clear();
                            mMagazineConcepts.addAll(data);
                        }

                        if (data.size() == 0 && mMagazineConcepts.size() == 0) {
                            mView.onEmpty();
                        } else if (data.size() == 0 && mMagazineConcepts.size() != 0) {
                            mView.onLoadNoMore();
                        } else if (mPage == 1) {
                            mView.onLoadListComplete(mMagazineConcepts, true);
                        } else {
                            mView.onLoadListComplete(data, false);
                        }
//                        mView.onLoadListComplete(homeDataDataWrapper.data, true);
                    }
                });
    }

    @Override
    public void refreshList() {
        mPage = 1;
        loadList();
    }

    @Override
    public void loadMoreList() {
        mPage++;
        loadList();
    }

    @Override
    public List<MagazineConcept> getHomeDatas() {
        return mMagazineConcepts;
    }
}
