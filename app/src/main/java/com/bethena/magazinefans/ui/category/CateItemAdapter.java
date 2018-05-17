package com.bethena.magazinefans.ui.category;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.Category;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.utils.ImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CateItemAdapter extends BaseQuickAdapter<MagazineConcept, BaseViewHolder> {
    public CateItemAdapter(@Nullable List<MagazineConcept> data) {
        super(R.layout.item_hori_cate, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MagazineConcept item) {

        helper.setText(R.id.tv_maga_name, item.magName);


        ImageLoader.loadCenterImg(mContext, item.magCover)
                .into((ImageView) helper.getView(R.id.img_cover));

    }
}
