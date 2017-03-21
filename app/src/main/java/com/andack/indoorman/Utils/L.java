package com.andack.indoorman.Utils;

import android.util.Log;

/**
 * 项目名称：MyMoocProject2
 * 项目作者：anDack
 * 项目时间：2017/1/19
 * 邮箱：    1160083806@qq.com
 * 描述：    对Log进行简单的封装，封装的过程和思路在我的笔记里《Log类的封装》
 */

public class L {
    //开关
    public static boolean DEBUG=true;
    //TAG
    public static final String TAG="Old driver";
    //五个等级DIWEF
    public static void d(String text){
        if (DEBUG)
        {
            Log.d(TAG, "d: "+text);
        }
    }
    public static void i(String text){
        if (DEBUG)
        {
            Log.i(TAG, "INFO: "+text);
        }
    }
    public static void w(String text){
        if (DEBUG)
        {
            Log.w(TAG, "WARNING: "+text);
        }
    }
    public static void e(String text){
        if (DEBUG)
        {
            Log.e(TAG, "ERROR: "+text);
        }
    }
    public static void f(String text){
        if (DEBUG)
        {
            Log.wtf(TAG, "F: "+text);
        }
    }


}
