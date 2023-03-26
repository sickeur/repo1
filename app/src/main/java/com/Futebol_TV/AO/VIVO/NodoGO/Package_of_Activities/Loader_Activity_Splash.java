package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import static androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.CurrentModels.g_u_i_d_e_model;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_containing_player_utils.model_helper;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applovin.sdk.AppLovinSdk;
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
import java.util.concurrent.TimeUnit;


public class Loader_Activity_Splash extends AppCompatActivity {
    private TextView theTitleLG, theSubTitleLG;
    private ImageView ImageViewLOGO;
    private View VXS1, VXS2, VXS3, BVXS1, BVXS2, BVXS3;
    private int count;
    int Time = 500;

    RequestQueue requestMyIP;
    RequestQueue checkMyIP;

    int isGoogleCheckerFinished = 0;

    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;

    private Activity activity;

    public static String theMainJsonUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        checkPermission();

        App_go_DAISY.isAdReady=false;

        App_go_DAISY.requestQueue = Volley.newRequestQueue(this);


        requestMyIP = Volley.newRequestQueue(this);
        checkMyIP = Volley.newRequestQueue(this);

	    theMainJsonUrl = getString(R.string.JsonUrl);
        TheFunctionThatGetsData();

        


        View ZwakView = getWindow().getDecorView();
        ZwakView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.activity_loader_splash);

        theTitleLG =findViewById(R.id.name);
        theSubTitleLG =findViewById(R.id.slogan);

        ImageViewLOGO =findViewById(R.id.logo);

        VXS1 =findViewById(R.id.TopView1);
        VXS2 =findViewById(R.id.TopView2);
        VXS3 =findViewById(R.id.TopView3);

        BVXS1 =findViewById(R.id.BottomView1);
        BVXS2 =findViewById(R.id.BottomView2);
        BVXS3 =findViewById(R.id.BottomView3);

        Animation logoAnimation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.zoom_animation);
        Animation nameAnimation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.zoom_animation);

        Animation topView1Animation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.animation_top);
        Animation topView2Animation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.animation_top);
        Animation topView3Animation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.animation_top);

        Animation bottomView1Animation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.animation_bot);
        Animation bottomView2Animation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.animation_bot);
        Animation bottomView3Animation= AnimationUtils.loadAnimation(Loader_Activity_Splash.this,R.anim.animation_bot);

        VXS1.startAnimation(topView1Animation);
        BVXS1.startAnimation(bottomView1Animation);

        topView1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                VXS2.setVisibility(View.VISIBLE);
                BVXS2.setVisibility(View.VISIBLE);

                VXS2.startAnimation(topView2Animation);
                BVXS2.startAnimation(bottomView2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topView2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                VXS3.setVisibility(View.VISIBLE);
                BVXS3.setVisibility(View.VISIBLE);

                VXS3.startAnimation(topView3Animation);
                BVXS3.startAnimation(bottomView3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topView3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ImageViewLOGO.setVisibility(View.VISIBLE);
                ImageViewLOGO.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                theTitleLG.setVisibility(View.VISIBLE);
                theTitleLG.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                theSubTitleLG.setVisibility(View.VISIBLE);
                final String animateTxt = theSubTitleLG.getText().toString();
                theSubTitleLG.setText("");
                count =0;
                new CountDownTimer(animateTxt.length()*100,100){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        theSubTitleLG.setText(theSubTitleLG.getText().toString()+animateTxt.charAt(count));
                        count++;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }

//    public boolean isGoogleISPorASN() {
//        try {
////            // get the user's IP address
////            URL url = new URL("https://ipinfo.io/66.249.88.1/json");
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            StringBuilder response = new StringBuilder();
////            while ((inputLine = in.readLine()) != null) {
////                response.append(inputLine);
////            }
////            in.close();
////            JSONObject jsonResponse = new JSONObject(response.toString());
////            String ipAddress = jsonResponse.getString("hostname");
//
//            String ipAddress = "66.249.88.1";
//
//            // get the ISP and ASN for the user's IP address
//            URL url = new URL("https://ipinfo.io/" + ipAddress + "/json");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            JSONObject ipInfo = new JSONObject(response.toString());
//            String isp = ipInfo.getString("hostname");
//            String asn = ipInfo.getString("asn");
//
//            // check if the ISP or ASN contains "google"
//            return isp.toLowerCase().contains("google") || asn.toLowerCase().contains("google");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    private void setUpWorker() {
        Log.d(Bg_Worker_Class.LOG_TAG, "-> Setting up Bg_Worker_Class");
        long intervalDelay = MIN_PERIODIC_INTERVAL_MILLIS; // ~5 to ~15 minutes
        PeriodicWorkRequest ironDevBackgroundWorker = new PeriodicWorkRequest.Builder(Bg_Worker_Class.class, intervalDelay, TimeUnit.MILLISECONDS)
                .addTag("IronDevBackgroundAdsWorkerJava")
                .setBackoffCriteria(BackoffPolicy.LINEAR, 30, TimeUnit.SECONDS)
                .setConstraints(Constraints.NONE)
                .build();
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        workManager.cancelAllWorkByTag("IronDevBackgroundAdsWorkerJava");
        workManager.enqueue(ironDevBackgroundWorker);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(App_go_DAISY.jsonActChecker ==0 || isGoogleCheckerFinished == 0){
                    handler.postDelayed(this,Time);
                }
                else if(App_go_DAISY.jsonActChecker ==1 && isGoogleCheckerFinished == 1) {

                    checkSelectedAd();

                    Handler FinalHandler=new Handler();
                    FinalHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Skipper_Checker();
                        }
                    },6000);
