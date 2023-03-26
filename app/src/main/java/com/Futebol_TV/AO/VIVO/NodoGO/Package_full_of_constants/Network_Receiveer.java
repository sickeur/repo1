package com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.Futebol_TV.AO.VIVO.NodoGO.R;

public class Network_Receiveer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Checking_Connexion_Class.isConnectedToTheInternet(context)) { //There is no connection
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layoutDialogue = LayoutInflater.from(context).inflate(R.layout.layout_when_there_is_no_internet_bro, null);
            builder.setView(layoutDialogue);

            AppCompatButton btnRetry = layoutDialogue.findViewById(R.id.btnRetry);

            //showDialogue
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    onReceive(context, intent);
                }
            });
        }
    }
}
