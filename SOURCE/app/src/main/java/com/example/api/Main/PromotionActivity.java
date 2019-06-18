package com.example.api.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.api.Adapter.PromotionAdapter;
import com.example.api.Interface.WonderVNService;
import com.example.api.Model.ExamplePromotion;
import com.example.api.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PromotionActivity extends AppCompatActivity {
    RecyclerView rcvPromotion;
    ExamplePromotion examplePromotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        init();
        getData();
    }

    void init() {
        rcvPromotion = findViewById(R.id.rcv_promotion);
    }

    void getData() {
        GetListPromotionBody getListPromotionBody = new GetListPromotionBody("1", 0);
        //khai báo + khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://150.95.115.192/api/").build();
        retrofit.create(WonderVNService.class).getListPromotion(getListPromotionBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String strJson = null;
                try {
                    strJson = response.body().string();
                    Gson gson = new Gson();
                    examplePromotion = gson.fromJson(strJson, ExamplePromotion.class);
                    Log.d("onResponse", "onResponse:");
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PromotionActivity.this, LinearLayoutManager.VERTICAL, false);
                    rcvPromotion.setLayoutManager(linearLayoutManager);
                    PromotionAdapter promotionAdapter = new PromotionAdapter();
                    promotionAdapter.data = examplePromotion.getResult();
                    promotionAdapter.setContext(PromotionActivity.this);
                    rcvPromotion.setAdapter(promotionAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("onFailure", "onFailure:");


            }
        });
    }


    class GetListPromotionBody {
        String page;
        int promotionID;

        public GetListPromotionBody(String page, int promotionID) {
            this.page = page;
            this.promotionID = promotionID;
        }


    }

}
