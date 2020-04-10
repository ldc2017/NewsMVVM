package com.ldc.newsmvvm.http;

import android.util.Log;

import com.blankj.utilcode.util.ApiUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api2Request {
    private static final String TAG = "Api2Request";
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private static class Singleton {
        private static final Api2Request SINGLETON = new Api2Request();
    }

    private Api2Request() {
        okHttpClient = getOkHttpClient();
        retrofit = getRetrofit();

    }

    public static Api2Request getInstance() {
        return Singleton.SINGLETON;
    }


    //获取服务
    public <T> T CreateServer(Class<T> tClass) {
        return retrofit.create(tClass);
    }

    // 获取网络请求客户端
    private OkHttpClient getOkHttpClient() {
        if (null == okHttpClient) {
            final HttpLoggingInterceptor logs = new HttpLoggingInterceptor(new HttpLogs());
            logs.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder()
                    .writeTimeout(35, TimeUnit.SECONDS)
                    .readTimeout(35, TimeUnit.SECONDS)
                    .addInterceptor(logs)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
        }

        return okHttpClient;
    }

    //获取 retrofit
    private Retrofit getRetrofit() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API.base_url)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    //网络请求日志
    private static class HttpLogs implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.e(TAG, String.format("网络请求日志:: %s", message));
        }
    }
}
