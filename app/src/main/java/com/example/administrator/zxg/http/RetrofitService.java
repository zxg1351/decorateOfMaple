package com.example.administrator.zxg.http;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/6/5 11:17
 */

public interface RetrofitService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie();
}
