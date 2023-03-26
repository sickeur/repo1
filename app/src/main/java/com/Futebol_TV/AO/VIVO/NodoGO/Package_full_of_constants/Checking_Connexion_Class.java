package com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Checking_Connexion_Class {

    public static boolean isConnectedToTheInternet(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo[] info=connectivityManager.getAllNetworkInfo();
            if(info!=null){
                for(int i=0;i< info.length;i++){
                    if(info[i].getState()==NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
