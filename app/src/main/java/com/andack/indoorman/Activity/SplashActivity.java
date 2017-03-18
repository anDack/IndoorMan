package com.andack.indoorman.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.andack.indoorman.MainActivity;
import com.andack.indoorman.R;
import com.andack.indoorman.Utils.ContentClass;
import com.andack.indoorman.Utils.ToolUtils;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/18
 * 邮箱：    1160083806@qq.com
 * 描述：    闪屏页
 */

public class SplashActivity extends AppCompatActivity {
    private TextView splashTv;
    private Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ContentClass.SPLASH_HANDLER:
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

    }

    private void initView() {
        splashTv= (TextView) findViewById(R.id.splash_tv);
        ToolUtils.setFont(this,splashTv);
        myHandler.sendEmptyMessageDelayed(ContentClass.SPLASH_HANDLER,ContentClass.SPLASH_TIME);
    }

    @Override
    public void onBackPressed() {

    }
}
