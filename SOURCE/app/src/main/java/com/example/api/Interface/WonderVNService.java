package com.example.api.Interface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WonderVNService {
    //Khai báo: tên api, kiểu, dữ liệu truyền lên, dữ liệu trả về
    @POST("Service/GetListPlace")
    Call<ResponseBody> getListPlace(@Body Object object);
    @POST("Service/GetListContact")
    Call<ResponseBody> getListContac(@Body Object object);
    @POST("Service/GetListPromotion")
    Call<ResponseBody> getListPromotion(@Body Object object);
}
