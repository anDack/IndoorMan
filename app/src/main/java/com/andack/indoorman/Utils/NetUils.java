package com.andack.indoorman.Utils;

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
 * 项目时间：2017/3/22
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class NetUils {
    public static final ArrayList<ZaiNanFuLiEntity> JsoupParse(String Url) throws IOException {
        ArrayList<ZaiNanFuLiEntity> entities = new ArrayList<>();
        ZaiNanFuLiEntity zaiNanFuLiEntity;
        Document document = null;
            document = Jsoup.connect(Url).
                    timeout(3000).get();
        Elements elements = document.select("article");
//        L.i(elements.toString());
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
        }
        return entities;
    }
}
