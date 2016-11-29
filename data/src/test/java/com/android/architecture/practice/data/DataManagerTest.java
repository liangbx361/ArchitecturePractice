package com.android.architecture.practice.data;

import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.Result;

import org.junit.Assert;
import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */
public class DataManagerTest {
    @Test
    public void getInstance() throws Exception {

    }

    @Test
    public void getLatestNews() throws Exception {
//        TestSubscriber<LatestNews> testSubscriber = new TestSubscriber<>();
        DataManager dataManager = DataManager.getInstance();
        Observable<LatestNews> observable = dataManager.getLatestNews();
        observable.subscribe(new Action1<LatestNews>() {
            @Override
            public void call(LatestNews latestNews) {
                System.out.println("onSubscribe -->");
            }
        });
//        testSubscriber.assertError(new Throwable());
//        testSubscriber.assertReceivedOnNext();

//        Thread.sleep(2000);
    }

    @Test
    public void getLastNews2() throws Exception {
        Observable.just("a")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Assert.assertEquals("ab", s);
                    }
                });

//        Thread.sleep(2000);
    }

    @Test
    public void getLastNews3() {
        DataManager dataManager = DataManager.getInstance();
        dataManager.getLatestNews3()
                .subscribe(new Action1<Result<LatestNews>>() {
                    @Override
                    public void call(Result<LatestNews> latestNewsResult) {

                    }
                });
    }

    @Test
    public void getLastNews4() {
        DataManager dataManager = DataManager.getInstance();
        dataManager.getLatestNews4();
    }

}