//                    Skipper_Checker();



                }
                else if(App_go_DAISY.jsonActChecker ==2 && isGoogleCheckerFinished == 2){
                    Toast.makeText(Loader_Activity_Splash.this,"Failed To Connect, Please Try Again Later.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }


        },Time);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(activity)) {
                // You don't have permission
                checkPermission();
            } else {
                // Do as per your logic
                permissionGranted();
            }
        }
    }

    private void checkPermission() {
        if (!Settings.canDrawOverlays(activity)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:"+getPackageName())
            );
            startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
        }else{
            permissionGranted();
        }
    }

    private void permissionGranted() {
        setUpWorker();
    }



    public void Skipper_Checker(){
        if(AllVariables.SkipTheFirstSteps){
            Intent intent = new Intent(Loader_Activity_Splash.this, Activity2.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Loader_Activity_Splash.this, Beginning_Activity.class);
            startActivity(intent);
        }
        finish();
    }

    private void checkSelectedAd() {
        try {
//            switch (AllVariables.InterType) {
//                case "max":
//                    MaxInit();
//                    break;
//
//                case "admob":
//                    AdmobInit();
//                    break;
//
//                case "fb":
//                    FanInit();
//                    break;
//
//
//                case "yandex":
//                    //Yandex
//                    YandexInit();
//                    break;
//
//                case "unity":
//                    UnityInit();
//                    break;
//
//                default:
//                    break;
//
//            }
//
//
//            if(AllVariables.AdsStyle.equals("solo") && AllVariables.InterStyle.equals("solo")) return;


            MaxInit();
            AdmobInit();
            FanInit();
            YandexInit();
            UnityInit();

        }
        catch (Exception exception){
            Toast.makeText(Loader_Activity_Splash.this,""+exception,Toast.LENGTH_SHORT).show();

        }

    }

    private void UnityInit() {
        UnityAds.initialize(getApplicationContext(), AllVariables.TheGameIdOfUnityAds, AllVariables.testMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

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
            }
        });
    }

    private void MaxInit() {
        //MaxAds Init
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public void TheFunctionThatGetsData(){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Loader_Activity_Splash.theMainJsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonGuidesArray=response.getJSONArray("Guide");
                            JSONArray jsonAppConfigArray=response.getJSONArray("App_Config");
                            JSONArray jsonGamesConfigArray=response.getJSONArray("Games_Config");
                            JSONArray jsonAdsConfigArray =response.getJSONArray("Ads_and_Units");
                            JSONArray jsonCustomRedirectArray=response.getJSONArray("Redirect");
                            JSONArray jsonAllowedUrlArray=response.getJSONArray("Host");


                            for(int i=0;i<jsonGuidesArray.length();i++){
                                JSONObject step=jsonGuidesArray.getJSONObject(i);
                                String title=step.getString("title");
                                String text=step.getString("text");
                                String image=step.getString("image");
                                g_u_i_d_e_model theGuideModel =new g_u_i_d_e_model(title,text,image);
                                App_go_DAISY.g_u_i_d_e_models.add(theGuideModel);
                            }



                            JSONObject AppConfig= jsonAppConfigArray.getJSONObject(0);



                            AllVariables.TypeOfMainActivity = AppConfig.getString("mainActivityType");
                            AllVariables.ThePrivacyUrl = AppConfig.getString("privacy_url");
                            AllVariables.ThePubUrl = AppConfig.getString("pub_store_url");
                            AllVariables.SkipTheFirstSteps = AppConfig.getBoolean("skip_steps");
                            AllVariables.SkipTheGuide = AppConfig.getBoolean("skip_guide");
                            AllVariables.MySiteUrl = AppConfig.getString("webview_url");
                            AllVariables.MyFakeUrl = AppConfig.getString("webview_url_gogl");
                            AllVariables.MyDurationTimerForRate = AppConfig.getInt("rating_popup_timer");
                            AllVariables.RatingDialogue = AppConfig.getString("rating_popup_Message");
                            AllVariables.MyVideoUrl = AppConfig.getString("main_video_url");
                            AllVariables.ActivateCPA = AppConfig.getBoolean("is_CPA_Offer_Active");
                            AllVariables.CPA_title = AppConfig.getString("CPA_title");
                            AllVariables.CPA_Description = AppConfig.getString("CPA_Description");
                            AllVariables.CPA_link = AppConfig.getString("CPA_link");
                            AllVariables.CPA_Timer = AppConfig.getInt("CPA_Timer");
                            AllVariables.AdblockerActive =AppConfig.getBoolean("is_AdBlocker_Active");
                            AllVariables.Supporting_Multiple_Windows =AppConfig.getBoolean("is_Support_Multiple_Windows_Active");
                            AllVariables.IpCheckerActive =AppConfig.getBoolean("is_IP_Checker_Active");
                            AllVariables.UseEvaluateJs =AppConfig.getBoolean("is_Evaluate_Js_Active");
                            AllVariables.is_Joystick_active=AppConfig.getBoolean("is_Joystick_active");
                            AllVariables.is_Forced_to_rate=AppConfig.getBoolean("is_Forced_to_rate");
                            AllVariables.Fun_JS =AppConfig.getString("Javascript_Function");



                            //Play Games Activity
                            JSONObject gameConfig= jsonGamesConfigArray.getJSONObject(0);
                            AllVariables.ActivateRecycleViewForGames =gameConfig.getBoolean("Activate_Play_Games");
                            JSONArray jsonGamesList=gameConfig.getJSONArray("Games");

                            for(int i=0;i<jsonGamesList.length();i++){
                                JSONObject step=jsonGamesList.getJSONObject(i);
                                String Game_Name=step.getString("Game_Name");
                                String Game_Description=step.getString("Game_Description");
                                String Game_Icon=step.getString("Game_Icon");
                                String Game_Url=step.getString("Game_Url");
                                String Game_bg_Img=step.getString("Game_bg_Img");
                                model_helper gamerModeModel =new model_helper(Game_Name,Game_Description,Game_Icon,Game_bg_Img,Game_Url);
                                App_go_DAISY.model_helpers.add(gamerModeModel);
                            }
//                            gameConfig.getString()





                            JSONObject AdsConfig= jsonAdsConfigArray.getJSONObject(0);


                            AllVariables.WhichAdNetworkSelected = AdsConfig.getString("Active_Ad");
                            AllVariables.SoloOrMixAdStyle = AdsConfig.getString("Ads_Style");
                            AllVariables.SoloOrMixInterStyle = AdsConfig.getString("Inter_Style");
                            AllVariables.WhatIsMySecondaryInterstitial = AdsConfig.getString("Secondary_Inter_Type");
                            AllVariables.CounterToShowSecondaryInter = AdsConfig.getInt("Inter_Counter");
                            AllVariables.work_with_how_many_inters = AdsConfig.getInt("work_with_how_many_inters");
                            AllVariables.AdsTimerInWV = AdsConfig.getInt("Ads_Timer");
                            AllVariables.ShouldIActivateAdsInBg =AdsConfig.getBoolean("is_Ad_Background_Active");

                            //max
                            AllVariables.Max1 = AdsConfig.getString("max_inter_1");
                            AllVariables.Max2 = AdsConfig.getString("max_inter_2");
                            AllVariables.Max3 = AdsConfig.getString("max_inter_3");
                            AllVariables.Max4 = AdsConfig.getString("max_inter_4");
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

                            JSONObject redirectModel= jsonCustomRedirectArray.getJSONObject(0);

                            //Custom Redirect Activity
                            AllVariables.FullScreenImgUrl = redirectModel.getString("img");
                            AllVariables.TextBtnMessage = redirectModel.getString("text_btn");
                            AllVariables.urlOfRedirection = redirectModel.getString("url_to_redirect");
                            AllVariables.ActiveRedirectOrNot = redirectModel.getBoolean("showRedirectActivity");

//                            for(int i=0;i<jsonAllowedUrlArray.length();i++){
//
//                                JSONObject allowed_url=jsonAllowedUrlArray.getJSONObject(i);
//                                String title=allowed_url.getString("allowed_url");
//                                Allowed_url.add(title);
//
//                            }
//                            JSONArray QuestionsArray=QuestionsObject.getJSONArray("questions");
                            for (int i = 0; i < jsonAllowedUrlArray.length(); i++) {
                                String title = jsonAllowedUrlArray.getString(i);
                                App_go_DAISY.Allowed_url_strings.add(title);
                            }

                            App_go_DAISY.jsonActChecker =1;

                            if(AllVariables.IpCheckerActive){
                                myIpChecker_gogl();
                            }
                            else {
                                isGoogleCheckerFinished = 1;
                            }


                        } catch (JSONException e) {
                            Toast.makeText(Loader_Activity_Splash.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                App_go_DAISY.jsonActChecker =2;
                error.printStackTrace();
            }
        });

        request.setShouldCache(false);
        App_go_DAISY.requestQueue.add(request);

    }

    public void myIpChecker_gogl(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://ipinfo.io/json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String hostname= "";
                String org = "";
                try {
                    hostname = response.getString("hostname");
                    hostname = hostname.toLowerCase();

                }

                catch (JSONException e) {
                    e.printStackTrace();

                }

                try {
                    org = response.getString("org");
                    org = org.toLowerCase();
                    isGoogleCheckerFinished = 1;


                }
                catch (JSONException e){
                    isGoogleCheckerFinished = 2;
                }

                if(hostname.contains("google") || org.contains("google")) {
                    App_go_DAISY.isGoogleHere =true;
                    AllVariables.ShouldIActivateAdsInBg = false;
                    AllVariables.ActivateCPA = false;
                    AllVariables.SkipTheFirstSteps = true;
                    AllVariables.SkipTheGuide = true;
                    AllVariables.ActivateRecycleViewForGames = false;


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                isGoogleCheckerFinished = 2;
            }
        });

        request.setShouldCache(false);
        requestMyIP.add(request);
    }




}

