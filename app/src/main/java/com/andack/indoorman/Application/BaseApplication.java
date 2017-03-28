package com.andack.indoorman.Application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/18
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        //对bugly进行初始化
        super.onCreate();
        //进行初始化
        CrashReport.initCrashReport(getApplicationContext(),"26103a674e",true);
    }
}
