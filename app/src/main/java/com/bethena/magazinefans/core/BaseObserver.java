package com.bethena.magazinefans.core;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    private IView mView;
    private BasePresenter mPresenter;


    public BaseObserver(IView view,BasePresenter presenter){
        this.mView = view;
        this.mPresenter = presenter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mPresenter.addDisposable(d);
    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
