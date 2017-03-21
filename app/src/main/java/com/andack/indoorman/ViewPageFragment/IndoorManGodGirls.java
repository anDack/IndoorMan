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
import com.andack.indoorman.adapter.IndoorManChannelAdapter;
import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/19
 * 邮箱：    1160083806@qq.com
 * 描述：    宅男福利社->宅男女神
 */

public class IndoorManGodGirls extends Fragment {
    private ListView listView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<ZaiNanFuLiEntity> entities;
    private IndoorManChannelAdapter adapter;
    private ACache aCache;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.indoor_god_girls_zainanfulishe,container,false);
        initView(view);
        initData();
        return view;
    }
    private void initData() {
        entities=new ArrayList<>();
        aCache=ACache.get(getContext());
        ContentThread thread=new ContentThread(ContentClass.INDOOR_AVER_URL, new OnJsoupPraseListener() {
            @Override
            public void onSuccess(ArrayList<ZaiNanFuLiEntity> tempEntity) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                entities=tempEntity;

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

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.mySwipeRefresh);
        refreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.swipe_color_2,
                R.color.swipe_color_3,
                R.color.swipe_color_4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ContentThread thread=new ContentThread(ContentClass.INDOOR_AVER_URL, new OnJsoupPraseListener() {
                    @Override
                    public void onSuccess(ArrayList<ZaiNanFuLiEntity> tempEntity) {
                        progressBar.setVisibility(View.GONE);
                        refreshLayout.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.VISIBLE);
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
