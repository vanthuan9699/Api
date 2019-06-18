package com.example.api.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.api.Model.ResultContact;
import com.example.api.Model.ResultPromotion;
import com.example.api.R;

import java.util.List;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {
    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ResultPromotion> data;

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PromotionAdapter.PromotionViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_promotion,viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder promotionViewHolder, int i) {
        ResultPromotion resultPromotion = data.get(i);
        promotionViewHolder.tvNamePromo.setText(resultPromotion.getPromotionName());
        promotionViewHolder.tvDetailPromo.setText(resultPromotion.getPlaceDetail().getAddress());
        
        Glide.with(context).load(resultPromotion.getUrlImage()).into(promotionViewHolder.imgPromotion);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPromotion;
        TextView tvNamePromo, tvDetailPromo;
        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPromotion = itemView.findViewById(R.id.img_promotion);
            tvNamePromo = itemView.findViewById(R.id.tv_name_promo);
            tvDetailPromo = itemView.findViewById(R.id.tv_chitiet_promo);

        }
    }



}
