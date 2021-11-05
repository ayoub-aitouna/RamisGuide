/*
 * Copyright (c) 2021.  Hurricane Development Studios
 */

package com.random.ramisguide.NetworkAdsManager.Ads;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.google.android.gms.ads.appopen.AppOpenAd;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.random.ramisguide.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import org.jetbrains.annotations.NotNull;

public class Network_Admob {
    private RewardedAd mRewardedAd;
    private static final String TAG = "Admob";
    private AppOpenAd mAppOpenAd = null;

    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    public void showOpenAd(InterCallback call) {
        loadCallback =
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                        super.onAdLoaded(appOpenAd);
                        mAppOpenAd = appOpenAd;
                        mAppOpenAd.show(mActivity);
                        call.call();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        call.call();
                    }


                };
        AppOpenAd.load(mActivity, Config.controls.getAdmobOpenUnitId(), new AdRequest.Builder().build(), AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

    }

    public interface AdFinished {
        void onAdFinished();
    }


    private final Activity mActivity;
    private InterstitialAd mInterstitialAd;

    public Network_Admob(Activity Activity) {
        this.mActivity = Activity;
        MobileAds.initialize(Activity);
    }

    public void showBanner(FrameLayout adContainer) {
        AdView adView = new AdView(mActivity);
        adView.setAdUnitId(Config.controls.AdmobBannerUnitId);
        adView.setAdSize(AdSize.BANNER);
        adContainer.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    public void showInterstitial(final AdFinished adFinished) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(mActivity, Config.controls.AdmobInterUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(mActivity);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            adFinished.onAdFinished();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NotNull AdError adError) {
                            // Called when fullscreen content failed to show.
                            adFinished.onAdFinished();
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when fullscreen content is shown.
                            // Make sure to set your reference to null so you don't
                            // show it a second time.
                            mInterstitialAd = null;
                        }
                    });
                } else {
                    adFinished.onAdFinished();
                }

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });

    }

    public void ShowReward(RewardCall callBack) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(mActivity, Config.controls.AdmobRewardUnitId,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                        callBack.error();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        if (mRewardedAd != null) {
                            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdShowedFullScreenContent() {
                                    Log.d(TAG, "Ad was shown.");
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    Log.d(TAG, "Ad failed to show.");
                                    callBack.error();
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    Log.d(TAG, "Ad was dismissed.");
                                    callBack.call("", 0);
                                    mRewardedAd = null;
                                }
                            });
                            mRewardedAd.show(mActivity, rewardItem -> {
                                Log.d(TAG, "The user earned the reward.");
                            });
                        } else {
                            Log.d(TAG, "The rewarded ad wasn't ready yet.");
                        }
                        Log.d(TAG, "Ad was loaded.");
                    }
                });


    }


    private void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);


    }


    public void AdmobNative(FrameLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(mActivity, Config.controls.AdmobNativeUnitId);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                nativeAd = nativeAd;
                frameLayout.setVisibility(View.VISIBLE);
                NativeAdView adView = (NativeAdView) mActivity.getLayoutInflater()
                        .inflate(R.layout.admob_native_ad_layout, null);
                populateUnifiedNativeAdView(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);

            }
        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(false)
                .build();

        com.google.android.gms.ads.formats.NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {

        }).build();

        adLoader.loadAd(new AdManagerAdRequest.Builder().build());


    }


}
