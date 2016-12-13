package com.android.architecture.practice.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.architecture.practice.R;
import com.android.architecture.practice.data.DataManager;
import com.android.architecture.practice.data.model.LatestNews;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LastNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_news);

        init();
    }

    private void init() {
        DataManager dataManager = DataManager.getInstance(this);
        dataManager.getNetworkService().getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LatestNews>() {
                    @Override
                    public void call(LatestNews latestNews) {
                        if(latestNews != null) {
                            Log.d("Network", latestNews.toString());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }
}
