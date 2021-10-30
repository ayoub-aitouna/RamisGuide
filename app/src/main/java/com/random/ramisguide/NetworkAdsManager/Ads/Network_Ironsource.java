package com.random.ramisguide.NetworkAdsManager.Ads;

import android.app.Activity;
import android.util.Log;
import android.widget.FrameLayout;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;


public class Network_Ironsource {
    private static final String TAG = "Iron";
    IronSourceBannerLayout Banner;
    Activity mActivity;
    boolean show = false;

    public Network_Ironsource(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void ShowBanner(FrameLayout layout) {
        IronSource.init(mActivity, Config.controls.IronKey, IronSource.AD_UNIT.BANNER);
        Banner = IronSource.createBanner(mActivity, ISBannerSize.BANNER);
        IronSourceBannerLayout banner = IronSource.createBanner(mActivity, ISBannerSize.BANNER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        layout.removeAllViews();
        layout.addView(banner, 0, layoutParams);

        banner.setBannerListener(new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError error) {
                // Called after a banner has attempted to load an ad but failed.
                mActivity.runOnUiThread(() -> layout.removeAllViews());
            }

            @Override
            public void onBannerAdClicked() {
                // Called after a banner has been clicked.
            }

            @Override
            public void onBannerAdScreenPresented() {
            }

            @Override
            public void onBannerAdScreenDismissed() {
            }

            @Override
            public void onBannerAdLeftApplication() {
            }
        });
        IronSource.loadBanner(banner, "DefaultBanner");


    }

    public void createInterstitial() {
        IronSource.init(mActivity, Config.controls.IronKey, IronSource.AD_UNIT.INTERSTITIAL);
        RequestNewInter();
    }

    private void RequestNewInter() {
        IronSource.loadInterstitial();

    }

    public void show_interstitial_ad(InterCallback callback) {
        show = true;
        IronSource.init(mActivity, Config.controls.getIronKey(), IronSource.AD_UNIT.INTERSTITIAL);
        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {
                Log.d(TAG, "onInterstitialAdReady: ");
                if (show) {
                    IronSource.showInterstitial("DefaultInterstitial");
                    show = false;
                }
            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                Log.d(TAG, "onInterstitialAdLoadFailed: " + error.getErrorMessage());
                callback.call();
            }

            @Override
            public void onInterstitialAdOpened() {
            }

            @Override
            public void onInterstitialAdClosed() {
                callback.call();
                RequestNewInter();
            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
                Log.d(TAG, "onInterstitialAdShowFailed: " + error.getErrorMessage());
                RequestNewInter();
                callback.call();
            }

            @Override
            public void onInterstitialAdClicked() {
            }

            @Override
            public void onInterstitialAdShowSucceeded() {
            }
        });
        RequestNewInter();
    }


    public void createRewardedVideo(RewardCall cb) {
        IronSource.init(mActivity, Config.controls.IronKey, IronSource.AD_UNIT.REWARDED_VIDEO);
        IronSource.setRewardedVideoListener(new RewardedVideoListener() {

            @Override
            public void onRewardedVideoAdOpened() {
            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewardedVideoAvailabilityChanged(boolean available) {
                //Change the in-app 'Traffic Driver' state according to availability
                show_rewarded_video();
            }

            @Override
            public void onRewardedVideoAdRewarded(Placement placement) {
                cb.call("", 0);
            }


            @Override
            public void onRewardedVideoAdShowFailed(IronSourceError error) {
                cb.error();
            }


            @Override
            public void onRewardedVideoAdClicked(Placement placement) {
            }


            @Override
            public void onRewardedVideoAdStarted() {
            }

            /* Invoked when the video ad finishes plating. */
            @Override
            public void onRewardedVideoAdEnded() {
            }
        });
        IronSource.shouldTrackNetworkState(mActivity, true);
    }

    public boolean is_rewarded_video_loaded() {
        return IronSource.isRewardedVideoAvailable();
    }

    public void show_rewarded_video() {
        mActivity.runOnUiThread(() -> IronSource.showRewardedVideo("DefaultRewardedVideo"));
    }


    public interface Callback {
        void onRewarded(Boolean b);

        void onAdLoaded();

        void onAdFailedToLoad();
    }

    public void DestroyBanner() {
        try {

            IronSource.destroyBanner(Banner);
        } catch (Exception e) {

        }
    }
}
