package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.DataWrapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public abstract class BaseRepository {
    protected <T> ObservableTransformer<DataWrapper<T>, List<T>> transResult() {
        return new ObservableTransformer<DataWrapper<T>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<DataWrapper<T>> upstream) {
                return upstream.flatMap(new Function<DataWrapper<T>, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(DataWrapper<T> tDataWrapper) throws Exception {

                        if (tDataWrapper.status == 200) {
                            return Observable.just(tDataWrapper.data);
                        } else {
                            return Observable.error(new Exception(tDataWrapper.error));
                        }
                    }
                });
            }
        };

    }
}
