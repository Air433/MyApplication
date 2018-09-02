package com.example.air.common;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.air.myapplication.MainActivity.JSON;

public class PostHandler extends AsyncTask<String, Void, String>{
    OkHttpClient client = new OkHttpClient();
    final String username,password;

    public PostHandler(String username,String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(String... strings) {

        String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
        RequestBody body = RequestBody.create(JSON, json);
        String url = "http://localhost:8099/sys/login";
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";
        try {
            result = response.body().string();
            System.err.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
