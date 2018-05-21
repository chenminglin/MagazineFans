package com.bethena.magazinefans.ui.all;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bethena.magazinefans.Constants;
import com.bethena.magazinefans.R;
import com.bethena.magazinefans.core.BaseFragment;

public class NameFragment extends BaseFragment<NamePresenter> implements NameContract.View {
    @Override
    public void onError() {

    }

    String mChat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mChat = getArguments().getString(Constants.PARAM_KEY1);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText(mChat);

    }

    public static NameFragment newInstance(String chat) {
        Bundle args = new Bundle();
        args.putString(Constants.PARAM_KEY1, chat);
        NameFragment fragment = new NameFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
