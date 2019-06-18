package com.example.api.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.api.Model.ExampleContact;
import com.example.api.Model.ResultContact;
import com.example.api.Model.ResultPlace;
import com.example.api.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ResultContact> data;


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_contact,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        ResultContact resultContact = data.get(i);
        contactViewHolder.tvName.setText(resultContact.getName());
        contactViewHolder.tvPhone.setText(resultContact.getPhone());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);

        }
    }

}
