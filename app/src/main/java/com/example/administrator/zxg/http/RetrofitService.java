package com.example.administrator.zxg.http;

import com.example.administrator.zxg.entity.DemoModel;
import com.example.administrator.zxg.entity.UserBean;
import com.example.administrator.zxg.entity.UserLoginBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/6/5 11:17
 */

public interface RetrofitService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie();

    @GET("demo/getDemo")
    Observable<DemoModel> getDemoModel();

    @GET("user/userLogin")
     Observable<UserBean> userLogin(@QueryMap Map<String,String> userLoginBean);

//    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET("user/userLoginPostTest")
    Observable<UserLoginBean> userLoginPostTest(@Body UserLoginBean userLoginBean);



    @POST("user/userLoginPost")
    Observable<UserBean>  userLoginPost(@Body UserLoginBean userBean);

    @GET("user/registerUser")
    Observable<UserBean> registerUser(@QueryMap Map<String,String> registerUser);
}
