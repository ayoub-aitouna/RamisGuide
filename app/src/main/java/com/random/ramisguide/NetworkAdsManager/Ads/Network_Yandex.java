package com.random.ramisguide.NetworkAdsManager.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.Utils.MyRatingView;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.random.ramisguide.R;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.MobileAds;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;
import com.yandex.mobile.ads.nativeads.MediaView;
import com.yandex.mobile.ads.nativeads.NativeAd;
import com.yandex.mobile.ads.nativeads.NativeAdException;
import com.yandex.mobile.ads.nativeads.NativeAdLoadListener;
import com.yandex.mobile.ads.nativeads.NativeAdLoader;
import com.yandex.mobile.ads.nativeads.NativeAdMedia;
import com.yandex.mobile.ads.nativeads.NativeAdRequestConfiguration;
import com.yandex.mobile.ads.nativeads.NativeAdView;
import com.yandex.mobile.ads.nativeads.NativeAdViewBinder;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;

import org.jetbrains.annotations.NotNull;

public class Network_Yandex {
    private String TAG = "Network_Yandex";
    private Activity activity;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;
    private NativeAdView mNativeAdView;


    public Network_Yandex(Activity activity) {
        MobileAds.initialize(activity, () -> Log.d(TAG, "SDK initialized"));
        MobileAds.enableDebugErrorIndicator(true);
        this.activity = activity;
    }

    public void Banner(FrameLayout banner) {
        BannerAdView mBannerAdView = new BannerAdView(activity);
        mBannerAdView.setBlockId(Config.controls.YandexBannerUnitId);
        mBannerAdView.setAdSize(AdSize.flexibleSize());
        final AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded: ");
            }

