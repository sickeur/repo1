package com.Futebol_TV.AO.VIVO.NodoGO.Package_API;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks.Google_Admob_Ads;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks.Applovin_Max_Ads;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks.Facebook_Meta_Ads;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks.Unity_Dash_Ads;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks.Yandex_Vungle_Ads;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.yandex.mobile.ads.banner.BannerAdView;

public class API_Manager {

    public static final String TAG = "API_Manager";
    private AppCompatActivity mActivity;

    Applovin_Max_Ads applovinMaxAds;
    Facebook_Meta_Ads facebook_meta_ads;
    Google_Admob_Ads google_admob_ads;
    Yandex_Vungle_Ads yandex_vungle_ads;
    Unity_Dash_Ads unity_dash_ads;

    public API_Manager(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public interface EndInter{
        void onEndInter();
    }

    public void SelectedApiForAds(){
        switch (AllVariables.TheTypeOfInter){
            case "max" :
                applovinMaxAds =new Applovin_Max_Ads(mActivity);
                break;

            case "admob" :
                google_admob_ads =new Google_Admob_Ads(mActivity);
                google_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                facebook_meta_ads =new Facebook_Meta_Ads(mActivity);
                break;

            case "yandex" :
                yandex_vungle_ads =new Yandex_Vungle_Ads(mActivity);
                break;

            case "unity":
                unity_dash_ads =new Unity_Dash_Ads(mActivity);
                break;

            default:
                break;
        }

        if(!AllVariables.SoloOrMixInterStyle.equals("solo")){
            SecondaryAdApiPicker();
        }

        if(AllVariables.SoloOrMixAdStyle.equals("solo")) return;

        switch (AllVariables.TheTypeOfBanner){
            case "max" :
                applovinMaxAds =new Applovin_Max_Ads(mActivity);
                break;

            case "admob" :
                google_admob_ads =new Google_Admob_Ads(mActivity);
                google_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                facebook_meta_ads =new Facebook_Meta_Ads(mActivity);
                break;


            case "yandex" :
                yandex_vungle_ads =new Yandex_Vungle_Ads(mActivity);
                break;

            case "unity":
                unity_dash_ads =new Unity_Dash_Ads(mActivity);
                break;

            default:
                break;
        }

        switch (AllVariables.TheTypeOfNative){
            case "max" :
                applovinMaxAds =new Applovin_Max_Ads(mActivity);
                break;

            case "admob" :
                google_admob_ads =new Google_Admob_Ads(mActivity);
                google_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                facebook_meta_ads =new Facebook_Meta_Ads(mActivity);
                break;


            case "yandex" :
                yandex_vungle_ads =new Yandex_Vungle_Ads(mActivity);
                break;

            case "unity":
                unity_dash_ads =new Unity_Dash_Ads(mActivity);
                break;

            default:
                break;
        }

    }
    public void SecondaryAdApiPicker(){
        switch (AllVariables.WhatIsMySecondaryInterstitial){
            case "max" :
                applovinMaxAds =new Applovin_Max_Ads(mActivity);
                break;

            case "admob" :
                google_admob_ads =new Google_Admob_Ads(mActivity);
                google_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                facebook_meta_ads =new Facebook_Meta_Ads(mActivity);
                break;


            case "yandex" :
                yandex_vungle_ads =new Yandex_Vungle_Ads(mActivity);
                break;

            case "unity":
                unity_dash_ads =new Unity_Dash_Ads(mActivity);
                break;

            default:
                break;
        }

    }

    public void showBanner(RelativeLayout allBannerContainer, BannerAdView YandexBannerContainer){
        switch (AllVariables.TheTypeOfBanner){
            case "max" :
                applovinMaxAds.showBanner(allBannerContainer);
                break;

            case "admob" :
                google_admob_ads.ShowingBanner(allBannerContainer);
                break;

            case "fb" :
                facebook_meta_ads.ShowingBanner(allBannerContainer);
                break;

            case "yandex" :
                yandex_vungle_ads.showBanner(YandexBannerContainer);
                break;


            case "unity" :
                unity_dash_ads.showingBanner(YandexBannerContainer);
                break;


            default:
                break;
        }
    }

    public void showNative(LinearLayout nativeContainer){
        switch (AllVariables.TheTypeOfNative){
            case "max" :
                applovinMaxAds.createNativeAd(nativeContainer);
                break;

            case "admob" :
                google_admob_ads.NativeAdsShower(nativeContainer);
                break;

            case "fb" :
                facebook_meta_ads.loadNativeAd();
                break;


            case "yandex" :
                yandex_vungle_ads.showNative(nativeContainer);
                break;

            case "unity" :
                unity_dash_ads.LoadAndSHowNative(nativeContainer);
                break;


            default:
                break;
        }
    }

    public void showSecondaryInter(EndInter endInter){
        switch (AllVariables.WhatIsMySecondaryInterstitial){
            case "max" :
                indexInterEcpm(endInter);
                break;

            case "admob" :
                google_admob_ads.InterAdsShower(new Google_Admob_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "fb" :
                facebook_meta_ads.LoadAndShowInter(new Facebook_Meta_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            case "yandex" :
                yandex_vungle_ads.showInter(new Yandex_Vungle_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "unity" :
                unity_dash_ads.LoadAndShowInter(new Unity_Dash_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            default:
                endInter.onEndInter();
                break;
        }

    }

    public void showInter(EndInter endInter){
        if(!AllVariables.SoloOrMixInterStyle.equals("solo")){
            if(App_go_DAISY.myCounter >= AllVariables.CounterToShowSecondaryInter){
                showSecondaryInter(endInter);
                App_go_DAISY.myCounter =0;
                return;
            }
            App_go_DAISY.myCounter++;
        }

//        Log.d("ridoux_log", "Show inter...");
//        Log.d("ridoux_log", "AllVariables.InterType: " + AllVariables.InterType);

        switch (AllVariables.TheTypeOfInter){
            case "max" :
                indexInterEcpm(endInter);
                break;

            case "admob" :
                google_admob_ads.InterAdsShower(new Google_Admob_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "fb" :
                facebook_meta_ads.LoadAndShowInter(new Facebook_Meta_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            case "yandex" :
                yandex_vungle_ads.showInter(new Yandex_Vungle_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "unity" :
                unity_dash_ads.LoadAndShowInter(new Unity_Dash_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            default:
                endInter.onEndInter();
                break;
        }

    }

    public void indexInterEcpm(EndInter endInter){
//        Log.d("ridoux_log", "AllVariables.TheInterIndex:" + AllVariables.TheInterIndex);
        switch (AllVariables.work_with_how_many_inters){
            case 4 :
                applovinMaxAds.show_four_inter(new Applovin_Max_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case 3:

                applovinMaxAds.show_three_inter(new Applovin_Max_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case 2 :

                applovinMaxAds.show_two_inter(new Applovin_Max_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;
            case 1 :

                applovinMaxAds.show_one_inter(new Applovin_Max_Ads.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            default: endInter.onEndInter(); break;
        }
    }







}
