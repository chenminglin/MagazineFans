package com.bethena.magazinefans.ui.maga;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.MagazineDetail;
import com.bethena.magazinefans.core.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class MagaFragment extends BaseFragment<MagaPresenter> implements MagaContract.View {

    @Inject
    String mMagaId;

    ViewPager mViewPager;

    @Inject
    public MagaFragment(){

    }

    @Override
    public void onError() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maga, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = view.findViewById(R.id.view_pager);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mPresenter.loadMaga();

    }

    @Override
    public void onLoadMagaComplete(List<MagazineDetail> magas) {

        MagaPagerAdapter adapter = new MagaPagerAdapter(magas);
        mViewPager.setAdapter(adapter);
    }
}
