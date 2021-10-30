package com.random.ramisguide.NetworkAdsManager.Ads.taqdaq;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.widget.FrameLayout;

import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.random.ramisguide.Utils.Config;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqConfig;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAdOptions;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMBannerAdSizes;
import com.tapdaq.sdk.debug.TMDebugAdapter;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.TLogLevel;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.model.TMAdSize;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Taqdaq {
    static final String TAG = "TAQDAQADS";
    Activity activity;
    TMBannerAdView ad;
    TapdaqConfig config = Tapdaq.getInstance().config();
    @SuppressLint("StaticFieldLeak")
    static TMDebugAdapter mLogListAdapter;
    static Map<String, TDMediatedNativeAd> mAd = new HashMap<>();
    static TMBannerAdView mBannerAd;

    public Taqdaq(Activity actvity) {
        this.activity = actvity;
        TLog.setLoggingLevel(TLogLevel.DEBUG);
    }


    public void ShowNative(FrameLayout layout) {
        NativeAdLayout adlayout = new NativeAdLayout(activity);
        TapdaqConfig config = Tapdaq.getInstance().config();
        Tapdaq.getInstance().initialize(activity, Config.controls.Tapdaq_App_id, Config.controls.Tapdaq_client_key, config,
                new TapdaqInitListener(activity, () -> {
                    Tapdaq.getInstance().loadMediatedNativeAd(activity, Config.controls.getTapdaqNative(), new MediatedNativeListener(() -> {
                        Log.d(TAG, "TapDaq Loaded Native: ");
                        TDMediatedNativeAdOptions options = new TDMediatedNativeAdOptions();
                        Tapdaq.getInstance().loadMediatedNativeAd(activity, Config.controls.getTapdaqNative(), options,
                                new NativeAdListener(adlayout, layout));

                    }));
                }));


    }


    public void ShowReawd(RewardCall call) {
        Tapdaq.getInstance().loadRewardedVideo(activity, Config.controls.getTapdaqReward(), new RewardedVideoListener(new RewardCall() {
            @Override
            public void call(String type, int amount) {
                boolean isReady = Tapdaq.getInstance().isRewardedVideoReady(activity,
                        Config.controls.getTapdaqReward());
                if (isReady) {
                    Tapdaq.getInstance().showRewardedVideo(activity, Config.controls.getTapdaqReward(), new RewardedVideoListener(call));
                } else {
                    call.error();
                }
            }

            @Override
            public void error() {

            }
        }));

    }

    public void loadInter(InterCallback callback) {
        Tapdaq.getInstance().loadInterstitial(activity, Config.controls.TapdaqInter, new InterListener(new InterCallback() {
            @Override
            public void call() {
                Tapdaq.getInstance().loadInterstitial(activity, Config.controls.TapdaqInter, new TMAdListener());
                if (Tapdaq.getInstance().isInterstitialReady(activity, Config.controls.TapdaqInter)) {
                    Tapdaq.getInstance().showInterstitial(activity, Config.controls.TapdaqInter, new TMAdListener());
                    callback.call();
                } else {
                    callback.call();
                }
            }
        }));
    }

    public static void showInter(Activity activity, InterCallback mIntent) {

    }

    public void showBanner2(FrameLayout layout) {
        TapdaqConfig config = Tapdaq.getInstance().config();
        Tapdaq.getInstance().initialize(activity, Config.controls.Tapdaq_App_id, Config.controls.Tapdaq_client_key, config,
                new TapdaqInitListener(activity, () -> {
                    try {
                        mBannerAd = new TMBannerAdView(activity);
                        TMAdSize size = TMBannerAdSizes.STANDARD;
                        mBannerAd.load(activity, Config.controls.TapdaqBanner, size, new BannerListener());
                        layout.addView(mBannerAd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));

    }

    private class NativeAdListener extends TMAdListener {
        NativeAdLayout adlayout;
        FrameLayout layout;

        public NativeAdListener(NativeAdLayout adlayout, FrameLayout layout) {
            this.adlayout = adlayout;
            this.layout = layout;
        }

        @Override
        public void didLoad(TDMediatedNativeAd ad) {
            super.didLoad(ad);
            mAd.put(Config.controls.getTapdaqNative(), ad);
            Log.i(TAG, "Native didLoad");
            adlayout.populate(mAd.get(Config.controls.getTapdaqNative()));
            mAd.remove(Config.controls.getTapdaqNative());
            mLogListAdapter.insert("didLoad", 0);
            layout.addView(adlayout);
        }

        @Override
        public void didFailToLoad(TMAdError error) {
            String str = String.format(Locale.ENGLISH, "Native didFailToLoad: %d - %s", error.getErrorCode(), error.getErrorMessage());
            for (String key : error.getSubErrors().keySet()) {
                for (TMAdError value : error.getSubErrors().get(key)) {
                    String subError = String.format(Locale.ENGLISH, "%s - %d: %s", key, value.getErrorCode(), value.getErrorMessage());
                    str = str.concat("\n ");
                    str = str.concat(subError);
                }
            }
            Log.i(TAG, str);
            mLogListAdapter.insert(str, 0);
        }

        @Override
        public void didDisplay() {
            Log.i(TAG, "Native didDisplay");
            mLogListAdapter.insert("Native didDisplay", 0);
        }

        @Override
        public void didClick() {
            Log.i(TAG, "didClick");
            mLogListAdapter.insert("didClick", 0);
        }

    }

    public void DestroyBanner() throws Exception {
        if (ad != null) {
            ad.destroy(activity);
        }
    }
}
