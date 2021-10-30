package com.random.ramisguide.Utils;

import com.random.ramisguide.Modules.CpaModule;
import com.random.ramisguide.Modules.FeatrudeModule;
import com.random.ramisguide.Modules.GuideModule;
import com.random.ramisguide.Modules.WallapaperModule;

import java.util.ArrayList;

public class Controls {

    public String Tapdaq_App_id;
    public String Tapdaq_client_key;
    public String AppDealKey;
    public String IronKey;
    public String StartAppId;
    public String FacebookNativeUnitId;
    public String FacebookBannerUnitId;
    public String FacebookInterUnitId;
    public String FacebookRewardUnitId;
    public String AdmobBannerUnitId;
    public String AdmobInterUnitId;
    public String AdmobNativeUnitId;
    public String AdmobRewardUnitId;
    public String AdmobOpenUnitId;
    public String YandexBannerUnitId;
    public String YandexInterUnitId;
    public String YandexRewardUnitId;
    public String YandexNativeUnitId;
    public String UnityGameID;
    public String UnityBannerUnitId;
    public String TapdaqInter;
    public String TapdaqBanner;
    public String TapdaqNative;
    public String TapdaqReward;
    /**
     * to change Network Type Just Writ on of those on you Adtype you want
     * Admob
     * Facebook
     * Unity
     * Appodeal
     * Network_Yandex
     * Network_StartApp
     * iron
     * Tapdaq
     */
    public String BannerType;
    public String InterType;
    public String NativeType;
    public String RewardType;
    /**
     * Switch ON/OF
     *
     * @ON(true)
     * @OF(false)
     */
    public Boolean IsAdmobON;
    public Boolean IsFacebookON;
    public Boolean IsIronON;
    public Boolean IsYandexON;
    public Boolean IsAppoDealON;
    public Boolean IsUnityON;
    public Boolean IsStartAppON;
    public Boolean IsTapdaqAppON;
    public Boolean IsCpaON;
    public Boolean IsFeaturedAppON;
    public Boolean IsSuspended;
    public String IconUrl;
    public String title;
    public String AppUrl;
    public String privacyUrl;

    ArrayList<FeatrudeModule> apps;
    ArrayList<WallapaperModule> wallpapers;
    ArrayList<GuideModule> Guide;
    CpaModule Cpa;

    public Controls(String tapdaq_App_id, String tapdaq_client_key, String appDealKey, String ironKey, String startAppId, String facebookNativeUnitId, String facebookBannerUnitId, String facebookInterUnitId, String facebookRewardUnitId, String admobBannerUnitId, String admobInterUnitId, String admobNativeUnitId, String admobRewardUnitId, String admobOpenUnitId, String yandexBannerUnitId, String yandexInterUnitId, String yandexRewardUnitId, String yandexNativeUnitId, String unityGameID, String unityBannerUnitId, String tapdaqInter, String tapdaqBanner, String tapdaqNative, String bannerType, String interType, String nativeType, String rewardType, Boolean isAdmobON, Boolean isFacebookON, Boolean isIronON, Boolean isYandexON, Boolean isAppoDealON, Boolean isUnityON, Boolean isStartAppON, Boolean isCpaON, Boolean isFeaturedAppON, Boolean isSuspended, String iconUrl, String title, String appUrl, String privacyUrl, ArrayList<FeatrudeModule> apps, ArrayList<WallapaperModule> wallpapers, ArrayList<GuideModule> guide, CpaModule cpa) {
        Tapdaq_App_id = tapdaq_App_id;
        Tapdaq_client_key = tapdaq_client_key;
        AppDealKey = appDealKey;
        IronKey = ironKey;
        StartAppId = startAppId;
        FacebookNativeUnitId = facebookNativeUnitId;
        FacebookBannerUnitId = facebookBannerUnitId;
        FacebookInterUnitId = facebookInterUnitId;
        FacebookRewardUnitId = facebookRewardUnitId;
        AdmobBannerUnitId = admobBannerUnitId;
        AdmobInterUnitId = admobInterUnitId;
        AdmobNativeUnitId = admobNativeUnitId;
        AdmobRewardUnitId = admobRewardUnitId;
        AdmobOpenUnitId = admobOpenUnitId;
        YandexBannerUnitId = yandexBannerUnitId;
        YandexInterUnitId = yandexInterUnitId;
        YandexRewardUnitId = yandexRewardUnitId;
        YandexNativeUnitId = yandexNativeUnitId;
        UnityGameID = unityGameID;
        UnityBannerUnitId = unityBannerUnitId;
        TapdaqInter = tapdaqInter;
        TapdaqBanner = tapdaqBanner;
        TapdaqNative = tapdaqNative;
        BannerType = bannerType;
        InterType = interType;
        NativeType = nativeType;
        RewardType = rewardType;
        IsAdmobON = isAdmobON;
        IsFacebookON = isFacebookON;
        IsIronON = isIronON;
        IsYandexON = isYandexON;
        IsAppoDealON = isAppoDealON;
        IsUnityON = isUnityON;
        IsStartAppON = isStartAppON;
        IsCpaON = isCpaON;
        IsFeaturedAppON = isFeaturedAppON;
        IsSuspended = isSuspended;
        IconUrl = iconUrl;
        this.title = title;
        AppUrl = appUrl;
        this.privacyUrl = privacyUrl;
        this.apps = apps;
        this.wallpapers = wallpapers;
        Guide = guide;
        Cpa = cpa;
    }

