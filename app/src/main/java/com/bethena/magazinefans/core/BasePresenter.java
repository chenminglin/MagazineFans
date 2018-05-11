package com.bethena.magazinefans.core;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    protected final String TAG = getClass().getSimpleName();

    V mView;


    public BasePresenter(V view){
        attachView(view);
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
