package com.andack.indoorman.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.andack.indoorman.R;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/24
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class WebActivity extends BaseActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private String title;
    private String url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initData();
        initView();
    }

    private void initView() {
        webView= (WebView) findViewById(R.id.my_webview);
        progressBar= (ProgressBar) findViewById(R.id.my_progress);
        webView.getSettings().setJavaScriptEnabled(true);//设置容许使用JavaScript
        webView.getSettings().setSupportZoom(true);//容许缩放
        webView.getSettings().setBuiltInZoomControls(true);
        //接口回调
        webView.setWebChromeClient(new WebViewClient());
        webView.loadUrl(url);
        //本地显示
        webView.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                //接受这个事件
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

//                progressBar.setVisibility(View.GONE);
//                webView.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
    }

    private void initData() {
        Intent intent=getIntent();
        title=intent.getStringExtra("title");
        url=intent.getStringExtra("url");


    }
    public class WebViewClient extends WebChromeClient {
        //监听进度条的变化

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress==100) {
                progressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }

    }
}
