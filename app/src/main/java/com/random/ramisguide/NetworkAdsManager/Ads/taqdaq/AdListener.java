package com.random.ramisguide.NetworkAdsManager.Ads.taqdaq;

import android.app.Activity;
import android.util.Log;

import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.Utils.Config;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.debug.TMDebugAdapter;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.model.rewards.TDReward;


import java.util.Locale;

public class AdListener extends TMAdListener {
    private static final String TAG = "MEDIATION-SAMPLE";
    private int mType;
    private TMDebugAdapter mLogListAdapter;
    Activity activity;
    InterCallback interCallback;

    AdListener(int type, TMDebugAdapter mLogListAdapter, InterCallback callback) {
        mType = type;
        this.interCallback = callback;
        this.mLogListAdapter = mLogListAdapter;
    }

    @Override
    public void didLoad() {
        Log.i("MEDIATION-SAMPLE", "didLoad " + TMAdType.getString(mType));
        mLogListAdapter.insert("didLoad " + TMAdType.getString(mType), 0);
        if (Tapdaq.getInstance().isInterstitialReady(activity, Config.controls.TapdaqInter) && mType == 0) {
            Tapdaq.getInstance().showInterstitial(activity, Config.controls.TapdaqInter, new AdListener(0, mLogListAdapter, interCallback));
        } else {
            Log.i(TAG, "Interstitial ad not available, call Load first");
        }
    }

    @Override
    public void didFailToLoad(TMAdError error) {
        String str = String.format(Locale.ENGLISH, "didFailToLoad %s: %d - %s", TMAdType.getString(mType), error.getErrorCode(), error.getErrorMessage());
        for (String key : error.getSubErrors().keySet()) {
            for (TMAdError value : error.getSubErrors().get(key)) {
                String subError = String.format(Locale.ENGLISH, "%s - %d: %s", key, value.getErrorCode(), value.getErrorMessage());
                str = str.concat("\n ");
                str = str.concat(subError);
            }
        }

        Log.i(TAG, str);
        mLogListAdapter.insert(str, 0);
        interCallback.call();
    }

    @Override
    public void didRefresh() {
        Log.i(TAG, "didRefresh " + TMAdType.getString(mType));
        mLogListAdapter.insert("didRefresh " + TMAdType.getString(mType), 0);
    }

    @Override
    public void willDisplay() {
        Log.i(TAG, "willDisplay " + TMAdType.getString(mType));
        mLogListAdapter.insert("willDisplay " + TMAdType.getString(mType), 0);
    }

    @Override
    public void didDisplay() {
        Log.i(TAG, "didDisplay " + TMAdType.getString(mType));
        mLogListAdapter.insert("didDisplay " + TMAdType.getString(mType), 0);
    }

    @Override
    public void didClick() {
        Log.i(TAG, "didClick " + TMAdType.getString(mType));
        mLogListAdapter.insert("didClick " + TMAdType.getString(mType), 0);
    }

    @Override
    public void didVerify(TDReward reward) {
        String str = String.format(Locale.ENGLISH, "didVerify %s: Reward name: %s. Value: %d. Valid: %b. Custom Json: %s", TMAdType.getString(mType), reward.getName(), reward.getValue(), reward.isValid(), reward.getCustom_json().toString());
        Log.i(TAG, str);
        mLogListAdapter.insert(str, 0);
    }

    @Override
    public void didClose() {
        interCallback.call();
        Log.i(TAG, "didClose " + TMAdType.getString(mType));
        mLogListAdapter.insert("didClose " + TMAdType.getString(mType), 0);
    }
}