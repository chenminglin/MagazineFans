package com.bethena.magazinefans.ui.maga;

import com.bethena.magazinefans.bean.MagazineDetail;
import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.core.BaseSubscriber;
import com.bethena.magazinefans.data.Repository;

import java.util.List;

import javax.inject.Inject;

public class MagaPresenter extends BasePresenter<MagaContract.View> implements MagaContract.Presenter {


    Repository mRepository;

    String mMagaId;

    @Inject
    public MagaPresenter(Repository repository, String magaId) {
        mRepository = repository;
        mMagaId = magaId;
    }


    @Override
    public void loadMaga() {
        mRepository.getMagzine(Integer.valueOf(mMagaId))
                .compose(this.<List<MagazineDetail>>applySchedulers())
                .subscribe(new BaseSubscriber<List<MagazineDetail>>(mView, this) {
                    @Override
                    public void onNext(List<MagazineDetail> magazineDetails) {
                        mView.onLoadMagaComplete(magazineDetails);
                    }
                });


    }
}
