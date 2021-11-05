package com.random.ramisguide.NetworkAdsManager.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.BannerView;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeAdView;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.NativeIconView;
import com.appodeal.ads.NativeMediaView;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.random.ramisguide.R;

import java.util.List;


public class Network_AppDeal {

    Activity activity;
    boolean testing = true;
    boolean first = true;
    FrameLayout nativeFrameLayout;

    //constructor
    public Network_AppDeal(Activity p_activity) {
        activity = p_activity;
    }

    public void LoadBanner(FrameLayout appodealBannerView) {
        init();
        activity.runOnUiThread(() -> {
            Appodeal.setTesting(true);
            Appodeal.disableLocationPermissionCheck();
            Appodeal.disableNetwork(activity, "cheetah");
            Appodeal.setAutoCache(Appodeal.BANNER, false);
            Appodeal.initialize(activity, Config.controls.AppDealKey, Appodeal.BANNER_VIEW);
            Appodeal.cache(activity, Appodeal.BANNER_VIEW);
            BannerView bannerView = Appodeal.getBannerView(activity);
            appodealBannerView.addView(bannerView);
            Appodeal.setBannerCallbacks(new BannerCallbacks() {
                @Override
                public void onBannerLoaded(int height, boolean isPrecache) {
                    ShowBanner();
                }

                @Override
                public void onBannerFailedToLoad() {
                }

                @Override
                public void onBannerShown() {
                }

                @Override
                public void onBannerShowFailed() {

                }

                @Override
                public void onBannerClicked() {
                }

                @Override
                public void onBannerExpired() {

                }
            });
        });

    }

    public void createNative(FrameLayout nativeAdFrameLayout) {
        init();
        first = true;
        Appodeal.setTesting(true);
        nativeFrameLayout = nativeAdFrameLayout;
        Appodeal.disableLocationPermissionCheck();
        Appodeal.disableNetwork(activity, "cheetah");
        Appodeal.setAutoCache(Appodeal.NATIVE, false);
        Appodeal.setRequiredNativeMediaAssetType(Native.MediaAssetType.ICON);
        Appodeal.initialize(activity, Config.controls.AppDealKey, Appodeal.NATIVE);
        loadNativeAd();
        Appodeal.setNativeCallbacks(new NativeCallbacks() {
            @Override
            public void onNativeLoaded() {
                if (first) {
                    first = false;
                    show_native_ad();
                }
            }

            @Override
            public void onNativeFailedToLoad() {

            }

            @Override
            public void onNativeShown(NativeAd nativeAd) {
            }

            @Override
            public void onNativeShowFailed(NativeAd nativeAd) {
            }

            @Override
            public void onNativeClicked(NativeAd nativeAd) {
            }

            @Override
            public void onNativeExpired() {
            }
        });
    }

    public void show_native_ad() {
        List<NativeAd> loadedNativeAds = Appodeal.getNativeAds(1);
        if (loadedNativeAds.isEmpty()) {
            //Native Ads not loaded yet
            return;
        }
        NativeAd nativeAd = loadedNativeAds.get(0);
        View view = (View) activity.getLayoutInflater().inflate(R.layout.appodeal_native_ad_layout, null);

        NativeAdView nativeAdView = (NativeAdView) view.findViewById(R.id.native_layout_appodeal);

        TextView tvTitle = (TextView) nativeAdView.findViewById(R.id.tv_title);
        tvTitle.setText(nativeAd.getTitle());
        nativeAdView.setTitleView(tvTitle);

        TextView tvDescription = (TextView) nativeAdView.findViewById(R.id.tv_description);
        tvDescription.setText(nativeAd.getDescription());
        nativeAdView.setDescriptionView(tvDescription);

        RatingBar ratingBar = (RatingBar) nativeAdView.findViewById(R.id.rb_rating);
        if (nativeAd.getRating() == 0) {
            ratingBar.setVisibility(View.INVISIBLE);
        } else {
            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.setRating(nativeAd.getRating());
            ratingBar.setStepSize(0.1f);
        }
        nativeAdView.setRatingView(ratingBar);

        Button ctaButton = (Button) nativeAdView.findViewById(R.id.b_cta);
        ctaButton.setText(nativeAd.getCallToAction());
        nativeAdView.setCallToActionView(ctaButton);

        View providerView = nativeAd.getProviderView(activity);
        if (providerView != null) {
            if (providerView.getParent() != null && providerView.getParent() instanceof ViewGroup) {
                ((ViewGroup) providerView.getParent()).removeView(providerView);
            }
            FrameLayout providerViewContainer = (FrameLayout) nativeAdView.findViewById(R.id.provider_view);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            providerViewContainer.addView(providerView, layoutParams);
        }
        nativeAdView.setProviderView(providerView);

        TextView tvAgeRestrictions = (TextView) nativeAdView.findViewById(R.id.tv_age_restriction);
        if (nativeAd.getAgeRestrictions() != null) {
            tvAgeRestrictions.setText(nativeAd.getAgeRestrictions());
            tvAgeRestrictions.setVisibility(View.VISIBLE);
        } else {
            tvAgeRestrictions.setVisibility(View.GONE);
        }

        NativeIconView nativeIconView = nativeAdView.findViewById(R.id.icon);
        nativeAdView.setNativeIconView(nativeIconView);

        NativeMediaView nativeMediaView = (NativeMediaView) nativeAdView.findViewById(R.id.appodeal_media_view_content);
        nativeAdView.setNativeMediaView(nativeMediaView);

        nativeAdView.registerView(nativeAd);
        nativeAdView.setVisibility(View.VISIBLE);
        nativeFrameLayout.removeAllViews();
        nativeFrameLayout.addView(nativeAdView);
        nativeFrameLayout.invalidate();
        nativeFrameLayout.setVisibility(View.VISIBLE);

    }

