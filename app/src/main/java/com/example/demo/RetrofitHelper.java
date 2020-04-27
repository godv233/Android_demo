package com.example.demo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @Author godv
 * Date on 2020/4/27  23:33
 */
public interface RetrofitHelper {
    @GET("/get/text")
    Call<ResponseBody> getJson();
}
