package com.yutou.net;

import com.google.gson.JsonObject;
import com.yutou.bean.ProductBean;
import com.yutou.data.APPURL;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by samsung on 2017/4/6.
 */

public interface ApiService {
    //获取商品列表数据(实体类)
    @GET(APPURL.PRODUCT_INDEX)
    Observable<ProductBean> getProductList(@Query("page") String page);
    //获取商品列表数据(Json)
    @GET(APPURL.PRODUCT_INDEX)
    Observable<JsonObject> getProductListJSON(@Query("page") String page);
}
