package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Rating;
import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;

import java.net.MalformedURLException;
import java.net.URL;

public class App_Main_Menu extends AppCompatActivity {

    ConsentForm consentForm;

    RelativeLayout bannerContainer;
    LinearLayout nativeAdContainer;

    BannerAdView yandexBanner;
    NativeBannerView yandexNative;

    Button lets_start, lets_rate, lets_go_to_more_apps, lets_privacy, lets_play_games;
    private Dialog dialog;
    Rating rating;

    API_Manager api_manager;

    Network_Receiveer network_receiveer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main_menu);

        network_receiveer =new Network_Receiveer();

        bannerContainer=findViewById(R.id.LayoutBanner);
        nativeAdContainer=findViewById(R.id.My_Native_Wrapper);

        yandexBanner=findViewById(R.id.yandex_banner);
        yandexNative=findViewById(R.id.Yandex_Native_Container);

        //The Loading Dialogue While Waiting For the Ads
        dialog = new Dialog(App_Main_Menu.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chargement_layout);
        dialog.setCanceledOnTouchOutside(false);

        //Instantiating the Rate us Dialogue
        rating =new Rating(App_Main_Menu.this);
        rating.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rating.setCancelable(false);

        api_manager =new API_Manager(this);
        api_manager.SelectedApiForAds();
        api_manager.showBanner(bannerContainer,yandexBanner);
        api_manager.showNative(nativeAdContainer);

        gdpr();

        rating.show();

        lets_start =findViewById(R.id.start);
        lets_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                api_manager.showInter(new API_Manager.EndInter() {
                    @Override
                    public void onEndInter() {
                        dialog.dismiss();

                        if(AllVariables.SkipTheGuide){

                            if(AllVariables.ActiveRedirectOrNot){
                                Intent intent=new Intent(App_Main_Menu.this, MyRedActivity.class);
                                startActivity(intent);
                                return;
                            }
                            Intent intent=new Intent(App_Main_Menu.this, First_WebV_Prime_Activity.class);
                            startActivity(intent);

                        }
                        else{

                            Intent intent=new Intent(App_Main_Menu.this, Boya_Guide_Activity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        lets_play_games =findViewById(R.id.play_games);

        if(AllVariables.ActivateRecycleViewForGames) lets_play_games.setVisibility(View.VISIBLE);

        lets_play_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                api_manager.showInter(new API_Manager.EndInter() {
                    @Override
                    public void onEndInter() {
                        dialog.dismiss();
                        Intent intent=new Intent(App_Main_Menu.this, List_Of_WebSites_Activity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        lets_rate =findViewById(R.id.Please_Rate_Me_Button);
        lets_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating.show();
            }
        });

        lets_privacy =findViewById(R.id.privacy);
        lets_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(AllVariables.ThePrivacyUrl));
                App_Main_Menu.this.startActivity(intent);
            }
        });

        lets_go_to_more_apps =findViewById(R.id.more_apps);
        lets_go_to_more_apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,  Uri.parse(AllVariables.ThePubUrl));
                App_Main_Menu.this.startActivity(intent);
            }
        });
    }

    public void gdpr(){

        ///////////////////////GDPR Badr////////////////////////////
        ConsentInformation consentInformation = ConsentInformation.getInstance(App_Main_Menu.this);

        String[] publisherIds = {"pub-5353669282093267"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
// User's consent status successfully updated.
                switch (consentStatus){
                    case PERSONALIZED:
                        ConsentInformation.getInstance(App_Main_Menu.this)
                                .setConsentStatus(ConsentStatus.PERSONALIZED);
                        break;
                    case NON_PERSONALIZED:
                        ConsentInformation.getInstance(App_Main_Menu.this)
                                .setConsentStatus(ConsentStatus.NON_PERSONALIZED);
                        break;

                    case UNKNOWN:
                        if
                        (ConsentInformation.getInstance(App_Main_Menu.this).isRequestLocationInEeaOrUnknown
                                ()){
                            URL privacyUrl = null;
                            try {

                                privacyUrl = new URL(AllVariables.ThePrivacyUrl);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
// Handle error.
                            }
                            consentForm = new ConsentForm.Builder(App_Main_Menu.this,privacyUrl)
                                    .withListener(new ConsentFormListener() {
                                        @Override
                                        public void onConsentFormLoaded() {
                                            // Consent form loaded successfully.
                                            showform();
                                        }
                                        @Override
                                        public void onConsentFormOpened() {
                                            // Consent form was displayed.
                                        }
                                        @Override
                                        public void onConsentFormClosed( ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                                            // Consent form was closed.

                                        }
                                        @Override
                                        public void onConsentFormError(String errorDescription) {
                                            // Consent form error.
                                        }
                                    })
                                    .withPersonalizedAdsOption()
                                    .withNonPersonalizedAdsOption()
                                    .build();
                            consentForm.load();
                        } else {
                            ConsentInformation.getInstance(App_Main_Menu.this)
                                    .setConsentStatus(ConsentStatus.PERSONALIZED);
                        }
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.

            }

            private void showform(){
                if (consentForm !=null){

                    consentForm.show();
                }
            }
        });

        //////////////////////GDPR Badr/////////////////////////////////////



    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(network_receiveer,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(network_receiveer);
        super.onStop();
    }
}