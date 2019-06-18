package com.example.api.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.api.Adapter.ContactAdapter;
import com.example.api.Interface.WonderVNService;
import com.example.api.Model.ExampleContact;
import com.example.api.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactActivity extends AppCompatActivity {
    RecyclerView rcvContact;
    ExampleContact exampleContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();
        getData();
    }

    void init() {
        rcvContact = findViewById(R.id.rcv_contact);
    }

    void getData() {
        GetListContactBody getListContactBody = new GetListContactBody("madara", "madara", 0, "");
        //khai báo + khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://150.95.115.192/api/")
                .build();
        retrofit.create(WonderVNService.class).getListContac(getListContactBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String strJson = null;
                try {
                    strJson = response.body().string();
                    Gson gson = new Gson();
                    exampleContact = gson.fromJson(strJson, ExampleContact.class);
                    Log.d("onResponse", "onResponse:");


                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ContactActivity.this, LinearLayoutManager.VERTICAL, false);
                    rcvContact.setLayoutManager(linearLayoutManager);
                    ContactAdapter adapter = new ContactAdapter();
                    adapter.data = exampleContact.getResult();
                    adapter.setContext(ContactActivity.this);
                    rcvContact.setAdapter(adapter);
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


    class GetListContactBody {
        String userAPI, passAPI, searchKey;
        int contactID;

        public GetListContactBody(String userAPI, String passAPI, int contactID, String searchKey) {
            this.userAPI = userAPI;
            this.passAPI = passAPI;
            this.searchKey = searchKey;
            this.contactID = contactID;
        }
    }
}
