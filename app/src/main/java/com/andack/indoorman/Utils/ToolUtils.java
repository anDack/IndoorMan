package com.andack.indoorman.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.TextView;

import com.andack.indoorman.Cache.ACache;
import com.andack.indoorman.entity.FanHaoEntity;
import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/18
 * 邮箱：    1160083806@qq.com
 * 描述：    工具类
 */

public class ToolUtils {
    //设置字体
    public static void setFont(Context context, TextView textView){
        Typeface fontType=Typeface.createFromAsset(context.getAssets(),"fonts/YYG.TTF");
        textView.setTypeface(fontType);
    }
    public static String CacheData(){
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
//        int se=calendar.get(Calendar.SECOND);
        return new String(year+month+day+"");
//        return new String(year+month+day+hour+minute+se+"");
    }
    public static void setFanHaoToACache(ArrayList<FanHaoEntity> fanHaoEntities,ACache aCache,String TAG){
        for (int i = 0; i < fanHaoEntities.size(); i++) {
            String ImageUrl=fanHaoEntities.get(i).getImageUrl();
            String FanHao=fanHaoEntities.get(i).getFanhao();
            String Content=FanHao+","+ImageUrl;
            aCache.put(ContentClass.CACHE_NAME+TAG+i,Content);
        }
    }

    public static void setArrayListToACache(ArrayList<ZaiNanFuLiEntity> arrayList
            , ACache aCache,String TAG){
        for (int i = 0; i < arrayList.size(); i++) {
            String Url=arrayList.get(i).getUrl();
            String Title=arrayList.get(i).getTitle();
            String ShortContent=arrayList.get(i).getShortContent();
            String Time=arrayList.get(i).getTime();
            String ImageUrl=arrayList.get(i).getImageUrl();
            String Bowrse=arrayList.get(i).getBrowse();
            String Content=Url+","+Title+","+ShortContent+","+Time+","+ImageUrl+","+Bowrse;
            aCache.put(ContentClass.CACHE_NAME+TAG+i,Content);
        }

    }
    public static ArrayList<FanHaoEntity> getFanHaoCacheToArrayList(ACache aCache,String TAG){
        ArrayList<FanHaoEntity> entities=new ArrayList<>();
        FanHaoEntity fanHaoEntity;
        for (int i = 0; i <ContentClass.FANHAO_NUM ; i++) {
            String aCahceString=aCache.getAsString(ContentClass.CACHE_NAME+TAG+i);
            if (!TextUtils.isEmpty(aCahceString)){
                String[] spilt=aCahceString.split(",");
                fanHaoEntity=new FanHaoEntity();
                fanHaoEntity.setFanhao(spilt[0]);
                fanHaoEntity.setImageUrl(spilt[1]);
                entities.add(fanHaoEntity);

            }

        }
        return entities;
    }
    public static ArrayList<ZaiNanFuLiEntity> getCacheToArrayList(ACache aCache,String TAG){
        ArrayList<ZaiNanFuLiEntity> entities=new ArrayList<>();
        ZaiNanFuLiEntity zaiNanFuLiEntity;
        for (int i = 0; i < ContentClass.PAGE_NUM; i++) {
            String aCacheString=aCache.getAsString(ContentClass.CACHE_NAME+TAG+i);
            if (!TextUtils.isEmpty(aCacheString)) {
                String[] Content = aCacheString.split(",");
                zaiNanFuLiEntity = new ZaiNanFuLiEntity();
                zaiNanFuLiEntity.setUrl(Content[0]);
                zaiNanFuLiEntity.setTitle(Content[1]);
                zaiNanFuLiEntity.setShortContent(Content[2]);
                zaiNanFuLiEntity.setTime(Content[3]);
                zaiNanFuLiEntity.setImageUrl(Content[4]);
                zaiNanFuLiEntity.setBrowse(Content[5]);
                entities.add(zaiNanFuLiEntity);
            }
        }
        return entities;
    }
}
