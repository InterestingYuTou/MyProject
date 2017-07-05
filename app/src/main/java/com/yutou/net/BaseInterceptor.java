package com.yutou.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 *描    述：拦截器
 *创 建 人：ZJY
 *创建日期：2017/4/6 16:57
 *修订历史：
 *修 改 人：
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
