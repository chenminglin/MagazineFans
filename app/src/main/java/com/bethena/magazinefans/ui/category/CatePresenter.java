package com.bethena.magazinefans.ui.category;

import com.bethena.magazinefans.bean.Category;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.core.BaseObserver;
import com.bethena.magazinefans.core.BasePresenter;
import com.bethena.magazinefans.data.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class CatePresenter extends BasePresenter<CateContract.View> implements CateContract.Presenter {

    Repository mRepository;

    List<CateViewModel> modelList = new ArrayList<>();

    @Inject
    public CatePresenter(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public void loadData() {
        mRepository.getCategory()
                .flatMap(new Function<List<Category>, ObservableSource<Category>>() {
                    @Override
                    public ObservableSource<Category> apply(List<Category> data) throws Exception {
                        Timber.tag(TAG).d(data + "");
                        return Observable.fromIterable(data);
                    }
                })
                .flatMap(new Function<Category, ObservableSource<CateViewModel>>() {
                    @Override
                    public ObservableSource<CateViewModel> apply(Category category) throws Exception {
                        CateViewModel viewModel = new CateViewModel();
                        viewModel.setCategory(category);
                        return mRepository.getMagazinesByCate(10, 2, category.cateId, null)
                                .zipWith(Observable.just(viewModel), new BiFunction<List<MagazineConcept>, CateViewModel, CateViewModel>() {
                                    @Override
                                    public CateViewModel apply(List<MagazineConcept> magazineConcepts, CateViewModel cateViewModel) throws Exception {
                                        cateViewModel.setMagas(magazineConcepts);
                                        return cateViewModel;
                                    }
                                });

                    }
                })
                .compose(this.<CateViewModel>applySchedulers())
                .subscribe(new BaseObserver<CateViewModel>(mView,this) {


                    @Override
                    public void onNext(CateViewModel cateViewModel) {
                        Timber.tag(TAG).d("onNext");
                        modelList.add(cateViewModel);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Timber.tag(TAG).d("onComplete");
                        mView.onLoadDataComplete(modelList);
                    }
                });
//
    }

    @Override
    public List<CateViewModel> getModels() {
        return modelList;
    }
}
