package com.Futebol_TV.AO.VIVO.NodoGO;

import android.app.ActivityManager;
import android.app.Application;
import android.widget.Toast;

import com.Futebol_TV.AO.VIVO.NodoGO.CurrentModels.g_u_i_d_e_model;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_containing_player_utils.model_helper;
import com.android.volley.RequestQueue;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class App_go_DAISY extends Application  {
    public static RequestQueue requestQueue;
    public static ArrayList<g_u_i_d_e_model> g_u_i_d_e_models =new ArrayList<>();
    public static Set<String> Allowed_url_strings =new HashSet<>();
    public static int jsonActChecker =0;
    public static int myCounter=0;

    public static ArrayList<model_helper> model_helpers =new ArrayList<>();

    public static boolean isAdReady=true;

    public static boolean isGoogleHere = false;


    @Override
    public void onCreate() {
        super.onCreate();
        try {

            //UnityAds Has an Error, When The app Has Cleared Data and cache, Banner and Inter works fine
            //After Launching the App, some Data get stored, making UnityAds Not working on its second launch
            //So we need to clear Data Each time the App gets closed or destroyed
//            if(!isAppRunning()){
//                if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
//                    try {
//                        ((ActivityManager)this.getSystemService(ACTIVITY_SERVICE))
//                                .clearApplicationUserData(); // note: it has a return value!
//
//                    }
//                    catch (Exception ex){
//                        Toast.makeText(this,"Error at Activity Service"+ex,Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
//            }



//            queue = Volley.newRequestQueue(this);
//            getData();

            String ONESIGNAL_APP_ID = getString(R.string.One_Signal_ID);


            // Enable verbose OneSignal logging to debug issues if needed.
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

            // OneSignal Initialization
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ONESIGNAL_APP_ID);

            // promptForPushNotifications will show the native Android notification permission prompt.
            // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
            OneSignal.promptForPushNotifications();


        }
        catch (Exception ex){
            Toast.makeText(App_go_DAISY.this,"Unexpected Error",Toast.LENGTH_LONG).show();
        }
    }



