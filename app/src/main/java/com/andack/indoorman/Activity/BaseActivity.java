package com.andack.indoorman.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/18
 * 邮箱：    1160083806@qq.com
 * 描述：    1.给普通的WebView页面增加一个返回的按钮
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 接下来要做的事情
     * 1.webView的接入
     * 2.番号图库爬取
     * 3.gank妹子展示
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
