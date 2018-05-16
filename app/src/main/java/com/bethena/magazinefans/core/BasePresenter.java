package com.bethena.magazinefans.core;

import com.bethena.magazinefans.bean.DataWrapper;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    protected final String TAG = getClass().getSimpleName();

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


    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
