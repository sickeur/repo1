package com.Futebol_TV.AO.VIVO.NodoGO.Package_for_Ad_Networks;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;
import com.yandex.mobile.ads.nativeads.template.SizeConstraint;
import com.yandex.mobile.ads.nativeads.template.appearance.BannerAppearance;
import com.yandex.mobile.ads.nativeads.template.appearance.ButtonAppearance;
import com.yandex.mobile.ads.nativeads.template.appearance.ImageAppearance;
import com.yandex.mobile.ads.nativeads.template.appearance.NativeTemplateAppearance;
import com.yandex.mobile.ads.nativeads.template.appearance.RatingAppearance;
import com.yandex.mobile.ads.nativeads.template.appearance.TextAppearance;

public class Yandex_Vungle_Ads {



    public interface AdFinished{
        void onAdFinished();
    }

    //Native
    private MaxAd nativeAd;

    private final AppCompatActivity mActivity;

    public Yandex_Vungle_Ads(AppCompatActivity context){
        this.mActivity =context;

    }

    public void showBanner(BannerAdView bannerContainer){
        bannerContainer.setAdUnitId(AllVariables.YandexBannerId);
        bannerContainer.setVisibility(View.VISIBLE);
        bannerContainer.setAdSize(com.yandex.mobile.ads.banner.AdSize.stickySize(AdSize.FULL_SCREEN.getWidth()));

        final AdRequest adRequest=new AdRequest.Builder().build();

        bannerContainer.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {

            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onLeftApplication() {

            }

            @Override
            public void onReturnedToApplication() {

            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });

        bannerContainer.loadAd(adRequest);

    }


    public void showInter(AdFinished adFinished){
        InterstitialAd interstitialAd=new InterstitialAd(mActivity);
        interstitialAd.setAdUnitId(AllVariables.YandexInterId);

        final AdRequest adRequest=new AdRequest.Builder().build();

        interstitialAd.setInterstitialAdEventListener(new InterstitialAdEventListener() {
            @Override
            public void onAdLoaded() {
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
                adFinished.onAdFinished();

            }

            @Override
            public void onAdShown() {

            }

            @Override
            public void onAdDismissed() {
                adFinished.onAdFinished();
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onLeftApplication() {

            }

            @Override
            public void onReturnedToApplication() {

            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });

        interstitialAd.loadAd(adRequest);

    }

    public void showNative(LinearLayout nativeAdContainer){

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

//    NativeAdLoader nativeAdLoader=new NativeAdLoader(mActivity);
//
//
//        final HashMap<String, String> parameters = new HashMap<String, String>(){{
//            put("preferable-height", "350");
//            put("preferable-width", "400");
//        }};
//
//
//        final NativeAdRequestConfiguration nativeAdRequestConfiguration =
//                new NativeAdRequestConfiguration.Builder("R-M-DEMO-native-i")
//                        .setParameters(parameters).build();
//
//        nativeAdLoader.setNativeAdLoadListener(new NativeAdLoadListener() {
//        @Override
//        public void onAdLoaded(@NonNull NativeAd nativeAd) {
//            Toast.makeText(mActivity,"Native Yandex Loaded Successfully",Toast.LENGTH_SHORT).show();
//            populateNative(nativeAdView);
//
//        }
//
//        @Override
//        public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
//            Toast.makeText(mActivity,"Native Yandex did not Load",Toast.LENGTH_SHORT).show();
//
//        }
//    });
//
//        nativeAdView.setVisibility(View.VISIBLE);
////        NativeAdLoader mNativeAdLoader = new NativeAdLoader(mActivity);
//        nativeAdLoader.loadAd(nativeAdRequestConfiguration);



    }

    private void populateNative(NativeBannerView nativeAdView){
        final NativeBannerView nativeBannerView = new NativeBannerView(mActivity);
        final NativeTemplateAppearance nativeTemplateAppearance =
                new NativeTemplateAppearance.Builder()
                        // Setting the color for the ad frame.
                        .withBannerAppearance(new BannerAppearance.Builder()
                                .setBorderColor(Color.YELLOW).build())

                        // Setting the button parameters.
                        .withCallToActionAppearance(new ButtonAppearance.Builder()
                                // Setting the font color and size for the action button label.
                                .setTextAppearance(new TextAppearance.Builder()
                                        .setTextColor(Color.BLUE)
                                        .setTextSize(14f).build())

                                // Setting the button color for the normal state and the clicked state.
                                .setNormalColor(Color.TRANSPARENT)
                                .setPressedColor(Color.GRAY)
                                // Setting the color and thickness of the button border.
                                .setBorderColor(Color.BLUE)
                                .setBorderWidth(1f).build())

                        // Setting the image width and the sizing constraint.
                        .withImageAppearance(new ImageAppearance.Builder()
                                .setWidthConstraint(new SizeConstraint(SizeConstraint
                                        .SizeConstraintType.FIXED, 60f)).build())

                        //  Setting the font size and color for the age restriction label.
                        .withAgeAppearance(new TextAppearance.Builder()
                                .setTextColor(Color.GRAY)
                                .setTextSize(12f).build())

                        // Setting the font size and color for the main ad text.
                        .withBodyAppearance(new TextAppearance.Builder()
                                .setTextColor(Color.GRAY)
                                .setTextSize(12f).build())

                        // Setting the color for filled stars in the rating.
                        .withRatingAppearance(new RatingAppearance.Builder()
                                .setProgressStarColor(Color.CYAN).build())

                        // Setting the font size and color for the ad title.
                        .withTitleAppearance(new TextAppearance.Builder()
                                .setTextColor(Color.BLACK)
                                .setTextSize(14f).build())

                        .build();

// Applying the settings.
        nativeBannerView.applyAppearance(nativeTemplateAppearance);

    }




}
