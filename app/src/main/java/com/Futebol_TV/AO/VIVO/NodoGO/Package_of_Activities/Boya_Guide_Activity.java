package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;
import com.bumptech.glide.Glide;
import com.yandex.mobile.ads.banner.BannerAdView;

public class Boya_Guide_Activity extends AppCompatActivity {

    ImageView MoveNext, MovePrevious, simple_IV;
    TextView H1_Tv, Paragraph_TV;
    int position=0;
    private Dialog adsUiLoader;

    RelativeLayout bannerContainer;
    BannerAdView yandex_The_Banner;

    API_Manager adminAPI;

    Network_Receiveer networkSpyListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boya_guide);

        networkSpyListener =new Network_Receiveer();

        bannerContainer=findViewById(R.id.LayoutBanner);
        yandex_The_Banner =findViewById(R.id.yandex_banner);

        MoveNext =findViewById(R.id.imgNext);
        MovePrevious =findViewById(R.id.imgPrevious);
        H1_Tv =findViewById(R.id.guide_title);
        Paragraph_TV =findViewById(R.id.guide_description);
        simple_IV =findViewById(R.id.guide_image);

        //The Loading Dialogue While Waiting For the Ads
        adsUiLoader = new Dialog(Boya_Guide_Activity.this);
        adsUiLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
        adsUiLoader.setContentView(R.layout.chargement_layout);
        adsUiLoader.setCanceledOnTouchOutside(false);

        adminAPI =new API_Manager(this);
        adminAPI.SelectedApiForAds();
        adminAPI.showBanner(bannerContainer, yandex_The_Banner);

        H1_Tv.setText(App_go_DAISY.g_u_i_d_e_models.get(position).getMyTitle());
        Paragraph_TV.setText(App_go_DAISY.g_u_i_d_e_models.get(position).getMyDescription());
        Glide.with(this).load(App_go_DAISY.g_u_i_d_e_models.get(position).getMyImage()).into(simple_IV);

        MoveNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>= App_go_DAISY.g_u_i_d_e_models.size()-1){
                    if(AllVariables.ActiveRedirectOrNot){
                        Intent intent=new Intent(Boya_Guide_Activity.this, MyRedActivity.class);
                        startActivity(intent);
                        return;
                    }
                    adminAPI.showInter(new API_Manager.EndInter() {
                        @Override
                        public void onEndInter() {
                            Intent intent=new Intent(Boya_Guide_Activity.this, First_WebV_Prime_Activity.class);
                            startActivity(intent);
                        }
                    });

                }
                else{
                    Inter_On_Next();
                }
            }
        });

        MovePrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inter_On_Back();
            }
        });

    }
    private void Go_Next(){
        position++;
        if(position>= App_go_DAISY.g_u_i_d_e_models.size()-1) position= App_go_DAISY.g_u_i_d_e_models.size()-1;
        H1_Tv.setText(App_go_DAISY.g_u_i_d_e_models.get(position).getMyTitle());
        Paragraph_TV.setText(App_go_DAISY.g_u_i_d_e_models.get(position).getMyDescription());
        Glide.with(this).load(App_go_DAISY.g_u_i_d_e_models.get(position).getMyImage()).into(simple_IV);
    }

    private void Go_Previous(){
        position--;
        if(position<=0) position=0;
        H1_Tv.setText(App_go_DAISY.g_u_i_d_e_models.get(position).getMyTitle());
        Paragraph_TV.setText(App_go_DAISY.g_u_i_d_e_models.get(position).getMyDescription());
        Glide.with(this).load(App_go_DAISY.g_u_i_d_e_models.get(position).getMyImage()).into(simple_IV);
    }

    private void Inter_On_Next(){
        adsUiLoader.show();
        adminAPI.showInter(new API_Manager.EndInter() {
            @Override
            public void onEndInter() {
                adsUiLoader.dismiss();
                Go_Next();
            }
        });
    }

    private void Inter_On_Back(){
        adsUiLoader.show();
        adminAPI.showInter(new API_Manager.EndInter() {
            @Override
            public void onEndInter() {
                adsUiLoader.dismiss();
                Go_Previous();
            }
        });
    }


    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkSpyListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkSpyListener);
        super.onStop();
    }
}