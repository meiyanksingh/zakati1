package com.mks.zakati.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mks.zakati.R;
import com.mks.zakati.utils.DialogUtil;
import com.mks.zakati.utils.Lg;
import com.mks.zakati.utils.PrefMgr;
import com.mks.zakati.utils.Utils;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by rahil on 30/8/16.
 */
public class App  extends MultiDexApplication {


    private static String TAG = App.class.getName();
    public static boolean isConnected;
    private static App mAppInstance;
    private BroadcastReceiver mNetStateChangeReceiver;
    private static int numRunningActivities = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(getNetworkStateReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        mAppInstance = this;
        isConnected = Utils.isNetworkAvailable(this);
        Fresco.initialize(this);
        PrefMgr.init(getApplicationContext());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);}

    private BroadcastReceiver getNetworkStateReceiver() {
        mNetStateChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                isConnected = Utils.isNetworkAvailable(App.this);
                Lg.e("Network State", isConnected ? "Network Available" : "Network Unavailable");

            }
        };
        return mNetStateChangeReceiver;
    }

    public static App get() {
        return mAppInstance;
    }


    public static boolean isConnected() {
        return isConnected;
    }

    public static boolean isConnected(boolean showToast) {
        if (!isConnected && showToast) DialogUtil.showNoNetworkToast(get());
        return isConnected;
    }

    public static boolean isConnected(View anyView) {
        if (!isConnected) DialogUtil.showNoNetworkSnackBar(anyView);
        return isConnected;
    }

    public static boolean isConnected(View anyView, View.OnClickListener retryListener) {
        if (!isConnected) DialogUtil.showNoNetworkSnackBar(anyView, retryListener);
        return isConnected;
    }


    @Override
    public void onTerminate() {
        unregisterReceiver(mNetStateChangeReceiver);
        super.onTerminate();

    }


}
