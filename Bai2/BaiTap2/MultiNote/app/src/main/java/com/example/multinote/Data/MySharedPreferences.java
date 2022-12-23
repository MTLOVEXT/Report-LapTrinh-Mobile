package com.example.multinote.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private  static  final String MY_SHARED = "MY_SHARED";
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }
    public  void putString(String key , String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public  String getString(String key ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
