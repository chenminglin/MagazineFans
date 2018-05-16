package com.bethena.magazinefans.ui.category;

import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.data.Repository;

import javax.inject.Inject;

public class CatePresenter extends BasePresenter<CateContract.View> implements CateContract.Presenter {

    Repository mRepository;

    @Inject
    public CatePresenter(Repository repository){
        this.mRepository = repository;
    }
}
