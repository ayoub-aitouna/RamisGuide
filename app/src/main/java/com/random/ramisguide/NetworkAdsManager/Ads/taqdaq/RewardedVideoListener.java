package com.random.ramisguide.NetworkAdsManager.Ads.taqdaq;

import android.util.Log;

import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.model.rewards.TDReward;

import java.util.Locale;

public class RewardedVideoListener extends TMAdListener {

    RewardCall call;

    public RewardedVideoListener(RewardCall call) {
        this.call = call;
    }

    @Override
    public void didLoad() {
        super.didLoad();
    }

    @Override
    public void didComplete() {
        super.didComplete();
        call.call("", 0);
    }

    @Override
    public void didFailToLoad(TMAdError error) {
        super.didFailToLoad(error);
        call.error();
    }

    @Override
    public void onUserDeclined() {
        super.onUserDeclined();
        call.error();
    }

    @Override
    public void didEngagement() {
        super.didEngagement();
    }

    @Override
    public void didClose() {
        super.didClose();
        call.error();
    }

    @Override
    public void didFailToDisplay(TMAdError error) {
        super.didFailToDisplay(error);
        call.error();
    }

    @Override
    public void didVerify(TDReward reward) {
        Log.i("MEDIATION-SAMPLE", String.format(Locale.ENGLISH, "didVerify: ID: %s, Tag: %s. Reward: %s. Value: %d. Valid: %b. Custom Json: %s", reward.getRewardId(), reward.getTag(), reward.getName(), reward.getValue(), reward.isValid(), reward.getCustom_json().toString()));
    }
}