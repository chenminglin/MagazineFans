package com.bethena.magazinefans.core;


import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposables;

public abstract class BaseSubscriber<T> implements FlowableSubscriber<T> {

    private IView mView;
    private BasePresenter mPresenter;


    public BaseSubscriber(IView view, BasePresenter presenter) {
        this.mView = view;
        this.mPresenter = presenter;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        mPresenter.addDisposable(Disposables.fromSubscription(s));
    }

    @Override
    public void onError(Throwable e) {
        mView.onError();
    }

    @Override
    public void onComplete() {

    }
}
