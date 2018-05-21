package com.bethena.magazinefans.ui.all;

import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.data.Repository;

import javax.inject.Inject;

public class AllPresenter extends BasePresenter<AllContract.View> implements AllContract.Presenter {

    @Inject
    Repository mRepository;

    @Inject
    public AllPresenter(Repository repository) {
        this.mRepository = repository;
    }

}
