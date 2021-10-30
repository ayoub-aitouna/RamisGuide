package com.random.ramisguide.NetworkAdsManager.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.Mrec;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;


public class Network_StartApp {
    Activity activity;
    private String TAG = "Network_StartApp";
    StartAppAd startAppAd;
    StartAppNativeAd startAppNativeAd;

    public Network_StartApp(Activity activity) {
        this.activity = activity;
        StartAppSDK.init(activity,
                Config.controls.StartAppId, false);
        startAppAd = new StartAppAd(activity);
        startAppNativeAd = new StartAppNativeAd(activity);
        StartAppAd.disableSplash();
//        StartAppSDK.setTestAdsEnabled(true);
    }

    public void ShowInter(InterCallback callback) {
        startAppAd.loadAd(new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                startAppAd.showAd(new AdDisplayListener() {
                    @Override
                    public void adHidden(Ad ad) {
                        callback.call();
                    }

                    @Override
                    public void adDisplayed(Ad ad) {

                    }

                    @Override
                    public void adClicked(Ad ad) {
                    }

                    @Override
                    public void adNotDisplayed(Ad ad) {
                        callback.call();
                    }
                });

            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                callback.call();
            }
        });


    }

    public void Banner(FrameLayout layout) {
        Banner banner = new Banner(activity);
        banner.setBannerListener(new BannerListener() {
            @Override
            public void onReceiveAd(View view) {

            }

            @Override
            public void onFailedToReceiveAd(View view) {
                Log.d(TAG, "onFailedToReceiveAd: ");
            }

            @Override
            public void onImpression(View view) {

            }

            @Override
            public void onClick(View view) {

            }
        });
        banner.loadAd();
        layout.addView(banner);
    }

    public void ShowNative(FrameLayout layout) {
        Mrec startAppMrec = new Mrec(activity);
        RelativeLayout.LayoutParams mrecParameters =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
// Add to main Layout
        layout.addView(startAppMrec, mrecParameters);
    }

}
