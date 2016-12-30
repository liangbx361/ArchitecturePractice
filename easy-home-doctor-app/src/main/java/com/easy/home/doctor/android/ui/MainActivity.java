package com.easy.home.doctor.android.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.easy.home.doctor.android.R;
import com.easy.home.doctor.android.ui.home.HomeFragment;
import com.easy.home.doctor.android.ui.mine.MineFragment;
import com.easy.home.doctor.android.ui.patient.PatientFragment;
import com.liangbx.android.common.base.BaseActivity;
import com.roughike.bottombar.BottomBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private Map<String, Fragment> mFragmentMap = new HashMap<>();

    @BindView(R.id.bottom_nav)
    BottomBar mBottomNav;
    @BindView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBottomNav.setOnTabSelectListener((tabId) -> {
            switch (tabId) {
                case R.id.nav_home:
                    if(mFragmentMap.get(HomeFragment.class.getSimpleName()) != null) {
                        setCurrentPage(mFragmentMap.get(HomeFragment.class.getSimpleName()), HomeFragment.class.getSimpleName());
                    } else {
                        Fragment fragment = HomeFragment.newInstance();
                        mFragmentMap.put(HomeFragment.class.getSimpleName(), fragment);
                        setCurrentPage(fragment, HomeFragment.class.getSimpleName());
                    }
                    break;

                case R.id.nav_patient:
                    if(mFragmentMap.get(PatientFragment.class.getSimpleName()) != null) {
                        setCurrentPage(mFragmentMap.get(PatientFragment.class.getSimpleName()), PatientFragment.class.getSimpleName());
                    } else {
                        Fragment fragment = PatientFragment.newInstance();
                        mFragmentMap.put(PatientFragment.class.getSimpleName(), fragment);
                        setCurrentPage(fragment, PatientFragment.class.getSimpleName());
                    }
                    break;

                case R.id.nav_mine:
                    if(mFragmentMap.get(MineFragment.class.getSimpleName()) != null) {
                        setCurrentPage(mFragmentMap.get(MineFragment.class.getSimpleName()), MineFragment.class.getSimpleName());
                    } else {
                        Fragment fragment = MineFragment.newInstance();
                        mFragmentMap.put(MineFragment.class.getSimpleName(), fragment);
                        setCurrentPage(fragment, MineFragment.class.getSimpleName());
                    }
                    break;
            }
        });
    }

    private void setCurrentPage(@NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void setCurrentPage(@NonNull Fragment fragment, @NonNull String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 隐藏当前显示的Fragment
        List<Fragment> cacheFragments = fragmentManager.getFragments();
        if(cacheFragments != null) {
            for (Fragment item : cacheFragments) {
                if (item.isVisible()) {
                    transaction.hide(item);
                }
            }
        }

        Fragment cacheFragment = fragmentManager.findFragmentByTag(tag);
        if(cacheFragment == null) {
            transaction.add(R.id.container, fragment, tag);
        } else {
            transaction.show(cacheFragment);
        }

        transaction.commit();
    }
}
