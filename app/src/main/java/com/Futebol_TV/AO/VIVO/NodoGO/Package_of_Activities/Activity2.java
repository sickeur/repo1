package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;


public class Activity2 extends AppCompatActivity {

    RelativeLayout bannerContainer;
    LinearLayout nativeAdContainer;

    BannerAdView yandexBanner;
    NativeBannerView yandexNative;

    API_Manager api_manager;
    private Dialog dialog;

    Button letsGoNext;
    Network_Receiveer network_receiveer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

         network_receiveer =new Network_Receiveer();

        App_go_DAISY.isAdReady=true;

        bannerContainer=findViewById(R.id.LayoutBanner);
        nativeAdContainer=findViewById(R.id.My_Native_Wrapper);
        yandexBanner=findViewById(R.id.yandex_banner);
        yandexNative=findViewById(R.id.Yandex_Native_Container);

        //The Loading Dialogue While Waiting For the Ads
        dialog = new Dialog(Activity2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chargement_layout);
        dialog.setCanceledOnTouchOutside(false);

        api_manager =new API_Manager(this);
        api_manager.SelectedApiForAds();
        api_manager.showBanner(bannerContainer,yandexBanner);
        api_manager.showNative(nativeAdContainer);

        letsGoNext =findViewById(R.id.next);
        letsGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                api_manager.showInter(new API_Manager.EndInter() {
                    @Override
                    public void onEndInter() {
                        dialog.dismiss();
                        Intent intent=new Intent(Activity2.this, App_Main_Menu.class);
                        startActivity(intent);
                    }
                });
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}