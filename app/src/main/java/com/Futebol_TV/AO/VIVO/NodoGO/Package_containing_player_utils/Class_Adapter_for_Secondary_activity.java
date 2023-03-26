package com.Futebol_TV.AO.VIVO.NodoGO.Package_containing_player_utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Futebol_TV.AO.VIVO.NodoGO.App_go_DAISY;
import com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities.Second_WebV_Prime_Activity;
import com.Futebol_TV.AO.VIVO.NodoGO.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Class_Adapter_for_Secondary_activity extends RecyclerView.Adapter<Class_Adapter_for_Secondary_activity.GamesViewHolder> {

    Context mContext;
    ArrayList<model_helper> gamerModeModels;

    public Class_Adapter_for_Secondary_activity(Context mContext, ArrayList<model_helper> gamerModeModels) {
        this.mContext = mContext;
        this.gamerModeModels = gamerModeModels;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_with_nice_ui,null);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        holder.Game_Name.setText(gamerModeModels.get(position).getGame_Name());
        holder.Game_Description.setText(gamerModeModels.get(position).getGame_Description());
        final model_helper model= gamerModeModels.get(position);
        Glide.with(mContext).load(model.getGame_Icon()).into(holder.Game_Icon);
        Glide.with(mContext).load(model.getGame_Bg_Image()).into(holder.Game_bg_Img);

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int Position) {
                String Url= App_go_DAISY.model_helpers.get(Position).getGame_Url();
                Intent intent=new Intent(mContext, Second_WebV_Prime_Activity.class);
                intent.putExtra("Url",Url);
                mContext.startActivity(intent);
//                Toast.makeText(mContext,"clicked "+Position,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return gamerModeModels.size();
    }

    public static class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView Game_Icon;
        ImageView Game_bg_Img;
        TextView Game_Name;
        TextView Game_Description;
        ItemClickListner listner;


        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Game_Icon = itemView.findViewById(R.id.Game_Icon);
            this.Game_bg_Img = itemView.findViewById(R.id.bg_img);
            this.Game_Name= itemView.findViewById(R.id.Game_Name);
            this.Game_Description= itemView.findViewById(R.id.Game_Description);
            itemView.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            this.listner.onItemClickListner(view,getLayoutPosition());
        }

        public void setItemClickListner(ItemClickListner ic){
            this.listner=ic;
        }

    }

}
