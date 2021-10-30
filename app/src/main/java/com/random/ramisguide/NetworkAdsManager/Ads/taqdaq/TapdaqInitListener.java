package com.random.ramisguide.NetworkAdsManager.Ads.taqdaq;

import android.app.Activity;
import android.util.Log;

import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMInitListener;

public class TapdaqInitListener extends TMInitListener {
    private static final String TAG = "TapdaqInitListener";
    Activity activity;
    InterCallback interCallback;

    public TapdaqInitListener(Activity activity, InterCallback callback) {
        this.interCallback = callback;
        this.activity = activity;
    }

    public void didInitialise() {
        super.didInitialise();

        interCallback.call();
//        Tapdaq.getInstance().loadMediatedNativeAd(activity, MyApp.TaqNativTag, new MediatedNativeListener(activity));
    }

    @Override
    public void didFailToInitialise(TMAdError error) {
        super.didFailToInitialise(error);
        //Tapdaq failed to initialise
        Log.d(TAG, "didFailToInitialise: " + error.getErrorMessage());
    }
}
