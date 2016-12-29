package com.easy.home.doctor.android.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.home.doctor.android.helper.CycleIndicator;
import com.easy.home.doctor.android.helper.GlideImageLoader;
import com.easy.home.doctor.android.R;
import com.liangbx.android.banner.BannerView;
import com.liangbx.android.banner.model.BannerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.banner)
    BannerView mBannerView;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBannerView != null) {
            mBannerView.stopAutoPlay();
            mBannerView.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mBannerView != null) {
            mBannerView.stopAutoPlay();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initBanner() {
        mBannerView.setImageLoader(new GlideImageLoader());

        //设置数据
        List<BannerItem> bannerItems = new ArrayList<>();
        bannerItems.add(new BannerItem("http://img1.3lian.com/img13/c3/26/d/81.jpg", "bird"));
        bannerItems.add(new BannerItem("http://img1.3lian.com/img13/c3/26/d/81.jpg", "bird"));
        bannerItems.add(new BannerItem("http://img1.3lian.com/img13/c3/26/d/81.jpg", "bird"));
        mBannerView.setData(bannerItems);
        mBannerView.setIndicator(new CycleIndicator(bannerItems.size()));

        //设置点击监听事件
        mBannerView.setOnItemClickListener(position -> {

        });
    }
}
