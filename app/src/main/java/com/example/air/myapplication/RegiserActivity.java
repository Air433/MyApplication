package com.example.air.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.air.common.Checker;
import com.example.air.common.HttpClient;
import com.example.air.domain.AirResult;
import com.example.air.domain.request.RegiserUserReq;

import java.util.ArrayList;
import java.util.List;

public class RegiserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser);
    }

    public void createUser(View view){
        String username = ((EditText) findViewById(R.id.reg_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.reg_password)).getText().toString();
        String email = ((EditText) findViewById(R.id.reg_email)).getText().toString();
        String tel = ((EditText) findViewById(R.id.reg_tel)).getText().toString();

        List<String> checkList = new ArrayList<>();
        checkList.add(username);
        checkList.add(password);
        String checkIsBank = Checker.checkIsBank(checkList);
        if (checkIsBank!=null){

        }
        RegiserUserReq regiserUserReq = new RegiserUserReq();
        regiserUserReq.setUsername(username);
        regiserUserReq.setPassword(password);
        regiserUserReq.setEmail(email);
        regiserUserReq.setMobile(tel);

        String url = "";
        AirResult airResult = HttpClient.post(url, regiserUserReq, AirResult.class);

        if (airResult!=null && airResult.getStatus() == 200){

        }
    }


}
