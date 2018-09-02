package com.example.air.common;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    private static OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static <T> T post(String url, Object req, Class<T> tClass){
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(req));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(result, tClass);
    }
}
