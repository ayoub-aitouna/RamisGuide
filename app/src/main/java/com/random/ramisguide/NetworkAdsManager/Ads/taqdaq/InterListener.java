package com.random.ramisguide.NetworkAdsManager.Ads.taqdaq;

import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;

public class InterListener extends TMAdListener {
    InterCallback callback;

    public InterListener(InterCallback callback) {
        this.callback = callback;
    }

    @Override
    public void didLoad() {
        super.didLoad();
        callback.call();
    }

    @Override
    public void didLoad(TDMediatedNativeAd ad) {
        super.didLoad(ad);
        callback.call();

    }

    @Override
    public void didRefresh() {
        super.didRefresh();
    }

    @Override
    public void didFailToRefresh(TMAdError error) {
        super.didFailToRefresh(error);
    }

    @Override
    public void willDisplay() {
        super.willDisplay();
    }

    @Override
    public void didDisplay() {
        super.didDisplay();
    }

    @Override
    public void didFailToDisplay(TMAdError error) {
        super.didFailToDisplay(error);
    }

    @Override
    public void didClick() {
        super.didClick();
    }

    @Override
    public void didClose() {
        super.didClose();
        callback.call();

    }

    @Override
    public void didFailToLoad(TMAdError error) {
        super.didFailToLoad(error);
        callback.call();

    }

    @Override
    public void didComplete() {
        super.didComplete();
    }

    @Override
    public void didEngagement() {
        super.didEngagement();
    }

    @Override
    public void didRewardFail(TMAdError error) {
        super.didRewardFail(error);
    }

    @Override
    public void onUserDeclined() {
        super.onUserDeclined();
    }
}
