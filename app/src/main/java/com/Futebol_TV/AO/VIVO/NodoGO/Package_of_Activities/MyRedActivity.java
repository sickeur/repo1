package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;
import com.bumptech.glide.Glide;

public class MyRedActivity extends AppCompatActivity {

    Button redirectButton;
    ImageView imageView;

    Network_Receiveer networkSpyListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_red);
        networkSpyListener =new Network_Receiveer();


        redirectButton=findViewById(R.id.btnAction);
        imageView=findViewById(R.id.imgAds);

        Glide.with(this).load(AllVariables.FullScreenImgUrl).into(imageView);
        redirectButton.setText(AllVariables.TextBtnMessage);
        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,  Uri.parse(AllVariables.urlOfRedirection));
                MyRedActivity.this.startActivity(intent);
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