            @Override
            public void onAdFailedToLoad(@NotNull AdRequestError adRequestError) {
                Log.d(TAG, "onAdFailedToLoad: " + adRequestError.getDescription());
            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }
        });
        // Loading the ad.
        banner.addView(mBannerAdView);
        mBannerAdView.loadAd(adRequest);
    }


    @NonNull
    public void ShowReward(RewardCall call) {
        final RewardedAd rewardedAd = new RewardedAd(activity);
        /**Test ads "R-M-DEMO-rewarded-client-side-rtb"*/
        Toast.makeText(activity, "YandexRewardUnitId :: " + Config.controls.YandexRewardUnitId, Toast.LENGTH_SHORT).show();
        rewardedAd.setBlockId(Config.controls.YandexRewardUnitId);
        rewardedAd.setRewardedAdEventListener(new RewardedAdEventListener() {
            @Override
            public void onAdLoaded() {
                if (rewardedAd != null) {
                    rewardedAd.show();
                }

            }

            @Override
            public void onRewarded(@NonNull final Reward reward) {
                call.call(reward.getType(), reward.getAmount());
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                call.error();
            }

            @Override
            public void onAdShown() {
            }

            @Override
            public void onAdDismissed() {
                call.error();
            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }
        });
        rewardedAd.loadAd(new AdRequest.Builder().build());

    }



    public void ShowInter(InterCallback callback) {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setBlockId(Config.controls.YandexInterUnitId);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setInterstitialAdEventListener(new InterstitialAdEventListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Inter onAdLoaded: ");
                mInterstitialAd.show();

            }

            @Override
            public void onAdFailedToLoad(AdRequestError adRequestError) {
                Log.d(TAG, "Inter onAdFailedToLoad: " + adRequestError.getDescription());
                callback.call();
            }

            @Override
            public void onAdShown() {

            }

            @Override
            public void onAdDismissed() {
                callback.call();
            }

            @Override
            public void onLeftApplication() {

            }

            @Override
            public void onReturnedToApplication() {

            }
        });


    }


    public void Show_Reward(RewardCall callBack) {
        mRewardedAd = new RewardedAd(activity);
        mRewardedAd.setBlockId(Config.controls.YandexRewardUnitId);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mRewardedAd.setRewardedAdEventListener(new RewardedAdEventListener() {
            @Override
            public void onRewarded(final Reward reward) {
                callBack.call(reward.getType(), reward.getAmount());
            }

            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Reward onAdLoaded: ");
                mRewardedAd.show();
            }

            @Override
            public void onAdFailedToLoad(final AdRequestError adRequestError) {
                Log.d(TAG, "Reward onAdFailedToLoad: " + adRequestError.getDescription());
                callBack.error();

            }

            @Override
            public void onAdShown() {

            }

            @Override
            public void onAdDismissed() {
                callBack.error();

            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }
        });
        mRewardedAd.loadAd(adRequest);

    }

    private NativeAdLoader mNativeAdLoader;

    public void LoadNative(FrameLayout layout) {
        mNativeAdLoader = new NativeAdLoader(activity);
        mNativeAdLoader.setNativeAdLoadListener(new NativeAdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull final NativeAd nativeAd) {
                bind_NativeAd(nativeAd, layout);
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                Log.d("SAMPLE_TAG", adRequestError.getDescription());
            }
        });

        /**test ads "R-M-DEMO-native-i"*/
        Toast.makeText(activity, "YandexNativeUnitId :: " + Config.controls.YandexNativeUnitId, Toast.LENGTH_SHORT).show();
        final NativeAdRequestConfiguration nativeAdRequestConfiguration =
                new NativeAdRequestConfiguration.Builder(Config.controls.YandexNativeUnitId)
                        .setShouldLoadImagesAutomatically(true)
                        .build();
        mNativeAdLoader.loadAd(nativeAdRequestConfiguration);


    }

    private void bind_NativeAd(@NonNull final NativeAd nativeAd, FrameLayout layout) {
        NativeBannerView mNativeBannerView = new NativeBannerView(activity);
        mNativeBannerView.setAd(nativeAd);
        mNativeBannerView.setVisibility(View.VISIBLE);
        layout.addView(mNativeBannerView);
    }

    public void Load_Native(FrameLayout layout) {
        mNativeAdView = new NativeAdView(activity);
        final NativeAdLoader nativeAdLoader = new NativeAdLoader(activity);
        nativeAdLoader.setNativeAdLoadListener(new NativeAdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull final NativeAd nativeAd) {
                //bind nativeAd
                Log.d(TAG, "Native onAdLoaded: ");
                bindNativeAd(nativeAd, layout);
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError error) {
                //log error
                Log.d(TAG, "Native onAdFailedToLoad" + error.getDescription());
            }
        });
        final NativeAdRequestConfiguration nativeAdRequestConfiguration =
                new NativeAdRequestConfiguration.Builder(Config.controls.YandexNativeUnitId).build();
        nativeAdLoader.loadAd(nativeAdRequestConfiguration);
    }

    private void bindNativeAd(@NonNull final NativeAd nativeAd, FrameLayout layout) {
        final NativeAdViewBinder nativeAdViewBinder = new NativeAdViewBinder.Builder(mNativeAdView)
                .setAgeView((TextView) activity.findViewById(R.id.age))
                .setBodyView((TextView) activity.findViewById(R.id.body))
                .setCallToActionView((Button) activity.findViewById(R.id.call_to_action))
                .setDomainView((TextView) activity.findViewById(R.id.domain))
                .setFaviconView((ImageView) activity.findViewById(R.id.favicon))
                .setFeedbackView((Button) activity.findViewById(R.id.feedback))
                .setIconView((ImageView) activity.findViewById(R.id.icon))
                .setMediaView((MediaView) activity.findViewById(R.id.media))
                .setPriceView((TextView) activity.findViewById(R.id.price))
                .setRatingView((MyRatingView) activity.findViewById(R.id.rating))
                .setReviewCountView((TextView) activity.findViewById(R.id.review_count))
                .setSponsoredView((TextView) activity.findViewById(R.id.sponsored))
                .setTitleView((TextView) activity.findViewById(R.id.title))
                .setWarningView((TextView) activity.findViewById(R.id.warning))
                .build();
        configureMediaView(nativeAd);
        try {
            nativeAd.bindNativeAd(nativeAdViewBinder);
            layout.addView(mNativeAdView);
        } catch (NativeAdException exception) {
            Log.d(TAG, "NativeAdException" + exception.getMessage());
        }
    }

    private void configureMediaView(@NonNull final NativeAd nativeAd) {
        final NativeAdMedia nativeAdMedia = nativeAd.getAdAssets().getMedia();
        if (nativeAdMedia != null) {
            //you can use the aspect ratio if you need it to determine the size of media view.
            final float aspectRatio = nativeAdMedia.getAspectRatio();
        }
    }


    public void destroy() {
        if (mRewardedAd != null) {
            mRewardedAd.setRewardedAdEventListener(null);
            mRewardedAd.destroy();
        }
    }
}
