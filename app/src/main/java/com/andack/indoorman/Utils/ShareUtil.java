package com.andack.indoorman.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名称：MyMoocProject2
 * 项目作者：anDack
 * 项目时间：2017/1/19
 * 邮箱：    1160083806@qq.com
 * 描述：    封装SharePreferences类
 */

public class ShareUtil {

    public static final String NAME="config";
    public static void putString(Context context,String key,String value)
    {
        L.i("正在存储String类型SharePreferences");
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    /**
     *
     * @param context   获取当前的Context
     * @param key       要获取的Value
     * @param defineVal 默认值
     * @return          返回所需要的数据
     */
    public static String GetString(Context context,String key,String defineVal)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defineVal);
    }
    //Integer
    public static void putInt(Context context,String key,int value)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    public static int GetInt(Context context,String key,int defineVal)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,defineVal);
    }
    //Boolean的封装
    public static void putBool(Context context,String key,boolean value)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    public static boolean GetBool(Context context,String key,boolean defineVal)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defineVal);
    }
    //删除单个
    public static void deleShared(Context context,String key)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
    //删除全部
    public static void deleAllShared(Context context){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
