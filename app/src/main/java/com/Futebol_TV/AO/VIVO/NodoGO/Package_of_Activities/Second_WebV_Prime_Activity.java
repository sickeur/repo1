package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Blocker_Checker;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.BlockerHelperClass;
import com.Futebol_TV.AO.VIVO.NodoGO.R;

public class Second_WebV_Prime_Activity extends AppCompatActivity {

    private WebView cashing_browser;
    private View DecorationView;
    private Dialog myLoader;
    API_Manager adsApi;
    Network_Receiveer networkSpyListener;

    public static SharedPreferences prefs = null;
    Popup The_Popup;
    Handler T1_Handler;

    static boolean IsItActive =false;

    String url;

    private Dialog CPA_Popup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_webv_prime);
        cashing_browser=findViewById(R.id.gameLauncher);

        networkSpyListener =new Network_Receiveer();

        adsApi=new API_Manager(Second_WebV_Prime_Activity.this);


        //CPA
        CPA_Popup = new Dialog(Second_WebV_Prime_Activity.this);
        CPA_Popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        CPA_Popup.setContentView(R.layout.cpa_layout);
        CPA_Popup.setCanceledOnTouchOutside(false);
        CPA_Popup.setCancelable(false);
        final Button EXIT = CPA_Popup.findViewById(R.id.exit);
        final Button Offer = CPA_Popup.findViewById(R.id.offer);
        final TextView cpa_title = CPA_Popup.findViewById(R.id.cpa_title);
        final TextView cpa_desctiption = CPA_Popup.findViewById(R.id.cpa_desctiption);
        cpa_title.setText(AllVariables.CPA_title);
        cpa_desctiption.setText(AllVariables.CPA_Description);
        EXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Second_WebV_Prime_Activity.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to Exit?")
                        .setIcon(R.drawable.new_icon)
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Second_WebV_Prime_Activity.super.onBackPressed();
                            }
                        }).create().show();
            }
        });

        Offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(AllVariables.CPA_link));
                Second_WebV_Prime_Activity.this.startActivity(intent);
                CPA_Popup.dismiss();
            }
        });

        //This will Hide Nav And Status Bar
        DecorationView =getWindow().getDecorView();
        DecorationView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0){
                    DecorationView.setSystemUiVisibility(Hide_The_System_Bars());
                }
            }
        });

        //The Loading Dialogue While Waiting For the Ads
        myLoader = new Dialog(Second_WebV_Prime_Activity.this);
        myLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myLoader.setContentView(R.layout.chargement_layout);
        myLoader.setCanceledOnTouchOutside(false);
        TextView loading_tv= myLoader.findViewById(R.id.loading_TV);
        loading_tv.setText("Loading Resources");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        cashing_browser.setVisibility(View.VISIBLE);
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        //Instantiating the Rate us Dialogue
        The_Popup =new Second_WebV_Prime_Activity.Popup(Second_WebV_Prime_Activity.this);
        The_Popup.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        The_Popup.setCancelable(false);

        if(prefs.getBoolean("firstrun", true)){

            T1_Handler =new Handler();
            T1_Handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(IsItActive){
                        try {
                            The_Popup.show();
                        }
                        catch (Exception exception){

                        }
                    }
                }
            }, AllVariables.MyDurationTimerForRate);
        }


        if(AllVariables.ActivateCPA){
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        CPA_Popup.show();
                    }
                    catch (Exception ex){

                    }

                }
            }, AllVariables.CPA_Timer);

        }

        adsApi.SelectedApiForAds();
        new BlockerHelperClass.init(this).initializeWebView(cashing_browser);
        Blocker_Checker.AD_HOSTS.addAll(App_go_DAISY.Allowed_url_strings);

        cashing_browser.clearCache(true);
        cashing_browser.clearHistory();
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            url=extras.getString("Url");
            cashing_browser.loadUrl(url);

        }

        cashing_browser.setWebViewClient(new Browser_home());
        cashing_browser.setWebChromeClient(new MyChrome());
        WebSettings webSettings= cashing_browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webSettings.setDisplayZoomControls(false);
        }

        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        if (AllVariables.Supporting_Multiple_Windows) {
            webSettings.setSupportMultipleWindows(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        }

        //This will allow the app to open ads on another tab

        showAds();

    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkSpyListener,filter);
        IsItActive =true;
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkSpyListener);
        IsItActive =false;
        super.onStop();
    }

    public void showAds(){
        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ShowTheChosenInter();
                }
            }, AllVariables.AdsTimerInWV);
        }
        catch (Exception exception){

        }

    }

    public void ShowTheChosenInter(){
        try {
            myLoader.show();
            cashing_browser.onPause();
            adsApi.showInter(new API_Manager.EndInter() {
                @Override
                public void onEndInter() {
                    myLoader.dismiss();
                    cashing_browser.onResume();
                    showAds();
                }
            });
        }
        catch (Exception ex){

        }
    }

    public int Hide_The_System_Bars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            DecorationView.setSystemUiVisibility(Hide_The_System_Bars());
        }
    }

    private class Browser_home extends WebViewClient {

        Browser_home() {}

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if(AllVariables.AdblockerActive){
                return !BlockerHelperClass.blockAds(view,url) ? Blocker_Checker.createEmptyResource() :
                        super.shouldInterceptRequest(view, url);
            }

            return super.shouldInterceptRequest(view, url);
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }



        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }


    private class MyChrome extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        cashing_browser.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cashing_browser.restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {

            if(cashing_browser.canGoBack()){
                cashing_browser.goBack();

            }
            else {
                cashing_browser.destroy();
                super.onBackPressed();
            }

    }

    public class Popup extends Dialog {

        public  float userRate=0;
        private AppCompatActivity mActivity;

        public Popup(@NonNull AppCompatActivity context) {
            super(context);
            this.mActivity =context;

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layour_rating);

            final Button button_rateUs = findViewById(R.id.goToRateUs);
            final Button button_later = findViewById(R.id.leave);
            final RatingBar ratingBar= findViewById(R.id.stars);
            final ImageView imageView= findViewById(R.id.emoticon);
            final TextView message=findViewById(R.id.textMessage);
            message.setText(AllVariables.RatingDialogue);

            button_rateUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(userRate>2){
                            dismiss();
                            Second_WebV_Prime_Activity.prefs.edit().putBoolean("firstrun", false).commit();
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ mActivity.getPackageName()));
                            mActivity.startActivity(intent);
                        }
                        else{
                            Second_WebV_Prime_Activity.prefs.edit().putBoolean("firstrun", true).commit();
                            dismiss();
                            Toast.makeText(mActivity,"Thanks for your rating!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(mActivity,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                    animateImage(imageView);

                    userRate=rating;
                }
            });


        }


        private void animateImage(ImageView imageView){
            ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(200);
            imageView.startAnimation(scaleAnimation);
        }
    }

}