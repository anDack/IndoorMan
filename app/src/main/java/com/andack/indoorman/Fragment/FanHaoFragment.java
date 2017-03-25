package com.andack.indoorman.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.andack.indoorman.R;
import com.andack.indoorman.Utils.ContentClass;
import com.andack.indoorman.Utils.L;
import com.andack.indoorman.Utils.NetUils;
import com.andack.indoorman.adapter.FanHaoAdapter;
import com.andack.indoorman.entity.FanHaoEntity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/24
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class FanHaoFragment extends Fragment {
    private RecyclerView fanhaoShowRecycle;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<FanHaoEntity> fanHaoEntities;
    private FanHaoAdapter fanHaoAdapter;
    private String thisUrl;
    private ProgressBar progressBar;
    private boolean pull=false;
    private boolean drop=false;
    private int currentPage=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fanhao_fragment,container,false);

        initData();
        initView(view);
        return view;

    }

    private void initView(View view) {
        fanhaoShowRecycle= (RecyclerView) view.findViewById(R.id.recyclerview);
        fanhaoShowRecycle.setItemAnimator(new DefaultItemAnimator());
        fanhaoShowRecycle.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        fanHaoAdapter=new FanHaoAdapter(fanHaoEntities,getContext());
        fanhaoShowRecycle.setAdapter(fanHaoAdapter);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fanhao_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new getFanHaoData().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData() {
        fanHaoEntities=new ArrayList<>();
        if (currentPage==1) {
            thisUrl= ContentClass.FANHAO_BASEURL+"/fanhao/fanhao.html";

        }else if (currentPage>1){
            thisUrl=ContentClass.FANHAO_BASEURL+"/fanhao/fanhao"+currentPage+".html";
            L.i("this Url is "+thisUrl);
        }
    }

    class getFanHaoData extends AsyncTask<Void,Void,ArrayList<FanHaoEntity>>{

        @Override
        protected ArrayList<FanHaoEntity> doInBackground(Void... params) {
            try {
                fanHaoEntities= NetUils.FanHaoParse(thisUrl);

            } catch (IOException e) {
                L.i("请求异常");
            }
            return fanHaoEntities;
        }

        @Override
        protected void onPostExecute(ArrayList<FanHaoEntity> fanHaoEntities) {
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            fanhaoShowRecycle.setVisibility(View.VISIBLE);
            fanHaoAdapter.addAll(fanHaoEntities);
        }
    }
}
