package com.random.ramisguide.NetworkAdsManager.Ads;

import android.app.Activity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class Network_UnityAd {
    static final String TAG = "UnityAds";
    private String adUnitId = "rewardedVideo";
    Activity activity;
    IUnityAdsListener listenerRewardedVideoAd;
    IUnityAdsListener listenerInterstitialAd;
    private final Boolean testMode = true;

    public Network_UnityAd(Activity activity) {
        this.activity = activity;
        final UnityAdsListener myAdsListener = new UnityAdsListener();
        UnityAds.addListener(myAdsListener);
        UnityAds.initialize(activity, Config.controls.UnityGameID, testMode);
    }


    public void ShowBanner(FrameLayout layout) {
        BannerView Banner = new BannerView(activity, Config.controls.UnityBannerUnitId, new UnityBannerSize(320, 50));
//        topBanner.setListener(new );
        Banner.load();
        layout.addView(Banner);
    }


    private void requestNewRewardedVideo() {

    }

    public void ShowInter(InterCallback callback) {
        if (UnityAds.isReady()) {
            Toast.makeText(activity, "Ready", Toast.LENGTH_SHORT).show();
            UnityAds.show(activity);
            callback.call();
        } else {
            Toast.makeText(activity, "NotReady", Toast.LENGTH_SHORT).show();
            callback.call();
        }

    }

    public void ShowReward(RewardCall call) {
        requestNewRewardedVideo();
        listenerRewardedVideoAd = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
//                UnityAds.show(activity, Config.UnityAdRewardedVideoUnitId);
            }

            @Override
            public void onUnityAdsStart(String s) {

            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                if (finishState.equals(UnityAds.FinishState.COMPLETED)) {
                    call.call("", 0);
                } else if (finishState.equals(UnityAds.FinishState.SKIPPED)) {
                    call.error();
                } else if (finishState.equals(UnityAds.FinishState.ERROR)) {
                    call.error();
                }
                requestNewRewardedVideo();
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                call.error();
            }
        };
        if (UnityAds.isReady()) {
            UnityAds.show(activity);
            UnityAds.addListener(listenerRewardedVideoAd);
        } else {
            call.error();
        }
    }

    // Implement the IUnityAdsListener interface methods:
    private class UnityAdsListener implements IUnityAdsListener {
        InterCallback callback;

        @Override
        public void onUnityAdsReady(String adUnitId) {
            // Implement functionality for an ad being ready to show.
        }

        @Override
        public void onUnityAdsStart(String adUnitId) {
            // Implement functionality for a user starting to watch an ad.
        }

        @Override
        public void onUnityAdsFinish(String adUnitId, UnityAds.FinishState finishState) {
            // Implement functionality for a user finishing an ad.
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            // Implement functionality for a Unity Ads service error occurring.
            Log.d(TAG, "onUnityAdsError: " + error);
        }
    }
}
