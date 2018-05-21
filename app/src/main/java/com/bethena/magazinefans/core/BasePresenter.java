package com.bethena.magazinefans.core;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    protected final String TAG = getClass().getCanonicalName();

    protected V mView;

    protected CompositeDisposable mCompositeDisposable;


    public BasePresenter() {
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    protected <T> FlowableTransformer<T, T> applySchedulers() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
