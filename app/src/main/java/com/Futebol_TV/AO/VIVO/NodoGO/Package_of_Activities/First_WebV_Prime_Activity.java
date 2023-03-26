package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_API.API_Manager;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.AllVariables;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.JoystickView;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Blocker_Checker;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.BlockerHelperClass;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants.Network_Receiveer;

public class First_WebV_Prime_Activity extends AppCompatActivity {

    VideoView myHeroVP;
    private View ZwakView;
    private Dialog adsUiLoader;
    private WebView TheWebS;
    API_Manager adsApi;
    public static SharedPreferences sharedP = null;
    myDamnRatingPopup maybeItsTheMyDamnRatingPopup;
    Handler aGodDamnHandler;
    static boolean ActiveChecker =false;

    Network_Receiveer networkSpyListener;

    private Dialog CPA_Popup;

    ImageButton run;
    ImageButton jump;
    ImageButton Settings;

    private JoystickView joystick;




    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_webv_prime);

        jump=findViewById(R.id.jumpBtn);
        run=findViewById(R.id.runBtn);
        Settings=findViewById(R.id.settings);
        joystick = (JoystickView) findViewById(R.id.joystickView);


        networkSpyListener =new Network_Receiveer();

        myHeroVP =findViewById(R.id.gameVideo);
        TheWebS =findViewById(R.id.simpleWView);
        adsApi=new API_Manager(First_WebV_Prime_Activity.this);

        //This will Hide Nav And Status Bar
        ZwakView =getWindow().getDecorView();
        ZwakView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0){
                    ZwakView.setSystemUiVisibility(Hide_The_System_Bars());
                }
            }
        });

        //The Loading Dialogue While Waiting For the Ads
        adsUiLoader = new Dialog(First_WebV_Prime_Activity.this);
        adsUiLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
        adsUiLoader.setContentView(R.layout.chargement_layout);
        adsUiLoader.setCanceledOnTouchOutside(false);
        TextView loading_tv= adsUiLoader.findViewById(R.id.loading_TV);
        loading_tv.setText("Loading Resources");

        //CPA
        CPA_Popup = new Dialog(First_WebV_Prime_Activity.this);
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
                new AlertDialog.Builder(First_WebV_Prime_Activity.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to Exit?")
                        .setIcon(R.drawable.new_icon)
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                First_WebV_Prime_Activity.super.onBackPressed();
                            }
                        }).create().show();
            }
        });

        Offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(AllVariables.CPA_link));
                First_WebV_Prime_Activity.this.startActivity(intent);
                CPA_Popup.dismiss();
            }
        });




        if(AllVariables.TypeOfMainActivity.equals("video")) {
            adsUiLoader.show();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        adsUiLoader.dismiss();
                        myHeroVP.start();
                    }
                    catch (Exception ex){

                    }

                }
            },5000);
        }



        if(AllVariables.TypeOfMainActivity.equals("video")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            myHeroVP.setVisibility(View.VISIBLE);
            myHeroVP.setVideoURI(Uri.parse(AllVariables.MyVideoUrl));
            myHeroVP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

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
                    else {
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(First_WebV_Prime_Activity.this, R.string.ToastFailMessage,Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(First_WebV_Prime_Activity.this, Beginning_Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        },2500);
                    }



                }
            });
        }
        else if(AllVariables.TypeOfMainActivity.equals("webview")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            TheWebS.setVisibility(View.VISIBLE);
            sharedP = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            //Instantiating the Rate us Dialogue
            maybeItsTheMyDamnRatingPopup =new myDamnRatingPopup(First_WebV_Prime_Activity.this);
            maybeItsTheMyDamnRatingPopup.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            maybeItsTheMyDamnRatingPopup.setCancelable(false);

            if(sharedP.getBoolean("firstrun", true)){

                    aGodDamnHandler =new Handler();
                    aGodDamnHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(ActiveChecker){
                                try {
                                maybeItsTheMyDamnRatingPopup.show();
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

            if(AllVariables.is_Joystick_active){
                jump.setVisibility(View.VISIBLE);
                run.setVisibility(View.VISIBLE);
                Settings.setVisibility(View.VISIBLE);
                joystick.setVisibility(View.VISIBLE);
            }



            jump.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int eventaction = event.getAction();

                    switch (eventaction) {
                        case MotionEvent.ACTION_DOWN:
                            TheWebS.evaluateJavascript("simulateKey(90,\"down\");",null);

                            return true;

                        case MotionEvent.ACTION_UP:
                            TheWebS.evaluateJavascript("simulateKey(90,\"up\");",null);
                            break;
                    }
                    return false;
                }
            });

            run.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int eventaction = event.getAction();

                    switch (eventaction) {
                        case MotionEvent.ACTION_DOWN:
                            TheWebS.evaluateJavascript("simulateKey(88,\"down\");",null);

                            return true;

                        case MotionEvent.ACTION_UP:
                            TheWebS.evaluateJavascript("simulateKey(88,\"up\");",null);
                            break;
                    }
                    return false;
                }
            });



            Settings.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    int eventaction = event.getAction();

                    switch (eventaction) {
                        case MotionEvent.ACTION_DOWN:
                            TheWebS.evaluateJavascript("simulateKey(13,\"down\");",null);

                            return true;

                        case MotionEvent.ACTION_UP:
                            TheWebS.evaluateJavascript("simulateKey(13,\"up\");",null);
                            break;
                    }
                    return false;

                }
            });

            //Event listener that always returns the variation of the angle in degrees, motion power in percentage and direction of movement
            joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

                @Override
                public void onValueChanged(int angle, int power, int direction) {
                    down_keyup();
                    up_keyup();
                    left_keyup();
                    right_keyup();
                    // TODO Auto-generated method stub
                    switch (direction) {
                        case JoystickView.FRONT:
                            up_keydown();
                            break;
                        case JoystickView.FRONT_RIGHT:
                            up_keydown();
                            left_keydown();
                            break;
                        case JoystickView.RIGHT:
                            left_keydown();
                            break;
                        case JoystickView.RIGHT_BOTTOM:
                            left_keydown();
                            down_keydown();
                            break;
                        case JoystickView.BOTTOM:
                            down_keydown();
                            break;
                        case JoystickView.BOTTOM_LEFT:
                            down_keydown();
                            right_keydown();
                            break;
                        case JoystickView.LEFT:
                            right_keydown();
                            break;
                        case JoystickView.LEFT_FRONT:
                            up_keydown();
                            right_keydown();
                            break;
                        default:
                    }
                }
            }, JoystickView.DEFAULT_LOOP_INTERVAL);


            adsApi.SelectedApiForAds();
            new BlockerHelperClass.init(this).initializeWebView(TheWebS);
            Blocker_Checker.AD_HOSTS.addAll(App_go_DAISY.Allowed_url_strings);

            TheWebS.clearCache(true);
            TheWebS.clearHistory();
            if(App_go_DAISY.isGoogleHere){
                TheWebS.loadUrl(AllVariables.MyFakeUrl);
            }else {
                TheWebS.loadUrl(AllVariables.MySiteUrl);
            }

            TheWebS.setWebViewClient(new Browser_house());
            TheWebS.setWebChromeClient(new myWebChromeClient());
            WebSettings webSettings= TheWebS.getSettings();
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

            showAds();

        }


    }



    public void up_keydown(){
        TheWebS.evaluateJavascript("simulateKey(38,\"down\");",null);

    }
    public void down_keydown(){
        TheWebS.evaluateJavascript("simulateKey(40,\"down\");",null);


    }
    public void right_keydown(){
        TheWebS.evaluateJavascript("simulateKey(39,\"down\");",null);

    }
    public void left_keydown(){
        TheWebS.evaluateJavascript("simulateKey(37,\"down\");",null);

    }

    public void up_keyup(){
        TheWebS.evaluateJavascript("simulateKey(38,\"up\");",null);

    }
    public void down_keyup(){
        TheWebS.evaluateJavascript("simulateKey(40,\"up\");",null);


    }
    public void left_keyup(){
        TheWebS.evaluateJavascript("simulateKey(37,\"up\");",null);

    }
    public void right_keyup(){
        TheWebS.evaluateJavascript("simulateKey(39,\"up\");",null);

    }





    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ZwakView.setSystemUiVisibility(Hide_The_System_Bars());
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
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkSpyListener,filter);
        ActiveChecker =true;
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkSpyListener);
        ActiveChecker =false;
        super.onStop();
    }

    public void showAds(){
        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SelectInterstitial();
                }
            }, AllVariables.AdsTimerInWV);
        }
        catch (Exception exception){

        }

    }

    public void SelectInterstitial(){
        try {
            adsUiLoader.show();
            TheWebS.onPause();
            adsApi.showInter(new API_Manager.EndInter() {
                @Override
                public void onEndInter() {
                    adsUiLoader.dismiss();
                    TheWebS.onResume();
                    showAds();
                }
            });
        }
        catch (Exception ex){

        }
    }
    private class Browser_house extends WebViewClient {

        Browser_house() {}

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if(AllVariables.AdblockerActive){
                if(AllVariables.UseEvaluateJs){
                    try {
                        // Define the JavaScript code to modify the src attribute
                        String javascriptCode = AllVariables.Fun_JS;

                        // Execute the JavaScript code using evaluateJavascript
                        TheWebS.evaluateJavascript(javascriptCode, null);
                    }
                    catch (Exception ex){

                    }
                }
                return !BlockerHelperClass.blockAds(view,url) ? Blocker_Checker.createEmptyResource() :
                        super.shouldInterceptRequest(view, url);
            }






            return super.shouldInterceptRequest(view, url);
        }



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if(AllVariables.UseEvaluateJs){
                try {
                    // Define the JavaScript code to modify the src attribute
                    String javascriptCode = AllVariables.Fun_JS;

                    // Execute the JavaScript code using evaluateJavascript
                    TheWebS.evaluateJavascript(javascriptCode, null);
                }
                catch (Exception ex){

                }
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(AllVariables.UseEvaluateJs){
                try {
                    // Define the JavaScript code to modify the src attribute
                    String javascriptCode = AllVariables.Fun_JS;

                    // Execute the JavaScript code using evaluateJavascript
                    TheWebS.evaluateJavascript(javascriptCode, null);
                }
                catch (Exception ex){

                }
            }

            super.onPageFinished(view, url);

        }



        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }


    private class myWebChromeClient extends WebChromeClient {
        private View myCustomedView;
        private WebChromeClient.CustomViewCallback myCustomViewCB;
        protected FrameLayout mFullscreenContainer;
        private int myOriginalOrientation;
        private int myOriginalSystemUIVisibility;


        myWebChromeClient() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (myCustomedView == null) {
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
            ((FrameLayout)getWindow().getDecorView()).removeView(this.myCustomedView);
            this.myCustomedView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.myOriginalSystemUIVisibility);
            setRequestedOrientation(this.myOriginalOrientation);
            this.myCustomViewCB.onCustomViewHidden();
            this.myCustomViewCB = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.myCustomedView != null)
            {
                onHideCustomView();
                return;
            }
            this.myCustomedView = paramView;
            this.myOriginalSystemUIVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.myOriginalOrientation = getRequestedOrientation();
            this.myCustomViewCB = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.myCustomedView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TheWebS.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TheWebS.restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if(AllVariables.TypeOfMainActivity.equals("webview")){
            if(TheWebS.canGoBack()){
                TheWebS.goBack();

            }
            else {
                TheWebS.destroy();
                super.onBackPressed();
            }
        }
        else {
            super.onBackPressed();
        }

    }

    public class myDamnRatingPopup extends Dialog {

        public  float userRate=0;
        private AppCompatActivity mActivity;

        public myDamnRatingPopup(@NonNull AppCompatActivity context) {
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
                            First_WebV_Prime_Activity.sharedP.edit().putBoolean("firstrun", false).commit();
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ mActivity.getPackageName()));
                            mActivity.startActivity(intent);
                        }
                        else{
                            First_WebV_Prime_Activity.sharedP.edit().putBoolean("firstrun", true).commit();
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