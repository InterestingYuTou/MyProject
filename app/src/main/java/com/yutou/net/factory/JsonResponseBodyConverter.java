package com.yutou.net.factory;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.yutou.net.HttpStatus;
import com.yutou.net.exception.ResultException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * 描    述：处理 ResponseBody
 * 创 建 人：ZJY
 * 创建日期：2017/4/6 17:22
 * 修订历史：
 * 修 改 人：
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        // 这里只是为了检测code是否为40000,所以只解析HttpStatus中的字段,因为只要code和hint就可以了
        HttpStatus httpStatus = gson.fromJson(response, HttpStatus.class);
        if (!httpStatus.getCode().equals("40000")) {
            value.close();
            throw new ResultException(httpStatus.getCode(), httpStatus.getHint());
        }
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = new JsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
