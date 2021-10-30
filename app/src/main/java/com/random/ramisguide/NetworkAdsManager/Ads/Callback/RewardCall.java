package com.random.ramisguide.NetworkAdsManager.Ads.Callback;

public interface RewardCall {
    void call(String type, int amount);

    void error();
}
