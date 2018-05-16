package com.bethena.magazinefans.ui.home;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.utils.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerAdapter extends PagerAdapter {

    List<Banner> mDatas;
    Map<Banner, View> mMap = new HashMap();

    public BannerAdapter(List<Banner> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view;
        if ((view = mMap.get(mDatas.get(position))) == null) {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, container, false);
            ImageView imageView = view.findViewById(R.id.img_banner);
            ImageLoader.loadCenterImg(container.getContext(), mDatas.get(position).magPic).into(imageView);
        }
        container.addView(view);
        return view;
    }
}
