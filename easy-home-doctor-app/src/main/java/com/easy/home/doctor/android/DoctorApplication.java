package com.easy.home.doctor.android;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.easy.home.doctor.android.log.AppLogger;
import com.squareup.leakcanary.LeakCanary;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/29
 */

public class DoctorApplication extends Application {

    // Starts as true in order to be notified on first launch
    private boolean isBackground = true;

    @Override
    public void onCreate() {
        super.onCreate();

        listenForForeground();
        listenForScreenTurningOff();
        initLeakCanary();
    }

    private void listenForForeground() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (isBackground) {
                    isBackground = false;
                    notifyForeground();
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private void listenForScreenTurningOff() {
        IntentFilter screenStateFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                isBackground = true;
                notifyBackground();
            }
        }, screenStateFilter);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            isBackground = true;
            notifyBackground();
        }
    }

    private void notifyForeground() {
        // This is where you can notify listeners, handle session tracking, etc
        AppLogger.get().d("DoctorApplication", "--> 进入前台");
    }

    private void notifyBackground() {
        // This is where you can notify listeners, handle session tracking, etc
        AppLogger.get().d("DoctorApplication", "--> 进入后台");
    }

    public boolean isBackground() {
        return isBackground;
    }

    public void initLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
