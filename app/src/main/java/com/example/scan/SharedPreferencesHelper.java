package com.example.scan;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    public static void saveData(Context context, String key, String value){
        SharedPreferences sh=context.getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sh.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public static void saveBoolean(Context context, String key, boolean value){
        SharedPreferences sh=context.getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sh.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static String getData(Context context,String key){
        SharedPreferences sh=context.getSharedPreferences("x",Context.MODE_PRIVATE);
        return sh.getString(key,"");
    }
    public static boolean getBoolean(Context context,String key,boolean def){
        SharedPreferences sh=context.getSharedPreferences("x",Context.MODE_PRIVATE);
        return sh.getBoolean(key,def);
    }

}
