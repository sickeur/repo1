package com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.Futebol_TV.AO.VIVO.NodoGO.R;

public class Applovin_Max_Ads {


    //Native
    private MaxAd nativeAd;

    //Banner
    private MaxAdView adView;

    private final AppCompatActivity mActivity;
    public boolean isCompleted=false;
    public boolean isClosed=false;




    public interface AdFinished{
        void onAdFinished();
    }

    public Applovin_Max_Ads(AppCompatActivity appCompatActivity){
        this.mActivity =appCompatActivity;
    }



    public void show_four_inter(final AdFinished adFinished){
        try {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(AllVariables.Max4, mActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    show_three_inter(adFinished);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            show_three_inter(adFinished);
        }

    }

    public void show_three_inter(final AdFinished adFinished){
        try{
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(AllVariables.Max3, mActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    show_two_inter(adFinished);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            show_two_inter(adFinished);
        }
    }

    public void show_two_inter(final AdFinished adFinished){
        try{
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(AllVariables.Max2, mActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    show_one_inter(adFinished);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            show_one_inter(adFinished);
        }
    }

    public void show_one_inter(final AdFinished adFinished){
        try{
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(AllVariables.Max1, mActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            adFinished.onAdFinished();
        }
    }



    public void showBanner(RelativeLayout bannerContainer) {
        adView=new MaxAdView(AllVariables.MaxBanner, mActivity);
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

                int width= ViewGroup.LayoutParams.MATCH_PARENT;
                int height= mActivity.getResources().getDimensionPixelOffset(R.dimen.max_banner_height);

                adView.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
                adView.setBackgroundColor(ContextCompat.getColor(mActivity,R.color.white));
                bannerContainer.addView(adView);
                bannerContainer.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });

        adView.loadAd();
    }



    public void createNativeAd(LinearLayout nativeAdContainer)
    {

        MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader( AllVariables.MaxNative, mActivity);
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
            }
        } );

        nativeAdLoader.loadAd();
    }

    public void showReward(AdFinished adFinished){
        isCompleted=false;
        isClosed=false;
        MaxRewardedAd rewardedAd = MaxRewardedAd.getInstance( AllVariables.MaxReward, mActivity);
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {
                isCompleted=true;
            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                rewardedAd.showAd();

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                isClosed=true;
                adFinished.onAdFinished();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                adFinished.onAdFinished();
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                adFinished.onAdFinished();
            }
        });

        rewardedAd.loadAd();
    }

    public boolean checkRewardAdCompleted(){
        if(isClosed && isCompleted){
            isClosed=false;
            isCompleted=false;
            return true;
        }
        else{
            isClosed=false;
            isCompleted=false;
            return false;
        }
    }
}
