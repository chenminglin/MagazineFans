package com.bethena.magazinefans.ui.all;

import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.core.BaseSubscriber;
import com.bethena.magazinefans.data.Repository;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class NamePresenter extends BasePresenter<NameContract.View> implements NameContract.Presenter {

    List<MagazineConcept> mDatas = new ArrayList<>();

    Repository mRepository;

    String mStartLetter;

    int mPage = 1;

    @Inject
    public NamePresenter(Repository repository, String startLeter) {
        this.mRepository = repository;
        this.mStartLetter = startLeter.toLowerCase();
    }


    @Override
    public List<MagazineConcept> getDatas() {
        return mDatas;
    }

    @Override
    public void refreshList() {
        mPage = 1;
        loadDatas();
    }

    @Override
    public void loadMoreList() {
        mPage++;
        loadDatas();
    }

    private void loadDatas() {
        mRepository
                .getMagzinesByLetter(30, mPage, mStartLetter)
                .compose(this.<List<MagazineConcept>>applySchedulers())
                .subscribe(new BaseSubscriber<List<MagazineConcept>>(mView, this) {
                    @Override
                    public void onNext(List<MagazineConcept> magas) {
                        Timber.tag(TAG).d("getHomeList onNext");
                        if (mPage == 1) {
                            mDatas.clear();
                            mDatas.addAll(magas);
                        }

                        if (magas.size() == 0 && mDatas.size() == 0) {
                            mView.onEmpty();
                        } else if (magas.size() == 0 && mDatas.size() != 0) {
                            mView.onLoadNoMore();
                        } else if (mPage == 1) {
                            mView.onLoadListComplete(mDatas, true);
                        } else {
                            mView.onLoadListComplete(magas, false);
                        }
                    }

                });

    }
}
