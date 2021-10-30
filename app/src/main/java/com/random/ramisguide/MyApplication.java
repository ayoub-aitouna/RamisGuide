package com.random.ramisguide;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.google.gson.Gson;
import com.onesignal.OneSignal;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_Admob;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_AppDeal;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.random.ramisguide.NetworkAdsManager.Ads.CpaInter;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_FacebookAd;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_Ironsource;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_StartApp;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_UnityAd;
import com.random.ramisguide.NetworkAdsManager.Ads.Network_Yandex;
import com.random.ramisguide.NetworkAdsManager.Ads.taqdaq.Taqdaq;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.Utils.Controls;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.JsonLoaded;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;


public class MyApplication extends Application {
    private static String TAG = "VolleyRequest";
    ReviewManager manager;
    private static final String ONESIGNAL_APP_ID = "########-####-####-####-############";
    Activity activity;
    Network_Admob admobAds;
    Network_FacebookAd networkFacebookAd;
    Network_StartApp networkStartApp;
    Network_Ironsource ironSource;
    Network_UnityAd networkUnityAd;
    Network_Yandex networkYandex;
    Network_AppDeal networkAppDeal;
    Taqdaq taqdaq;

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        EditTheme();


    }

    public void LoadJson(JsonLoaded callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.URL, null,
                response -> {
                    Config.controls = new Gson().fromJson(String.valueOf(response), Controls.class);
                    callback.loaded();
                }, error -> {
            callback.Error();
        });
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        requestQueue.getCache().clear();
    }

    private void EditTheme() {
        try {
            SharedPreferences settings =
                    getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
            String fontSizePref = settings.getString("FONT_SIZE", "Medium");
            String Mode = settings.getString("MODE", "Light");
            if (fontSizePref.equals("Small"))
                Config.TextSize = 16F;
            else if (fontSizePref.equals("Large"))
                Config.TextSize = 25F;
            else Config.TextSize = 32F;
            AppCompatDelegate.setDefaultNightMode(Mode.equals("Light") ? MODE_NIGHT_NO : MODE_NIGHT_YES);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initAds(Activity activity) {
        this.activity = activity;
        admobAds = new Network_Admob(activity);
        networkStartApp = new Network_StartApp(activity);
        ironSource = new Network_Ironsource(activity);
        networkUnityAd = new Network_UnityAd(activity);
        networkYandex = new Network_Yandex(activity);
        networkAppDeal = new Network_AppDeal(activity);
        taqdaq = new Taqdaq(activity);
        networkFacebookAd = new Network_FacebookAd(activity);
//        Config.controls.NativeType = "Tapdaq";
//        Config.controls.BannerType = "Tapdaq";
//        Config.controls.InterType = "Tapdaq";
//        Config.controls.RewardType = "Tapdaq";
//        Config.controls.IsTapdaqAppON = true;
//        Config.controls.Tapdaq_App_id = "617d5ae008fe6c2d735d721d";
//        Config.controls.setTapdaq_client_key("53a81a6b-346a-4661-b055-69a2658bc09b");
    }


    public void ShowOPenAds(InterCallback call) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading  Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        if (Config.controls.IsAdmobON)
            admobAds.showOpenAd(() -> {
                if (dialog.isShowing()) dialog.dismiss();
                call.call();
            });
    }

    public void ShowBanner(FrameLayout layout) {
        switch (Config.controls.BannerType) {
            case "Admob":
                if (Config.controls.IsAdmobON) {
                    admobAds.showBanner(layout);
                }
                break;
            case "Facebook":
                if (Config.controls.IsFacebookON) {
                    networkFacebookAd.ShowBanner(layout);
                }
                break;
            case "Unity":
                if (Config.controls.IsUnityON) {
                    networkUnityAd.ShowBanner(layout);
                }
                break;
            case "Appodeal":
                if (Config.controls.IsAppoDealON) {
                    networkAppDeal.LoadBanner(layout);
                }
                break;
            case "Network_StartApp":
                if (Config.controls.IsStartAppON) {
                    networkStartApp.Banner(layout);
                }
                break;
            case "Network_Yandex":
                if (Config.controls.IsYandexON) {
                    networkYandex.Banner(layout);
                }
                break;
            case "iron":
                if (Config.controls.IsIronON) {
                    ironSource.ShowBanner(layout);
                }
                break;
            case "Tapdaq":
                if (Config.controls.IsTapdaqAppON)
                    taqdaq.showBanner2(layout);
                break;
        }
    }


    public void ShowInter(InterCallback callback) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading  Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        switch (Config.controls.InterType) {
            case "Admob":
                if (Config.controls.IsAdmobON) {
                    admobAds.showInterstitial(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "Facebook":
                if (Config.controls.IsFacebookON) {
                    networkFacebookAd.ShowInter(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "Unity":
                if (Config.controls.IsUnityON) {
                    networkUnityAd.ShowInter(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "Appodeal":
                if (Config.controls.IsAppoDealON) {
                    networkAppDeal.ShowInter(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "Network_StartApp":
                if (Config.controls.IsStartAppON) {
                    networkStartApp.ShowInter(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "Network_Yandex":
                if (Config.controls.IsYandexON) {
                    networkYandex.ShowInter(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "iron":
                if (Config.controls.IsIronON) {
                    ironSource.show_interstitial_ad(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            case "Tapdaq":
                if (Config.controls.getTapdaqAppON()) {
                    taqdaq.loadInter(() -> {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        callback.call();
                    });
                }
                break;
            default:
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                callback.call();
                break;

        }
    }

    public void ShowReward(RewardCall call) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        switch (Config.controls.RewardType) {
            case "Admob":
                if (Config.controls.IsAdmobON) {
                    admobAds.ShowReward(new RewardCall() {
                        @Override
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            case "Facebook":
                if (Config.controls.IsFacebookON) {
                    networkFacebookAd.ShowReward(new RewardCall() {
                        @Override
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            case "Unity":
                if (Config.controls.IsUnityON) {
                    networkUnityAd.ShowReward(new RewardCall() {
                        @Override
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            case "Appodeal":
                if (Config.controls.IsAppoDealON) {
                    networkAppDeal.showRewardedVideoAd(new RewardCall() {
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            case "Network_Yandex":
                if (Config.controls.IsYandexON) {
                    networkYandex.ShowReward(new RewardCall() {
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            case "iron":
                if (Config.controls.IsIronON) {
                    ironSource.createRewardedVideo(new RewardCall() {
                        @Override
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            case "Tapdaq":
                if (Config.controls.IsTapdaqAppON) {
                    taqdaq.ShowReawd(new RewardCall() {
                        @Override
                        public void call(String type, int amount) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.call(type, amount);
                        }

                        @Override
                        public void error() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            call.error();
                        }
                    });
                }
                break;
            default:
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                call.error();
                break;

        }
    }


    public void ShowNative(FrameLayout layout) {
        switch (Config.controls.NativeType) {
            case "Admob":
                if (Config.controls.IsAdmobON) {
                    admobAds.AdmobNative(layout);
                }
                break;
            case "Facebook":
                if (Config.controls.IsFacebookON) {
                    networkFacebookAd.createNative(layout);
                }
                break;
            case "Network_Yandex":
                if (Config.controls.IsYandexON) {
                    networkYandex.LoadNative(layout);
                }
                break;
            case "Appodeal":
                if (Config.controls.IsAppoDealON) {
                    networkAppDeal.createNative(layout);
                }
                break;
            case "Network_StartApp":
                if (Config.controls.IsStartAppON) {
                    networkStartApp.ShowNative(layout);
                }
            case "Tapdaq":
                if (Config.controls.IsTapdaqAppON) {
                    taqdaq.ShowNative(layout);
                }
                break;

        }
    }


    /**
     * call this Fucntion to Show Cpa PopUp
     *
     * @parameters
     * @type (InterCalback)
     * @description (this parameter is an callback is use for doAction after the popup is dismissed)
     */
    public void ShowCpaOffer(InterCallback callback) {
        if (Config.controls.getCpaON()) {
            CpaInter permotionDialog = new CpaInter(activity, android.R.style.Theme_Light);
            permotionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            permotionDialog.getWindow().setGravity(Gravity.CENTER);
            permotionDialog.show();
            permotionDialog.setOnDismissListener(dialogInterface -> {
                callback.call();
            });
        } else {
            callback.call();
        }

    }

    public void Destroy() throws Exception {
        networkFacebookAd.onMainDestroy();
        ironSource.DestroyBanner();
        networkYandex.destroy();
        taqdaq.DestroyBanner();
    }

    public void ShowReview(Activity activity) {
        manager = ReviewManagerFactory.create(activity);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(LunchTask -> {

                });
            } else {
                Log.d(TAG, "ShowReview: failled");
            }
        });
    }

    /**
     * input  getWindow().getDecorView()
     */
    public void AlterTextSize(View v) {
        ViewGroup viewgroup = (ViewGroup) v;
        for (int i = 0; i < viewgroup.getChildCount(); i++) {
            View v1 = viewgroup.getChildAt(i);
            if (v1 instanceof ViewGroup) AlterTextSize(v1);
            if (v1 instanceof TextView) {
                ((TextView) v1).setTextSize(Config.TextSize);
            }
            if (v1 instanceof Button) {
                ((Button) v1).setTextSize(Config.TextSize);
            }
//
        }
    }
}
