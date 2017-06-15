package com.example.administrator.zxg.http;

import com.example.administrator.zxg.common.IConstant;
import com.example.administrator.zxg.entity.DemoModel;
import com.example.administrator.zxg.entity.UserBean;
import com.example.administrator.zxg.entity.UserLoginBean;

import java.net.UnknownServiceException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okio.Timeout;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description
 * @Author bayonet1351
 * 将请求过程进行封装
 * Created 2017/6/5 13:23
 */

public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private RetrofitService retrofitService;

    //构造私有方法

    private HttpMethods(){
        //手动创建一个OKHHTTPclient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IConstant.base_zxg)
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();

    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /**
    * 用于获取豆瓣电影Top250的数据
    * @param subscriber 由调用者传过来的观察者对象

     */
    //获取用户
    public void getTopMovie(Subscriber<HttpResult<List<Subject>>> subscriber){
        retrofitService.getTopMovie()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getBaseUrl(Subscriber<DemoModel> subscriber){
        retrofitService.getDemoModel()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

     public void login(Subscriber<UserBean> subscriber, Map<String,String> userLoginBean){
         retrofitService.userLogin(userLoginBean)
                 .subscribeOn(Schedulers.io())
                 .unsubscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(subscriber);


     }

    public void userLoginPostTest(Subscriber<UserLoginBean> subscriber, UserLoginBean userLoginBean){
        retrofitService.userLoginPostTest(userLoginBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void userLoginPost (Subscriber<UserBean> subscriber,UserLoginBean userBean){


        retrofitService.userLoginPost(userBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void registerUser (Subscriber<UserBean> subscriber,Map<String,String> userBean){


        retrofitService.registerUser(userBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
