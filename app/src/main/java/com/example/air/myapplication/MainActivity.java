package com.example.air.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.air.common.PostHandler;
import com.example.air.domain.AirResult;
import com.example.air.domain.UserConfig;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.air.myapplication.MESSAGE";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText2);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void login(View view) throws IOException {

        String username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();

        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        OkHttpClient client = new OkHttpClient();
        String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
        RequestBody body = RequestBody.create(JSON, json);
        String url = "http://192.168.13.1:8099/sys/login";
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try{
            response = client.newCall(request).execute();
            result = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }

        AirResult airResult = new Gson().fromJson(result, AirResult.class);

        if (airResult!= null && airResult.getStatus()==200){
            String toJson = new Gson().toJson(airResult.getData());

            Map<String, Object> map = new Gson().fromJson(toJson, Map.class);
            UserConfig.setToken((String) map.get("token"));
            UserConfig.setExpire(((Double)map.get("expire")).intValue());
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "登陆成功");
            startActivity(intent);
        }else {
            ((EditText) findViewById(R.id.editText2)).setText("登陆失败",TextView.BufferType.NORMAL);
        }

    }

    public void regiser(View view){
        Intent intent = new Intent(this, RegiserActivity.class);
        startActivity(intent);
    }
}