    public void hideNativeAd() {
        Appodeal.hide(activity, Appodeal.NATIVE);
        nativeFrameLayout.setVisibility(View.GONE);
        Log.d("godot", "hide native");
    }

    public void createInterstitial() {
        init();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Appodeal.setTesting(true);
                Appodeal.disableLocationPermissionCheck();
                Appodeal.disableNetwork(activity, "cheetah");
                Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
                Appodeal.initialize(activity, Config.controls.AppDealKey, Appodeal.INTERSTITIAL);
                Appodeal.cache(activity, Appodeal.INTERSTITIAL);
                Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
                    @Override
                    public void onInterstitialLoaded(boolean isPrecache) {
                    }

                    @Override
                    public void onInterstitialFailedToLoad() {
                    }

                    @Override
                    public void onInterstitialShown() {
                    }

                    @Override
                    public void onInterstitialShowFailed() {

                    }

                    @Override
                    public void onInterstitialClicked() {
                    }

                    @Override
                    public void onInterstitialClosed() {
                    }

                    @Override
                    public void onInterstitialExpired() {

                    }
                });

            }
        });
    }

    public void init() {
        Appodeal.setTesting(testing);
    }


    public void ShowBanner() {
        Appodeal.show(activity, Appodeal.BANNER_VIEW);
    }

    public boolean show = false;

    public void showRewardedVideoAd(RewardCall call) {
        show = true;
        Appodeal.disableLocationPermissionCheck();
        Appodeal.disableNetwork(activity, "cheetah");
        Appodeal.setAutoCache(Appodeal.REWARDED_VIDEO, false);
        Appodeal.initialize(activity, Config.controls.AppDealKey, Appodeal.REWARDED_VIDEO);
        Appodeal.cache(activity, Appodeal.REWARDED_VIDEO);
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded(boolean b) {
                if (show) {
                    Appodeal.show(activity, Appodeal.REWARDED_VIDEO);
                    show = false;
                }

            }

            @Override
            public void onRewardedVideoFailedToLoad() {
                call.error();

            }

            @Override
            public void onRewardedVideoShown() {
            }

            @Override
            public void onRewardedVideoShowFailed() {
                call.error();

            }

            @Override
            public void onRewardedVideoFinished(double v, String s) {
                //call.call("", 0);
            }

            @Override
            public void onRewardedVideoClosed(boolean finished) {
                loadRewardedVideoAd();
                if (finished) {
                    call.call("", 0);
                } else {
                    call.error();
                }
//                cb.onRewarded(finished);
            }

            @Override
            public void onRewardedVideoExpired() {

            }

            @Override
            public void onRewardedVideoClicked() {

            }
        });

    }

    public boolean ShowInter = false;

    public void ShowInter(InterCallback callback) {
        ShowInter = true;
        init();
        activity.runOnUiThread(() -> {
            Appodeal.setTesting(true);
            Appodeal.disableLocationPermissionCheck();
            Appodeal.disableNetwork(activity, "cheetah");
            Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
            Appodeal.initialize(activity, Config.controls.AppDealKey, Appodeal.INTERSTITIAL);
            Appodeal.cache(activity, Appodeal.INTERSTITIAL);
            Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
                @Override
                public void onInterstitialLoaded(boolean isPrecache) {
                    if (ShowInter) {
                        Appodeal.show(activity, Appodeal.INTERSTITIAL);
                        ShowInter = false;
                    }
                }

                @Override
                public void onInterstitialFailedToLoad() {
                    callback.call();
                }

                @Override
                public void onInterstitialShown() {
                }

                @Override
                public void onInterstitialShowFailed() {


                }

                @Override
                public void onInterstitialClicked() {
                }

                @Override
                public void onInterstitialClosed() {
                    callback.call();
                }

                @Override
                public void onInterstitialExpired() {

                }
            });

        });
    }


    public void loadNativeAd() {
        Appodeal.cache(activity, Appodeal.NATIVE);
    }

    public void loadInterstitialVideoAd() {
        Appodeal.cache(activity, Appodeal.INTERSTITIAL);
    }

    public void loadRewardedVideoAd() {
        Appodeal.cache(activity, Appodeal.REWARDED_VIDEO);
    }






}
