package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.CurrentModels.s_t_e_p_s_model;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;
import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.bumptech.glide.Glide;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class Beginning_Activity extends AppCompatActivity  {

    GifImageView GifIV1, GifIV2;
    TextView titre, titre_image_1, titre_image_2;

    RelativeLayout BannerRL;
    LinearLayout NativeLL;

    BannerAdView BannerAdView;
    NativeBannerView YandexAdNative;

    API_Manager api_manager;

    ArrayList<s_t_e_p_s_model> s_t_e_p_s_models =new ArrayList<>();
    int po =0;
    private Dialog dialog;

    Network_Receiveer network_receiveer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);

        App_go_DAISY.isAdReady=true;

        network_receiveer =new Network_Receiveer();

        BannerRL =findViewById(R.id.LayoutBanner);
        NativeLL =findViewById(R.id.My_Native_Wrapper);
        BannerAdView =findViewById(R.id.yandex_banner);
        YandexAdNative =findViewById(R.id.Yandex_Native_Container);

        titre =findViewById(R.id.selectTV);
        titre_image_1 =findViewById(R.id.text1);
        titre_image_2 =findViewById(R.id.text2);
        GifIV1 =findViewById(R.id.IVselect1);
        GifIV2 =findViewById(R.id.IVselect2);

        //The Loading Dialogue While Waiting For the Ads
        dialog = new Dialog(Beginning_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chargement_layout);
        dialog.setCanceledOnTouchOutside(false);

        gettingTheModel(s_t_e_p_s_models);
        gettingTheStep(po);

        api_manager =new API_Manager(this);
        api_manager.SelectedApiForAds();
        api_manager.showBanner(BannerRL, BannerAdView);
        api_manager.showNative(NativeLL);

        GifIV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goN();
            }
        });

        GifIV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goN();
            }
        });

    }

    public void goN(){
        dialog.show();
        api_manager.showInter(new API_Manager.EndInter() {
            @Override
            public void onEndInter() {
                dialog.dismiss();
                goP();
            }
        });
    }

    private void goP(){
        po++;
        if(po < s_t_e_p_s_models.size()){
            gettingTheStep(po);
        }
        else {
            Intent intent=new Intent(Beginning_Activity.this, Activity2.class);
            this.startActivity(intent);
        }

    }

    private void gettingTheStep(int position){
        try {
            titre.setText(s_t_e_p_s_models.get(position).getPleaseSelect_TV());
            titre_image_1.setText(s_t_e_p_s_models.get(position).getChoice_1());
            titre_image_2.setText(s_t_e_p_s_models.get(position).getChoice_2());
            Glide.with(this).load(s_t_e_p_s_models.get(position).getImageView1()).circleCrop().into(GifIV1);
            Glide.with(this).load(s_t_e_p_s_models.get(position).getImageView2()).circleCrop().into(GifIV2);
        }
        catch (Exception ex){
            Toast.makeText(Beginning_Activity.this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void gettingTheModel(ArrayList<s_t_e_p_s_model> theStepsonModelForTheSteps){
        theStepsonModelForTheSteps.add(new s_t_e_p_s_model("Please select your gender","Male","Female", R.drawable.rajel,R.drawable.mraaaa));
        theStepsonModelForTheSteps.add(new s_t_e_p_s_model("Please select your language","English","Spanish", R.drawable.england,R.drawable.spania));
        theStepsonModelForTheSteps.add(new s_t_e_p_s_model("Are you 18+ ?","Above 18","Under 18", R.drawable.eighteen_super,R.drawable.not_super_eighteen));
        theStepsonModelForTheSteps.add(new s_t_e_p_s_model("Select the Human","Human","Robot", R.drawable.not_a_robot,R.drawable.the_cute_robooot));
        theStepsonModelForTheSteps.add(new s_t_e_p_s_model("Select your closest Server","U.S.A","Europe", R.drawable.captain_america_flag,R.drawable.oropa));
//        theStepsonModelForTheSteps.add(new s_t_e_p_s_model("Select internet Connection type","WiFi","Mobile Data", R.drawable.wifi_icon,R.drawable.three_g));
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