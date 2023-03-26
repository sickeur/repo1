package com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants;

import android.content.Context;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

public class BlockerHelperClass {


    static Map<String, Boolean> loadedUrls = new HashMap<>();

    public static boolean blockAds(WebView view, String url) {
        boolean ad;
        if (!loadedUrls.containsKey(url)) {
            ad = Blocker_Checker.isAd(url);
            loadedUrls.put(url, ad);
        } else {
            ad = loadedUrls.get(url);
        }
        return ad;
    }

    public static class init {
        Context context;

        public init(Context context) {
            Blocker_Checker.init(context);
            this.context = context;
        }

        public void initializeWebView(WebView mWebView) {


        }
    }
}
