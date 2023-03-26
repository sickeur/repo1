package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Futebol_TV.AO.VIVO.NodoGO.R;

public class supprimer_Activity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_this);
        Whatever();
    }

    public void Whatever(){
        button = findViewById(R.id.t9awed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do walo
            }
        });
    }
}