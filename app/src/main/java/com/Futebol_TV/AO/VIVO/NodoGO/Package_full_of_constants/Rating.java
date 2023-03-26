package com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.Futebol_TV.AO.VIVO.NodoGO.R;

public class Rating extends Dialog {

    public  float RatingScore =0;
    private AppCompatActivity mContext;

    Dialog load;


    public Rating(@NonNull AppCompatActivity mContext) {
        super(mContext);
        this.mContext = mContext;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layour_rating);

        final Button button_rateUs = findViewById(R.id.goToRateUs);
        final Button button_later = findViewById(R.id.leave);
        final RatingBar ratingBar= findViewById(R.id.stars);
        final ImageView imageView= findViewById(R.id.emoticon);

        //The Loading Dialogue While Waiting For the Ads
        load = new Dialog(mContext);
        load.requestWindowFeature(Window.FEATURE_NO_TITLE);
        load.setContentView(R.layout.chargement_layout);
        load.setCanceledOnTouchOutside(true);


        button_rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(RatingScore >2){
                        dismiss();
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ mContext.getPackageName()));
                        mContext.startActivity(intent);
                    }
                    else{
                        dismiss();
                        Toast.makeText(mContext,"Thanks for your rating!",Toast.LENGTH_SHORT).show();
                    }

                }
                catch (ActivityNotFoundException e){
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id="));
                    mContext.startActivity(intent);
                }
            }
        });

        button_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AllVariables.is_Forced_to_rate){
                    dismiss();
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating<=1){
                    imageView.setImageResource(R.drawable.one_super_star);
                }
                else if(rating<=2){
                    imageView.setImageResource(R.drawable.two_super_star);

                }
                else if(rating<=3){
                    imageView.setImageResource(R.drawable.three_super_star);

                }
                else if(rating<=4){
                    imageView.setImageResource(R.drawable.four_super_star);

                }
                else if(rating<=5){
                    imageView.setImageResource(R.drawable.five_super_star);

                }
                //This is making my Emoji Animated
                animateEmoticon(imageView);

                RatingScore =rating;
            }
        });

    }

    private void animateEmoticon(ImageView imageView){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        imageView.startAnimation(scaleAnimation);
    }

}
