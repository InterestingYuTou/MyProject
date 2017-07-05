package com.yutou.net.utils;

import android.util.Log;

import com.yutou.BuildConfig;
import com.yutou.MyApplication;
import com.yutou.data.APPURL;
import com.yutou.net.ApiService;
import com.yutou.net.BaseSubscriber;
import com.yutou.net.factory.JsonConverterFactory;
import com.yutou.ui.utils.HttpUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 描    述：请求管理类
 * 创 建 人：ZJY
 * 创建日期：2017/4/6 16:13
 * 修订历史：
 * 修 改 人：
 */

public class RetrofitUtil {
    private static RetrofitUtil mInstance;
    public static final int DEFAULT_TIMEOUT = 5;//设置超时秒数

    private Retrofit mRetrofit;
    private ApiService mApiService;

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    /**
     * 初始化Retrofit
     *
     * @return
     */
    private Retrofit getRetrofit() {
        if (null == mRetrofit) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(APPURL.APP_BASE_URL)
                    .client(getOkHttpClient())
                    //增加返回值为Gson的支持(以实体类返回)
                    .addConverterFactory(JsonConverterFactory.create())
                    //增加返回值为Oservable<T>的支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 获取okhttpclient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "MyProjectCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(logging);//添加拦截器 打印接口以及数据
        }
        Interceptor cacheInterceptor = getCacheInterceptor();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)//添加缓存
//                .addNetworkInterceptor(cacheInterceptor)//设置缓存拦截器
                .retryOnConnectionFailure(true);//设置失败重连
//        builder.readTimeout(20, TimeUnit.SECONDS);
//        builder.writeTimeout(20, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }


    /**
     * 获取缓存拦截器
     *
     * @return
     */
    private Interceptor getCacheInterceptor() {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!HttpUtils.checkNetworkState(MyApplication.getInstance())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                Log.e("123", response.toString() + "123");
                if (HttpUtils.checkNetworkState(MyApplication.getInstance())) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("WuXiaolong")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("nyn")
                            .build();
                }
                return response;
            }
        };
        return cacheInterceptor;
    }


    /**
     * 绑定接口文档
     *
     * @return
     */
    public ApiService getApiService() {
        if (mApiService == null) {
            mApiService = getRetrofit().create(ApiService.class);
        }
        return mApiService;
    }

    /**
     * 设置观察者
     *
     * @param o   被观察者
     * @param s   观察者
     * @param <T> 数据类型
     */
    public <T> void toSubscribe(Observable<T> o, BaseSubscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()) //在io线程中处理网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);//在主线程中处理数据
    }
}
