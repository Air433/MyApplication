package com.example.air.common;

import java.util.List;

public class Checker {

    public static String checkIsBank(List<String> list){
        for (String s : list) {
            if (s==null || "".equals(s)){
                return s+"不能为空，请检查后重试";
            }
        }
        return null;
    }
}
