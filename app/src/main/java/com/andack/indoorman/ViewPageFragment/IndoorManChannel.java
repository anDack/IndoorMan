package com.andack.indoorman.ViewPageFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.andack.indoorman.Cache.ACache;
import com.andack.indoorman.NetAndParse.ContentThread;
import com.andack.indoorman.NetAndParse.OnJsoupPraseListener;
import com.andack.indoorman.R;
import com.andack.indoorman.Utils.ContentClass;
import com.andack.indoorman.Utils.L;
import com.andack.indoorman.Utils.ShareUtil;
import com.andack.indoorman.Utils.ToolUtils;
import com.andack.indoorman.adapter.IndoorManChannelAdapter;
import com.andack.indoorman.entity.ZaiNanFuLiEntity;

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
    private SwipeRefreshLayout refreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.indoor_channel_zainanfulishe,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        final ACache aCache=ACache.get(getContext());
        entities=new ArrayList<>();
        L.i("初始化数据");
        if (ShareUtil.GetBool(getContext(),"isLunched",false)) {
            //非第一次请求
            //1.读取缓存文件
            //2.请求数据
            //3.比较数据是否有更新
            //4.如果没有更新就采用原来的数据
            //5.如果有更新就清除数据->设置新的数据到缓存
            L.i("the not first time");
            entities=ToolUtils.getCacheToArrayList(aCache);
            L.i("get the entities"+entities.size());
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
//            for (int i = 0; i < entities.size(); i++) {
//                L.i(entities.get(i).getTitle());
//            }
            adapter=new IndoorManChannelAdapter(getContext(),entities);
            listView.setAdapter(adapter);
            //TODO: 自动刷新的功能
//            refreshLayout.setRefreshing(true);
//            refreshLayout.post(new Runnable() {
//                @Override
//                public void run() {
//                    refreshLayout.setRefreshing(true);
//                }
//            });
//            L.i("the refreshLayout is "+refreshLayout.isRefreshing());
        }else {
            //第一次请求
            //缓存数据
            L.i("the first time");
            ShareUtil.putBool(getContext(),"isLunched",true);
            ContentThread thread=new ContentThread(ContentClass.INDOOR_CHANNEL_URL, new OnJsoupPraseListener() {
                @Override
                public void onSuccess(ArrayList<ZaiNanFuLiEntity> tempEntity) {
                    progressBar.setVisibility(View.GONE);
                    refreshLayout.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    entities=tempEntity;
                    ToolUtils.setArrayListToACache(entities,aCache);
                    adapter=new IndoorManChannelAdapter(getContext(),entities);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(getContext(), "请求似乎失败了", Toast.LENGTH_SHORT).show();
                }
            });
            thread.start();
        }


    }

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        refreshLayout= (SwipeRefreshLayout) view.
                findViewById(R.id.mySwipeRefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ContentThread thread=new ContentThread(ContentClass.INDOOR_AVER_URL, new OnJsoupPraseListener() {
                    @Override
                    public void onSuccess(ArrayList<ZaiNanFuLiEntity> tempEntity) {
                        L.i("请求成功!");
                        if (tempEntity.equals(entities)) {
                            refreshLayout.setRefreshing(false);
                        }else {
                            entities = tempEntity;
                            adapter.notifyDataSetChanged();
                            refreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(getContext(), "请求似乎失败了", Toast.LENGTH_SHORT).show();
                    }
                });
                thread.start();
            }
        });
    }

}
