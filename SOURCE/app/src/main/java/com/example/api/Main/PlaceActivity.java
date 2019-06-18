package com.example.api.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.api.Adapter.PlaceAdapter;
import com.example.api.Interface.WonderVNService;
import com.example.api.Model.ExamplePlace;
import com.example.api.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceActivity extends AppCompatActivity {
    RecyclerView rcvPlace;
    ExamplePlace placeExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        init();
        getData();
//        configRVPlace();
    }
    void init(){
        rcvPlace = findViewById(R.id.rcv_place);
    }


    void getData(){
        GetListPlaceBody getListPlaceBody = new GetListPlaceBody(0,0, "");
        //khai báo + khởi tạo retrofit

        Retrofit retrofit = new Retrofit
                                .Builder()
                                //convert json
                                .addConverterFactory(GsonConverterFactory.create())
                                .baseUrl("http://150.95.115.192/api/")
                                .build();
        retrofit.create(WonderVNService.class).getListPlace(getListPlaceBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String srtJson = null;
                try {
                    srtJson = response.body().string();


                    Gson gson = new Gson();
                    placeExample = gson.fromJson(srtJson, ExamplePlace.class);

                    Log.d("onResponse", "onResponse:");
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlaceActivity.this, LinearLayoutManager.VERTICAL, false);
                    rcvPlace.setLayoutManager(linearLayoutManager);
                    PlaceAdapter adapter = new PlaceAdapter();

                    adapter.data = placeExample.getResult();
                    adapter.setContext(PlaceActivity.this);
                    rcvPlace.setAdapter(adapter);


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



    class GetListPlaceBody{
        int cateID, placeID;
        String searchKey;
        public GetListPlaceBody(int cateID, int placeID, String searchKey){
            this.cateID= cateID;
            this.placeID=placeID;
            this.searchKey=searchKey;
        }

    }


}
