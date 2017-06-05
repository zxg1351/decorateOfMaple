package com.example.administrator.zxg.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.common.IConstant;
import com.example.administrator.zxg.http.HttpMethods;
import com.example.administrator.zxg.http.HttpResult;
import com.example.administrator.zxg.http.RetrofitService;
import com.example.administrator.zxg.http.Subject;
import com.example.administrator.zxg.util.ToastUtil;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/6/2 17:03
 */

public class TestRetrofit extends CommonActivity {

    private TextView tv_title,result_TV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testretrofit);
        initView();
    }
    //初始化界面
    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("testretrofit");
        result_TV =(TextView) findViewById(R.id.result_TV);
    }

    public void retrofit(View view){

        getMovie();

    }

    private void getMovie() {

        HttpMethods.getInstance().getTopMovie(new Subscriber<HttpResult<List<Subject>>>() {
            @Override
            public void onCompleted() {
                ToastUtil.showShort(TestRetrofit.this,"Get Top Movie Completed");
            }

            @Override
            public void onError(Throwable e) {
                result_TV.setText(e.getMessage());
            }

            @Override
            public void onNext(HttpResult<List<Subject>> listHttpResult) {
                result_TV.setText(listHttpResult.toString());
            }
        });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(IConstant.baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//
//        RetrofitService movieServiec = retrofit.create(RetrofitService.class);
//        movieServiec.getTopMovie(0,10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResult<List<Subject>>>() {
//                    @Override
//                    public void onCompleted() {
//                        ToastUtil.showShort(TestRetrofit.this,"Get Top Movie Completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        result_TV.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<List<Subject>> listHttpResult) {
//                        result_TV.setText(listHttpResult.toString());
//                    }
//
//
//                });


    }
}
