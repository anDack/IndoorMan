package com.andack.indoorman.ViewPageFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andack.indoorman.R;
import com.andack.indoorman.Utils.ContentClass;
import com.andack.indoorman.adapter.IndoorManChannelAdapter;
import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/19
 * 邮箱：    1160083806@qq.com
 * 描述：    宅男福利社-> 宅男频道
 */

public class IndoorManChannel extends Fragment {
    private ListView listView;
    private ProgressBar progressBar;
    private ArrayList<ZaiNanFuLiEntity> entities;
    private IndoorManChannelAdapter adapter;
    private ZaiNanFuLiEntity zaiNanFuLiEntity;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ContentClass.HANDLER_INDOORMAN_CHANNEL:
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    adapter=new IndoorManChannelAdapter(getContext(),entities);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.indoor_channel_zainanfulishe,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        entities=new ArrayList<>();
        ContentThread thread=new ContentThread();
        thread.start();

    }

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);

    }
    class ContentThread extends Thread{
        @Override
        public void run() {
            try {
                Document document= Jsoup.connect(ContentClass.INDOOR_CHANNEL_URL).
                        timeout(3000).get();
                Elements elements=document.select("article");
                for (Element element : elements) {
                    String title = element.select("a").attr("title");
                    String url = element.select("a").attr("href");
                    String ImageUrl = element.select("a").select("div.thumb-img").select("img").attr("src");
                    String time=element.select("span.postlist-meta-time").text();
                    String browse=element.select("div.meta").select("span.postlist-meta-views").text();
                    String shortContent=element.select("p").text();
                    if (!title.equals("") && !ImageUrl.equals("")) {
                        zaiNanFuLiEntity=new ZaiNanFuLiEntity();
                        zaiNanFuLiEntity.setTitle(title);
                        zaiNanFuLiEntity.setUrl(url);
                        zaiNanFuLiEntity.setImageUrl(ImageUrl);
                        zaiNanFuLiEntity.setTime(time);
                        zaiNanFuLiEntity.setBrowse(browse);
                        zaiNanFuLiEntity.setShortContent(shortContent);
                        entities.add(zaiNanFuLiEntity);
                    }
                    mHandler.sendEmptyMessage(ContentClass.HANDLER_INDOORMAN_CHANNEL);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
