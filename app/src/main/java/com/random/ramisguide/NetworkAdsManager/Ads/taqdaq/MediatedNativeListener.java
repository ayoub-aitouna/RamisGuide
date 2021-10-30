package com.random.ramisguide.NetworkAdsManager.Ads.taqdaq;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;

public class MediatedNativeListener extends TMAdListener {
    private static final String TAG = "Native";
    InterCallback context;

    public MediatedNativeListener(InterCallback context) {
        this.context = context;
    }

    @Override
    public void didLoad(TDMediatedNativeAd ad) {
        Log.d(TAG, "native Did Load ");
        // Provides the ad object full of data
        context.call();
    }

    @Override
    public void didFailToDisplay(TMAdError error) {
        super.didFailToDisplay(error);
        Log.d(TAG, "didFailToDisplay: " + error.getErrorMessage());
    }

    @Override
    public void didFailToLoad(TMAdError error) {
        super.didFailToLoad(error);
        Log.d(TAG, "didFailToLoad: " + error.getErrorMessage());

    }
}
