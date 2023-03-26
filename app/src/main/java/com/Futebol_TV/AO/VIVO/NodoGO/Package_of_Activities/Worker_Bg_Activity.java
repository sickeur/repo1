package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;
import com.yandex.mobile.ads.common.InitializationListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Worker_Bg_Activity extends AppCompatActivity {

    Activity activity;
    API_Manager adsApi;
    int Time = 200;
    RequestQueue queue;

    public boolean isAdmobInitialized=false,isUnityInitialized=false,isMaxInitialized=false,isYandexInitialized=false;
    public String myLog="Active Ad : "+ AllVariables.WhichAdNetworkSelected +" is initialized : ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        activity = this;

        Log.d("Ayman Log", "WorderInterstitial Started");


        adsApi = new API_Manager(Worker_Bg_Activity.this);


        //Toast.makeText(getApplicationContext(), "Worker_Bg_Activity Opened!", Toast.LENGTH_SHORT).show();

        queue = Volley.newRequestQueue(this);
        getData();
    }

    private void getData(){
        Log.d("ridoux_log", "get Data...");
        //Toast.makeText(getApplicationContext(), "Getting Json data...", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Loader_Activity_Splash.theMainJsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Toast.makeText(getApplicationContext(), "JSON Loaded!", Toast.LENGTH_SHORT).show();
                            JSONArray jsonAppConfigArray = response.getJSONArray("App_Config");
                            JSONArray jsonAdsConfigArray = response.getJSONArray("Ads_Units");




                            JSONObject AppConfig= jsonAppConfigArray.getJSONObject(0);

                            AllVariables.WhichAdNetworkSelected = AppConfig.getString("Active_Ad");
                            AllVariables.SoloOrMixAdStyle = AppConfig.getString("Ads_Style");
                            AllVariables.SoloOrMixInterStyle = AppConfig.getString("Inter_Style");
                            AllVariables.CounterToShowSecondaryInter = AppConfig.getInt("Inter_Counter");
                            AllVariables.WhatIsMySecondaryInterstitial = AppConfig.getString("Secondary_Inter_Type");
                            AllVariables.work_with_how_many_inters = AppConfig.getInt("Inter_Ads_Index");



                            // I Don't know if I should delete this or not
//                            AllVariables.is_Ad_Background_Active=AppConfig.getBoolean("is_Ad_Background_Active");




                            //Mix Selected Ads
                            AllVariables.TheMixOfTheSelectedAds = Arrays.asList(AllVariables.WhichAdNetworkSelected.split(";"));

                            if(AllVariables.SoloOrMixAdStyle.equals("mix")){
                                AllVariables.TheTypeOfInter = AllVariables.TheMixOfTheSelectedAds.get(0);
                                AllVariables.TheTypeOfBanner = AllVariables.TheMixOfTheSelectedAds.get(1);
                                AllVariables.TheTypeOfNative = AllVariables.TheMixOfTheSelectedAds.get(2);
                            }
                            else {
                                AllVariables.TheTypeOfInter = AllVariables.TheMixOfTheSelectedAds.get(0);
                                AllVariables.TheTypeOfBanner = AllVariables.TheMixOfTheSelectedAds.get(0);
                                AllVariables.TheTypeOfNative = AllVariables.TheMixOfTheSelectedAds.get(0);
                            }



                            JSONObject AdsConfig= jsonAdsConfigArray.getJSONObject(0);

                            //max
                            AllVariables.Max1 = AdsConfig.getString("max_inter_first");
                            AllVariables.Max2 = AdsConfig.getString("max_inter_second");
                            AllVariables.Max3 = AdsConfig.getString("max_inter_third");
                            AllVariables.Max4 = AdsConfig.getString("max_inter_fourth");
                            AllVariables.MaxBanner = AdsConfig.getString("max_banner");
                            AllVariables.MaxNative = AdsConfig.getString("max_native");

                            //fb
                            AllVariables.MetaInter = AdsConfig.getString("fb_inter");
                            AllVariables.MetaBanner = AdsConfig.getString("fb_banner");
                            AllVariables.MetaNative = AdsConfig.getString("fb_native");

                            //admob
                            AllVariables.GoogleInter = AdsConfig.getString("admob_inter");
                            AllVariables.GoogleBanner = AdsConfig.getString("admob_banner");
                            AllVariables.GoogleNative = AdsConfig.getString("admob_native");

                            //yandex
                            AllVariables.YandexInterId = AdsConfig.getString("yandex_inter");
                            AllVariables.YandexBannerId = AdsConfig.getString("yandex_banner");

                            //Unity
                            AllVariables.TheGameIdOfUnityAds = AdsConfig.getString("UnityGameId");
                            AllVariables.BannerId = AdsConfig.getString("unity_banner");
                            AllVariables.InterId = AdsConfig.getString("unity_inter");


                            //Toast.makeText(getApplicationContext(), "Loading ads...", Toast.LENGTH_SHORT).show();
                            checkSelectedAd();
                            adsApi.SelectedApiForAds();
                        } catch (JSONException e) {
                            //Toast.makeText(Worker_Bg_Activity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Ayman Log", "Json Backgrouind Error");
                finish();
                error.printStackTrace();
            }
        });

        request.setShouldCache(false);
        queue.add(request);
    }

    public void showAds(){
        Log.d("ridoux_log", "Show Ads!");
        Log.d("ridoux_log", "ShowTheChosenInter");
        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ShowTheChosenInter();
                }
            }, AllVariables.AdsTimerInWV);
        }
        catch (Exception exception){

        }

    }

    public void ShowTheChosenInter(){
        //Toast.makeText(getApplicationContext(), "Loading interstitial...", Toast.LENGTH_SHORT).show();

        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("Ayman Log", "Ads Background Inside adsHandler");

                        if(!App_go_DAISY.isAdReady){
                            Log.d("Ayman Log", "Ads Background Didn't start");
                            adsHandler.postDelayed(this,Time);
                        }
                        else {
                            Log.d("Ayman Log", "Ads Background Should Start");

                            if(!AllVariables.ShouldIActivateAdsInBg){
                                Log.d("Ayman Log", "Ads Background is not Active");
                                finish();
                            }
                            else {
                                checkInit();
                                adsApi.showInter(new API_Manager.EndInter() {
                                    @Override
                                    public void onEndInter() {
                                        //Toast.makeText(getApplicationContext(), "Interstitial finished.", Toast.LENGTH_SHORT).show();
                                        Log.d("Ayman Log", "Ads Background Ended");
                                        checkInit();
                                        finish();
                                    }
                                });
                            }





                        }

                    }
                }, Time);
            }
        catch (Exception ex){
            //Toast.makeText(getApplicationContext(), "Failed to show interstitial!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }



    @Override
    public void onBackPressed() {
        //Block back button
    }

    private void checkSelectedAd() {
        try {
            MaxInit();
            AdmobInit();
            FanInit();
            YandexInit();
            UnityInit();

            ShowTheChosenInter();
        }
        catch (Exception exception){
            //Toast.makeText(Worker_Bg_Activity.this,""+exception,Toast.LENGTH_SHORT).show();
        }
    }

    private void UnityInit() {
        UnityAds.initialize(getApplicationContext(), AllVariables.TheGameIdOfUnityAds, AllVariables.testMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

                isUnityInitialized=true;
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
    }

    private void YandexInit() {
        com.yandex.mobile.ads.common.MobileAds.initialize(this, new InitializationListener() {
            @Override
            public void onInitializationCompleted() {

                isYandexInitialized=true;
            }
        });
    }

    private void FanInit() {
        //MaxAds Init
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        //Fb Meta Init
        AudienceNetworkAds.initialize(this);
    }

    private void AdmobInit() {
        //Admob Init
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                isAdmobInitialized=true;
            }
        });
    }

    private void MaxInit() {
        //MaxAds Init
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {
                isMaxInitialized=true;
            }
        });
    }

    private void checkInit(){
        switch (AllVariables.WhichAdNetworkSelected){
            case "max" :
                Log.d("Ayman Log", myLog + isMaxInitialized);

                break;

            case "admob" :
                Log.d("Ayman Log", myLog + isAdmobInitialized);

                break;

            case "fb" :
                Log.d("Ayman Log", myLog + "Facebook");
                break;


            case "yandex" :
                Log.d("Ayman Log", myLog + isYandexInitialized);
                break;

            case "unity":
                Log.d("Ayman Log", myLog + isUnityInitialized);
                break;

            default:
                Log.d("Ayman Log", "Nothing is Initialized");

                break;
        }
    }

}
