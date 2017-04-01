package com.hades.baselib.net;

import android.content.Context;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Hades on 2016/11/14.
 */

public class RetrofitManager {

    OkHttpClient mOkHttpClient;
    Retrofit mRetrofit;
    Retrofit mRxRetrofit;
    Context mContext;

    public String getmBaseUrl() {
        return mBaseUrl;
    }

    String mBaseUrl;

    private RetrofitManager() {
    }

    private static class Singleton {
        private static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance(){
        return Singleton.instance;
    }

    //1 关联OKhttp
    public void bindHttpClient(OkHttpClient httpClient, Context context){
        mOkHttpClient = httpClient;
        mContext = context;
    }
    //2
    public void setBaseUrl(String url){
        mBaseUrl = url;
    }

    //2 初始化retrofit [需要设置url后方可使用]
    public Retrofit getDefaultRetrofit(){
        if (mOkHttpClient != null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }else {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }


//    public Retrofit getRxRetrofit(){
//        if (mOkHttpClient != null){
//            mRxRetrofit = new Retrofit.Builder()
//                    .baseUrl(mBaseUrl)
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(mOkHttpClient)
//                    .build();
//        }else {
//            mRxRetrofit = new Retrofit.Builder()
//                    .baseUrl(mBaseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return mRxRetrofit;
//    }


    //获取基准域名、应对不标准的情况
    public Retrofit getDefaultRetrofit(String url){
        if (mOkHttpClient != null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }else {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

    public Retrofit getFit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .build();
        return mRetrofit;
    }
}
