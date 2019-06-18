package com.example.api.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.api.Model.ResultPlace;
import com.example.api.Model.ResultPlace;
import com.example.api.R;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{
    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ResultPlace> data;
    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlaceViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_place, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {
        ResultPlace placeResult = data.get(i);
        placeViewHolder.tvName.setText(placeResult.getPlaceName());
        int a = placeResult.getIsPromotion();
        if(a ==1){
            placeViewHolder.tvPromotion.setVisibility(View.VISIBLE);
        }else {
            placeViewHolder.tvPromotion.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPromotion;


        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_place);
            tvPromotion = itemView.findViewById(R.id.tv_promotion);


        }
    }

}
