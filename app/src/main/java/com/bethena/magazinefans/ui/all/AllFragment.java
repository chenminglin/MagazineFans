package com.bethena.magazinefans.ui.all;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.core.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.support.design.widget.TabLayout.*;

public class AllFragment extends BaseFragment<AllPresenter> implements AllContract.View {

    final char START_WIHT_LETTER_FIRST = 'A';
    final char START_WIHT_LETTER_LAST = 'Z';


    TabLayout mTabLayout;

    ViewPager mViewPager;

    @Inject
    public AllFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        for (char c = START_WIHT_LETTER_FIRST; c <= START_WIHT_LETTER_LAST; c++) {
            NameFragment nameFragment = NameFragment.newInstance(String.valueOf(c));
            fragmentList.add(nameFragment);
            titles.add(String.valueOf(c));
        }

        AllViewPagerAdapter adapter = new AllViewPagerAdapter(getChildFragmentManager(), fragmentList, titles);
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_all_fragment, menu);
    }

    @Override
    public void onError() {

    }

}
