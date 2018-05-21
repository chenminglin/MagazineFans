package com.bethena.magazinefans.ui.all;

import android.support.annotation.Nullable;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class NameAdapter extends BaseQuickAdapter<MagazineConcept, BaseViewHolder> {
    public NameAdapter(@Nullable List<MagazineConcept> data) {
        super(R.layout.item_name, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MagazineConcept item) {
        helper.setText(R.id.txt_title, item.magName);

    }
}