    public String getTapdaqReward() {
        return TapdaqReward;
    }

    public void setTapdaqReward(String tapdaqReward) {
        TapdaqReward = tapdaqReward;
    }

    public Controls() {
    }

    public Boolean getTapdaqAppON() {
        return IsTapdaqAppON;
    }

    public void setTapdaqAppON(Boolean tapdaqAppON) {
        IsTapdaqAppON = tapdaqAppON;
    }

    public String getTapdaq_App_id() {
        return Tapdaq_App_id;
    }

    public void setTapdaq_App_id(String tapdaq_App_id) {
        Tapdaq_App_id = tapdaq_App_id;
    }

    public String getTapdaq_client_key() {
        return Tapdaq_client_key;
    }

    public void setTapdaq_client_key(String tapdaq_client_key) {
        Tapdaq_client_key = tapdaq_client_key;
    }

    public String getTapdaqInter() {
        return TapdaqInter;
    }

    public void setTapdaqInter(String tapdaqInter) {
        TapdaqInter = tapdaqInter;
    }

    public String getTapdaqBanner() {
        return TapdaqBanner;
    }

    public void setTapdaqBanner(String tapdaqBanner) {
        TapdaqBanner = tapdaqBanner;
    }

    public String getTapdaqNative() {
        return TapdaqNative;
    }

    public void setTapdaqNative(String tapdaqNative) {
        TapdaqNative = tapdaqNative;
    }

    public Boolean getCpaON() {
        return IsCpaON;
    }

    public void setCpaON(Boolean cpaON) {
        IsCpaON = cpaON;
    }

    public Boolean getFeaturedAppON() {
        return IsFeaturedAppON;
    }

    public void setFeaturedAppON(Boolean featuredAppON) {
        IsFeaturedAppON = featuredAppON;
    }

    public String getPrivacyUrl() {
        return privacyUrl;
    }

    public void setPrivacyUrl(String privacyUrl) {
        this.privacyUrl = privacyUrl;
    }

    public CpaModule getCpa() {
        return Cpa;
    }

    public void setCpa(CpaModule cpa) {
        Cpa = cpa;
    }

    public ArrayList<GuideModule> getGuide() {
        return Guide;
    }

    public void setGuide(ArrayList<GuideModule> guide) {
        Guide = guide;
    }

    public String getAppDealKey() {
        return AppDealKey;
    }

    public void setAppDealKey(String appDealKey) {
        AppDealKey = appDealKey;
    }

    public String getIronKey() {
        return IronKey;
    }

    public void setIronKey(String ironKey) {
        IronKey = ironKey;
    }

    public String getStartAppId() {
        return StartAppId;
    }

    public void setStartAppId(String startAppId) {
        StartAppId = startAppId;
    }

    public String getFacebookNativeUnitId() {
        return FacebookNativeUnitId;
    }

    public void setFacebookNativeUnitId(String facebookNativeUnitId) {
        FacebookNativeUnitId = facebookNativeUnitId;
    }

    public ArrayList<WallapaperModule> getWallpapers() {
        return wallpapers;
    }


    public ArrayList<FeatrudeModule> getApps() {
        return apps;
    }

    public void setApps(ArrayList<FeatrudeModule> apps) {
        this.apps = apps;
    }

    public void setWallpapers(ArrayList<WallapaperModule> wallpapers) {
        this.wallpapers = wallpapers;
    }

    public String getFacebookBannerUnitId() {
        return FacebookBannerUnitId;
    }

    public void setFacebookBannerUnitId(String facebookBannerUnitId) {
        FacebookBannerUnitId = facebookBannerUnitId;
    }

    public String getFacebookInterUnitId() {
        return FacebookInterUnitId;
    }

    public void setFacebookInterUnitId(String facebookInterUnitId) {
        FacebookInterUnitId = facebookInterUnitId;
    }

