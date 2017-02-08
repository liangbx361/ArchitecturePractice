package com.easy.home.doctor.android.ui.home;

import android.content.Context;

import com.liangbx.android.banner.model.BannerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2017<／p>
 * <p>Company: <／p>
 *
 * @author liangbx
 * @version 2017/2/4
 */

class HomePresenter implements HomeContract.Presenter {

    private Context mContext;
    private HomeContract.View mView;

    HomePresenter(Context context, HomeContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        initBannerData();
    }

    private void initBannerData() {
        List<BannerItem> bannerItems = new ArrayList<>();
        bannerItems.add(new BannerItem("http://img1.3lian.com/img13/c3/26/d/81.jpg", "bird"));
        bannerItems.add(new BannerItem("http://img1.3lian.com/img13/c3/26/d/81.jpg", "bird"));
        bannerItems.add(new BannerItem("http://img1.3lian.com/img13/c3/26/d/81.jpg", "bird"));
        mView.setBannerData(bannerItems);
    }

    @Override
    public void onBannerItemClick(int position) {

    }
}
