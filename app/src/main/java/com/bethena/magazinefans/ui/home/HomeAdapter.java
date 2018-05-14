package com.bethena.magazinefans.ui.home;

import android.support.annotation.Nullable;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.HomeData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeData, BaseViewHolder> {
    public HomeAdapter(@Nullable List<HomeData> data) {
        super(R.layout.item_home, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData item) {
        helper.setText(R.id.title, item.magName);
    }
}
