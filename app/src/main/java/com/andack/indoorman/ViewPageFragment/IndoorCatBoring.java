package com.andack.indoorman.ViewPageFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andack.indoorman.Cache.ACache;
import com.andack.indoorman.R;
import com.andack.indoorman.Utils.ContentClass;
import com.andack.indoorman.Utils.L;
import com.andack.indoorman.Utils.NetUils;
import com.andack.indoorman.Utils.ToolUtils;
import com.andack.indoorman.adapter.IndoorManChannelAdapter;
import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/19
 * 邮箱：    1160083806@qq.com
 * 描述：    绅士学院的fragment
 */

public class IndoorCatBoring extends Fragment {
    protected ListView listView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<ZaiNanFuLiEntity> mData;
    private IndoorManChannelAdapter adapter;
    private ACache aCache;

    private static String thisChannelUrl= ContentClass.CAT_BORING_URL;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cat_boring_fragment,container,false);
        mData=new ArrayList<>();
        initView(view);
        return view;
    }
    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
        adapter=new IndoorManChannelAdapter(getContext(),mData);
        ArrayList<ZaiNanFuLiEntity>temp=new ArrayList<>();
        final ACache aCache=ACache.get(getContext());
        ArrayList<ZaiNanFuLiEntity> list= ToolUtils.getCacheToArrayList(aCache,thisChannelUrl);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        refreshLayout= (SwipeRefreshLayout) view.
                findViewById(R.id.mySwipeRefresh);

        L.i("bool is "+list.equals(temp));
        if (list.equals(temp) ) {
            L.i("第一次进入没有缓存数据");
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            new getDataTask().execute();
        }else {
            //如果有缓冲数据
            L.i("有没有缓存数据");
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            mData.addAll(list);
            adapter.notifyDataSetChanged();

        }

        listView.setAdapter(adapter);

        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light,android.R.color.holo_blue_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new getDataTask().execute();
                refreshLayout.setRefreshing(false);
            }
        });
    }
    class getDataTask extends AsyncTask<Void,Void,ArrayList<ZaiNanFuLiEntity>> {

        @Override
        protected ArrayList<ZaiNanFuLiEntity> doInBackground(Void... params) {
            try {
                mData= NetUils.JsoupParse(thisChannelUrl);
            } catch (IOException e) {
//                Toast.makeText(getActivity(), "网络请求异常！", Toast.LENGTH_SHORT).show();
            }
            return mData;
        }

        @Override
        protected void onPostExecute(ArrayList<ZaiNanFuLiEntity> entities) {

            final ACache aCache=ACache.get(getContext());
            ArrayList<ZaiNanFuLiEntity> list=ToolUtils.getCacheToArrayList(aCache,thisChannelUrl);
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            adapter.addAll(entities);
            adapter.notifyDataSetChanged();
            if (!list.equals(entities)) {
                //如果两者不同数据相同
                ToolUtils.setArrayListToACache(entities,aCache,thisChannelUrl);
            }

        }
    }
}
