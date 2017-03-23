package com.andack.indoorman.ViewPageFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andack.indoorman.Cache.ACache;
import com.andack.indoorman.R;
import com.andack.indoorman.Utils.ContentClass;
import com.andack.indoorman.Utils.L;
import com.andack.indoorman.Utils.NetUils;
import com.andack.indoorman.Utils.ShareUtil;
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

public class IndoorCatGodGirl extends Fragment {
    protected ListView listView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<ZaiNanFuLiEntity> mData;
    private IndoorManChannelAdapter adapter;
    private ACache aCache;
    private static  String thisChannelUrl= ContentClass.CAT_GODGirLS_URL;
    private static int currentPage=1;
    private static int currentItemNum=ContentClass.PAGE_NUM-3;
    private static boolean pull=false;
    private static boolean drop=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cat_godgirls_fragment,container,false);
        mData=new ArrayList<>();
        initView(view);
        return view;
    }
    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
        adapter=new IndoorManChannelAdapter(getContext(),mData);
        ArrayList<ZaiNanFuLiEntity>temp=new ArrayList<>();
        final ACache aCache=ACache.get(getContext());
        ArrayList<ZaiNanFuLiEntity> list=ToolUtils.getCacheToArrayList(aCache,thisChannelUrl);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        refreshLayout= (SwipeRefreshLayout) view.
                findViewById(R.id.mySwipeRefresh);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount>currentItemNum) {
                    currentPage++;
                    currentItemNum+=10;
                    L.i("下拉加载更多，第"+currentPage);
                    thisChannelUrl+="/page/"+currentPage;
                    drop=true;
                    new getDataTask().execute();
                }
            }
        });
        if (list.equals(temp) ) {
            //如果没有缓存数据
            ShareUtil.putBool(getContext(),"Channel",true);
            L.i("第一次进入没有缓存数据");
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            new getDataTask().execute();
        }else {
            //如果有缓冲数据
            L.i("有缓存数据");
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
                //
                currentItemNum=ContentClass.PAGE_NUM-3;
                currentPage=1;
                pull=true;
                new getDataTask().execute();
                refreshLayout.setRefreshing(false);
            }
        });
    }
    class getDataTask extends AsyncTask<Void,Void,ArrayList<ZaiNanFuLiEntity>>{

        @Override
        protected ArrayList<ZaiNanFuLiEntity> doInBackground(Void... params) {
            try {
                L.i(thisChannelUrl);
                mData= NetUils.JsoupParse(thisChannelUrl);
                thisChannelUrl=ContentClass.CAT_GODGirLS_URL;
                L.i("请求异常");
            } catch (IOException e) {
//               Toast.makeText(getActivity(), "网络请求异常！", Toast.LENGTH_SHORT).show();
                L.i("请求异常");
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
            //如果两者不同数据相同并且是上拉
            //第一步清除缓存数据
            //第二步清除当前第一页数据
            //第三部将新的数据重新加入ListView
            //第四步重新缓存
            L.i("进入刷新界面");

            if (!list.equals(entities)&& pull) {

                L.i("数据不同上拉");
                list.clear();
                adapter.removeAllData();
                adapter.addAll(entities);
                adapter.notifyDataSetChanged();
                ToolUtils.setArrayListToACache(entities,aCache,thisChannelUrl);
                pull=false;
            }else if (!list.equals(entities) && drop){
                //对下面的数据不进行缓存
                adapter.addAll(entities);
                adapter.notifyDataSetChanged();
                //ToolUtils.setArrayListToACache(entities,aCache,thisChannelUrl);
                drop=false;
            }


        }
    }
}
