package com.easy.home.doctor.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav)
    BottomBar mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBottomNav.setOnTabSelectListener((tabId) -> {
            switch (tabId) {
//                case R.id.nav
            }
        });
    }
}
