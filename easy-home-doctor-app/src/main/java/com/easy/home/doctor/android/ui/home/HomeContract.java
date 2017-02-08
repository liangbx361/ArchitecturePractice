package com.easy.home.doctor.android.ui.home;

import com.easy.home.doctor.android.base.BasePresenter;
import com.easy.home.doctor.android.base.BaseView;
import com.liangbx.android.banner.model.BannerItem;

import java.util.List;

/**
 * <p>Title: MVP桥架类<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2017<／p>
 * <p>Company: <／p>
 *
 * @author liangbx
 * @version 2017/2/4
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void setBannerData(List<BannerItem> bannerItems);
    }

    interface Presenter extends BasePresenter {
        void onBannerItemClick(int position);
    }
}