    public String getFacebookRewardUnitId() {
        return FacebookRewardUnitId;
    }

    public void setFacebookRewardUnitId(String facebookRewardUnitId) {
        FacebookRewardUnitId = facebookRewardUnitId;
    }

    public String getAdmobBannerUnitId() {
        return AdmobBannerUnitId;
    }

    public void setAdmobBannerUnitId(String admobBannerUnitId) {
        AdmobBannerUnitId = admobBannerUnitId;
    }

    public String getAdmobInterUnitId() {
        return AdmobInterUnitId;
    }

    public void setAdmobInterUnitId(String admobInterUnitId) {
        AdmobInterUnitId = admobInterUnitId;
    }

    public String getAdmobNativeUnitId() {
        return AdmobNativeUnitId;
    }

    public void setAdmobNativeUnitId(String admobNativeUnitId) {
        AdmobNativeUnitId = admobNativeUnitId;
    }

    public String getAdmobRewardUnitId() {
        return AdmobRewardUnitId;
    }

    public void setAdmobRewardUnitId(String admobRewardUnitId) {
        AdmobRewardUnitId = admobRewardUnitId;
    }

    public String getAdmobOpenUnitId() {
        return AdmobOpenUnitId;
    }

    public void setAdmobOpenUnitId(String admobOpenUnitId) {
        AdmobOpenUnitId = admobOpenUnitId;
    }

    public String getYandexBannerUnitId() {
        return YandexBannerUnitId;
    }

    public void setYandexBannerUnitId(String yandexBannerUnitId) {
        YandexBannerUnitId = yandexBannerUnitId;
    }

    public String getYandexInterUnitId() {
        return YandexInterUnitId;
    }

    public void setYandexInterUnitId(String yandexInterUnitId) {
        YandexInterUnitId = yandexInterUnitId;
    }

    public String getYandexRewardUnitId() {
        return YandexRewardUnitId;
    }

    public void setYandexRewardUnitId(String yandexRewardUnitId) {
        YandexRewardUnitId = yandexRewardUnitId;
    }

    public String getYandexNativeUnitId() {
        return YandexNativeUnitId;
    }

    public void setYandexNativeUnitId(String yandexNativeUnitId) {
        YandexNativeUnitId = yandexNativeUnitId;
    }

    public String getUnityGameID() {
        return UnityGameID;
    }

    public void setUnityGameID(String unityGameID) {
        UnityGameID = unityGameID;
    }

    public String getUnityBannerUnitId() {
        return UnityBannerUnitId;
    }

    public void setUnityBannerUnitId(String unityBannerUnitId) {
        UnityBannerUnitId = unityBannerUnitId;
    }

    public String getBannerType() {
        return BannerType;
    }

    public void setBannerType(String bannerType) {
        BannerType = bannerType;
    }

    public String getInterType() {
        return InterType;
    }

    public void setInterType(String interType) {
        InterType = interType;
    }

    public String getNativeType() {
        return NativeType;
    }

    public void setNativeType(String nativeType) {
        NativeType = nativeType;
    }

    public String getRewardType() {
        return RewardType;
    }

    public void setRewardType(String rewardType) {
        RewardType = rewardType;
    }

    public Boolean getAdmobON() {
        return IsAdmobON;
    }

    public void setAdmobON(Boolean admobON) {
        IsAdmobON = admobON;
    }

    public Boolean getFacebookON() {
        return IsFacebookON;
    }

    public void setFacebookON(Boolean facebookON) {
        IsFacebookON = facebookON;
    }

    public Boolean getIronON() {
        return IsIronON;
    }

    public void setIronON(Boolean ironON) {
        IsIronON = ironON;
    }

    public Boolean getYandexON() {
        return IsYandexON;
    }

    public void setYandexON(Boolean yandexON) {
        IsYandexON = yandexON;
    }

    public Boolean getAppoDealON() {
        return IsAppoDealON;
    }

    public void setAppoDealON(Boolean appoDealON) {
        IsAppoDealON = appoDealON;
    }

    public Boolean getUnityON() {
        return IsUnityON;
    }

    public void setUnityON(Boolean unityON) {
        IsUnityON = unityON;
    }

    public Boolean getStartAppON() {
        return IsStartAppON;
    }

    public void setStartAppON(Boolean startAppON) {
        IsStartAppON = startAppON;
    }

    public Boolean getSuspended() {
        return IsSuspended;
    }

    public void setSuspended(Boolean suspended) {
        IsSuspended = suspended;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppUrl() {
        return AppUrl;
    }

    public void setAppUrl(String appUrl) {
        AppUrl = appUrl;
    }
}