//    private void getData(){
//        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, AllVariables.myJsonUrl, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//
//                            JSONArray jsonGuidesArray=response.getJSONArray("Guide");
//                            JSONArray jsonAppConfigArray=response.getJSONArray("App_Config");
//                            JSONArray jsonGamesConfigArray=response.getJSONArray("Games_Config");
//                            JSONArray jsonAdsConfigArray =response.getJSONArray("Ads_Units");
//                            JSONArray jsonCustomRedirectArray=response.getJSONArray("Redirect");
//                            JSONArray jsonAllowedUrlArray=response.getJSONArray("Host");
//
//
//                            for(int i=0;i<jsonGuidesArray.length();i++){
//                                JSONObject step=jsonGuidesArray.getJSONObject(i);
//                                String title=step.getString("title");
//                                String text=step.getString("text");
//                                String image=step.getString("image");
//                                g_u_i_d_e_model modelForNextArrow =new g_u_i_d_e_model(title,text,image);
//                                modelGuideList.add(modelForNextArrow);
//                            }
//
//
//
//                            JSONObject AppConfig= jsonAppConfigArray.getJSONObject(0);
//
//                            AllVariables.SelectedAd = AppConfig.getString("Active_Ad");
//                            AllVariables.AdsStyle = AppConfig.getString("Ads_Style");
//                            AllVariables.InterStyle = AppConfig.getString("Inter_Style");
//                            AllVariables.InterCounter = AppConfig.getInt("Inter_Counter");
//                            AllVariables.Secondary_Inter_Type = AppConfig.getString("Secondary_Inter_Type");
//                            AllVariables.TheInterIndex = AppConfig.getInt("Inter_Ads_Index");
//                            AllVariables.SelectMainActivityType = AppConfig.getString("mainActivityType");
//                            AllVariables.UrlOfThePrivacy = AppConfig.getString("privacy_url");
//                            AllVariables.UrlForTheStore = AppConfig.getString("pub_store_url");
//                            AllVariables.SkipTheSteps = AppConfig.getBoolean("skip_steps");
//                            AllVariables.SkipTheGuide = AppConfig.getBoolean("skip_guide");
//                            AllVariables.WV_Url = AppConfig.getString("webview_url");
//                            AllVariables.Duration_Time_For_Ads = AppConfig.getInt("Time");
//                            AllVariables.Duration_Time_For_Rate = AppConfig.getInt("rating_popup_timer");
//                            AllVariables.TheRatingDisplayedMessage = AppConfig.getString("rating_popup_Message");
//                            AllVariables.VideoUrlPlay = AppConfig.getString("main_video_url");
//                            AllVariables.is_CPA_Offer_Active = AppConfig.getBoolean("is_CPA_Offer_Active");
//                            AllVariables.CPA_title = AppConfig.getString("CPA_title");
//                            AllVariables.CPA_Description = AppConfig.getString("CPA_Description");
//                            AllVariables.CPA_link = AppConfig.getString("CPA_link");
//                            AllVariables.CPA_Timer = AppConfig.getInt("CPA_Timer");
//                            AllVariables.is_AdBlocker_Active =AppConfig.getBoolean("is_AdBlocker_Active");
//                            AllVariables.is_Support_Multiple_Windows_Active =AppConfig.getBoolean("is_Support_Multiple_Windows_Active");
//                            AllVariables.is_Ad_Background_Active=AppConfig.getBoolean("is_Ad_Background_Active");
//
//
//                            //Play Games Activity
//                            JSONObject gameConfig= jsonGamesConfigArray.getJSONObject(0);
//                            AllVariables.Activate_Play_Games=gameConfig.getBoolean("Activate_Play_Games");
//                            JSONArray jsonGamesList=gameConfig.getJSONArray("Games");
//
//                            for(int i=0;i<jsonGamesList.length();i++){
//                                JSONObject step=jsonGamesList.getJSONObject(i);
//                                String Game_Name=step.getString("Game_Name");
//                                String Game_Description=step.getString("Game_Description");
//                                String Game_Icon=step.getString("Game_Icon");
//                                String Game_Url=step.getString("Game_Url");
//                                String Game_bg_Img=step.getString("Game_bg_Img");
//                                model_helper gamesModel=new model_helper(Game_Name,Game_Description,Game_Icon,Game_bg_Img,Game_Url);
//                                gamerModeModels.add(gamesModel);
//                            }
////                            gameConfig.getString()
//
//                            //Mix Selected Ads
//                            AllVariables.MixedSelectedAds= Arrays.asList(AllVariables.SelectedAd.split(";"));
//
//                            if(AllVariables.AdsStyle.equals("mix")){
//                                AllVariables.InterType=AllVariables.MixedSelectedAds.get(0);
//                                AllVariables.BannerType=AllVariables.MixedSelectedAds.get(1);
//                                AllVariables.NativeType=AllVariables.MixedSelectedAds.get(2);
//                            }
//                            else {
//                                AllVariables.InterType=AllVariables.MixedSelectedAds.get(0);
//                                AllVariables.BannerType=AllVariables.MixedSelectedAds.get(0);
//                                AllVariables.NativeType=AllVariables.MixedSelectedAds.get(0);
//                            }
//
//
//
//                            JSONObject AdsConfig= jsonAdsConfigArray.getJSONObject(0);
//
//                            //max
//                            AllVariables.MaxFirstIndex = AdsConfig.getString("max_inter_first");
//                            AllVariables.MaxSecondIndex = AdsConfig.getString("max_inter_second");
//                            AllVariables.MaxThirdIndex = AdsConfig.getString("max_inter_third");
//                            AllVariables.MaxFourthIndex = AdsConfig.getString("max_inter_fourth");
//                            AllVariables.MaxBanner = AdsConfig.getString("max_banner");
//                            AllVariables.MaxNative = AdsConfig.getString("max_native");
//
//                            //fb
//                            AllVariables.Fan_inter = AdsConfig.getString("fb_inter");
//                            AllVariables.Fan_banner = AdsConfig.getString("fb_banner");
//                            AllVariables.Fan_native = AdsConfig.getString("fb_native");
//
//                            //admob
//                            AllVariables.admob_inter_id = AdsConfig.getString("admob_inter");
//                            AllVariables.admob_banner_id = AdsConfig.getString("admob_banner");
//                            AllVariables.admob_native_id = AdsConfig.getString("admob_native");
//
//                            //yandex
//                            AllVariables.Yandex_inter = AdsConfig.getString("yandex_inter");
//                            AllVariables.Yandex_Banner = AdsConfig.getString("yandex_banner");
//
//                            //Unity
//                            AllVariables.UnityGameId= AdsConfig.getString("UnityGameId");
//                            AllVariables.unity_banner= AdsConfig.getString("unity_banner");
//                            AllVariables.unity_inter= AdsConfig.getString("unity_inter");
//
//                            JSONObject redirectModel= jsonCustomRedirectArray.getJSONObject(0);
//
//                            //Custom Redirect Activity
//                            AllVariables.ImgUrl = redirectModel.getString("img");
//                            AllVariables.ButtonMessage = redirectModel.getString("text_btn");
//                            AllVariables.UrlToGoTo = redirectModel.getString("url_to_redirect");
//                            AllVariables.ShowActivityOfRedirection = redirectModel.getBoolean("showRedirectActivity");
//
////                            for(int i=0;i<jsonAllowedUrlArray.length();i++){
////
////                                JSONObject allowed_url=jsonAllowedUrlArray.getJSONObject(i);
////                                String title=allowed_url.getString("allowed_url");
////                                Allowed_url.add(title);
////
////                            }
////                            JSONArray QuestionsArray=QuestionsObject.getJSONArray("questions");
//                            for (int i = 0; i < jsonAllowedUrlArray.length(); i++) {
//                                String title = jsonAllowedUrlArray.getString(i);
//                                Allowed_url.add(title);
//                            }
//
//                            isJsonDone=1;
//
//                        } catch (JSONException e) {
//                            Toast.makeText(App_go_DAISY.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                isJsonDone=2;
//                error.printStackTrace();
//            }
//        });
//
//        request.setShouldCache(false);
//        queue.add(request);
//
//    }

    private boolean isAppRunning() {
        ActivityManager m = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList =  m.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        int n=0;
        while(itr.hasNext()){
            n++;
            itr.next();
        }
        if(n==1){ // App is killed
            return false;
        }

        return true; // App is in background or foreground
    }


}
