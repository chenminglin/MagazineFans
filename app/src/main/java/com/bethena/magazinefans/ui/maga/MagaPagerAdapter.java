package com.bethena.magazinefans.ui.maga;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.MagazineDetail;
import com.bethena.magazinefans.utils.ImageLoader;

import java.util.List;

public class MagaPagerAdapter extends PagerAdapter {

    List<MagazineDetail> mDetails;

    public MagaPagerAdapter(List<MagazineDetail> details) {
        mDetails = details;
    }

    @Override
    public int getCount() {
        return mDetails.size();
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
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_maga, container, false);

        ImageView imageView = view.findViewById(R.id.img_pager);
        ImageLoader.loadCenterImg(container.getContext(), mDetails.get(position).magPic).into(imageView);
        container.addView(view);
        return view;
    }

}
