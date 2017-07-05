package com.yutou.net;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.yutou.R;
import com.yutou.net.exception.ResultException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * 描    述：自定义Subscriber处理统一的逻辑
 * 创 建 人：ZJY
 * 创建日期：2017/4/6 17:07
 * 修订历史：
 * 修 改 人：
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    private String textResources;//初始化例如可以赋给弹框中的文字
    private Context context;

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    /**
     * 完成
     * 如有弹框 先隐藏弹框
     */
    @Override
    public void onCompleted() {

    }

    /**
     * 对错误进行统一处理
     * 如有弹框 先隐藏弹框
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.e("Exception------", "请求异常为" + e.getClass());
        //请求异常提示
        String toast = "";
        if (e instanceof UnknownHostException)
            toast = context.getString(R.string.network_connection_faile);
        else if (e instanceof SocketTimeoutException)
            toast = context.getString(R.string.network_connection_time_out);
        else if (e instanceof ConnectException)
            toast = context.getString(R.string.network_connection_time_out);
        else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int responseCode = httpException.code();
            if (responseCode >= 400 && responseCode <= 417) {
                toast = context.getString(R.string.common_url_error);
            } else if (responseCode >= 500 && responseCode <= 505) {
                toast = context.getString(R.string.network_connection_busy);
            } else {
                toast = context.getString(R.string.network_connection_exception);
            }
        } else if (e instanceof ResultException) {
            //改异常为判断服务器返回数据code不是40000的情况
            ResultException resultException = (ResultException) e;
            toast = resultException.getMessage();
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof NullPointerException) {
            toast = context.getString(R.string.common_data_exception);
        } else {
            toast = context.getString(R.string.common_unknown_error);
        }

        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }


    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
    }
}
