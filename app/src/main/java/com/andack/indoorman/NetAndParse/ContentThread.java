package com.andack.indoorman.NetAndParse;

import android.os.Handler;
import android.os.Looper;

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
 * 项目时间：2017/3/20
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class ContentThread extends Thread {
    private Handler mHandler;
    private String Url;
    private ArrayList<ZaiNanFuLiEntity> entities;
    private ZaiNanFuLiEntity zaiNanFuLiEntity;
    private OnJsoupPraseListener listener;
    public ContentThread(String Url,ArrayList<ZaiNanFuLiEntity> entities,OnJsoupPraseListener listener) {
        this.entities=entities;
        this.listener=listener;
        this.Url=Url;
        this.mHandler=new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {
            Document document = Jsoup.connect(Url).
                    timeout(3000).get();
            Elements elements = document.select("article");
            for (Element element : elements) {
                String title = element.select("a").attr("title");
                String url = element.select("a").attr("href");
                String ImageUrl = element.select("a").select("div.thumb-img").select("img").attr("src");
                String time = element.select("span.postlist-meta-time").text();
                String browse = element.select("div.meta").select("span.postlist-meta-views").text();
                String shortContent = element.select("p").text();
                if (!title.equals("") && !ImageUrl.equals("")) {
                    zaiNanFuLiEntity = new ZaiNanFuLiEntity();
                    zaiNanFuLiEntity.setTitle(title);
                    zaiNanFuLiEntity.setUrl(url);
                    zaiNanFuLiEntity.setImageUrl(ImageUrl);
                    zaiNanFuLiEntity.setTime(time);
                    zaiNanFuLiEntity.setBrowse(browse);
                    zaiNanFuLiEntity.setShortContent(shortContent);
                    entities.add(zaiNanFuLiEntity);
                }
//                mHandler.sendEmptyMessage(ContentClass.HANDLER_INDOORMAN_CHANNEL);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(entities);
                    }
                });
            }
        } catch (IOException e) {
            listener.onFailure(e);
        }
    }
}
