package com.andack.indoorman.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andack.indoorman.Activity.WebActivity;
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
 * 项目时间：2017/3/28
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class ContentFragment extends Fragment {

    private static final String PACKAGE="com.me";
    private String mFragmmentName;
    private String thisChannelUrl=ContentClass.INDOOR_CHANNEL_URL;

    private ListView listView;
    private ProgressBar progressBar;
    private ArrayList<ZaiNanFuLiEntity> mData;
    private IndoorManChannelAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private static int currentPage=1;
    private ArrayList<String> mTitles;
    private ArrayList<String> mUrls;
    private static int currentItemNum= ContentClass.PAGE_NUM-3;
    private static boolean pull=false;
    private static boolean isFirst=false;
    private static boolean drop=false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        //这里获得Fragment的具体是什么内容
        mFragmmentName=bundle.getString(PACKAGE);
        getThisChannelUrl(mFragmmentName);
    }

    /**
     * 对当前的URL赋值
     * @param mFragmmentName    当前Fragment的名字
     */
    private void getThisChannelUrl(String mFragmmentName) {
        switch (mFragmmentName) {
            case "频道":
                thisChannelUrl=ContentClass.INDOOR_CHANNEL_URL;
                break;
            case "女神":
                thisChannelUrl=ContentClass.INDOOR_AVER_URL;
                break;
            case "电影":
                thisChannelUrl=ContentClass.INDOOR_MOVIE_URL;
                break;
            case "技术宅":
                thisChannelUrl=ContentClass.INDOOR_SKILL_URL;
                break;
            case "资讯":
                thisChannelUrl=ContentClass.INDOOR_NEWS_URL;
                break;
            case "ACG":
                thisChannelUrl=ContentClass.INDOOR_ACG_URL;
                break;
            case "百科":
                thisChannelUrl=ContentClass.INDOOR_WIKI_URL;
                break;
            case "绅士学院":
                thisChannelUrl=ContentClass.CAT_GENTLEMAN_URL;
                break;
            case "日本女忧":
                thisChannelUrl=ContentClass.CAT_AVER_URL;
                break;
            case "宅男福利":
                thisChannelUrl=ContentClass.CAT_WELARE_URL;
                break;
            case "宅男女神":
                thisChannelUrl=ContentClass.CAT_GODGirLS_URL;
                break;
            case "宅男图库":
                thisChannelUrl=ContentClass.CAT_PICTURE_URL;
                break;
            case "GIF福利":
                thisChannelUrl=ContentClass.CAT_GIF_URL;
                break;

        }
    }

    public static ContentFragment newInstance(String mCategoryName){
        ContentFragment contentFragment=new ContentFragment();
        Bundle bundle=new Bundle();
        bundle.putString(PACKAGE,mCategoryName);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.indoor_channel_zainanfulishe,container,false);
        mData=new ArrayList<>();
        mTitles=new ArrayList<>();
        mUrls=new ArrayList<>();
        initView(view);
//        initData();
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
//                    thisChannelUrl+="/page/"+currentPage;
                    drop=true;
                    new getDataTask().execute(String.valueOf(currentPage));
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("title",mTitles.get(position));

                intent.putExtra("url",mUrls.get(position));
                L.i("title:"+mTitles.get(position)+"url:"+mUrls.get(position));
                startActivity(intent);
            }
        });
        if (list.equals(temp) ) {
            //如果没有缓存数据
            ShareUtil.putBool(getContext(),"Channel",true);
            L.i("第一次进入没有缓存数据");
            isFirst=true;
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            new getDataTask().execute(String.valueOf(0));
        }else {
            //如果有缓冲数据
            L.i("有缓存数据");
            progressBar.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            mData.addAll(list);
            getTitleAndUrl(list);

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
                new getDataTask().execute(String.valueOf(0));
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void getTitleAndUrl(ArrayList<ZaiNanFuLiEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            //获取标题和Url
            mUrls.add(list.get(i).getUrl());
            mTitles.add(list.get(i).getTitle());
        }
    }

    class getDataTask extends AsyncTask<String,Void,ArrayList<ZaiNanFuLiEntity>> {

        @Override
        protected ArrayList<ZaiNanFuLiEntity> doInBackground(String... params) {
            try {

                String Page=params[0];
                String currentUrl=thisChannelUrl;
                L.i("currentUrl:"+currentUrl);
                if (!Page.equals("0")) {
                    currentUrl = thisChannelUrl + "/page/" + Page;
                }
                mData= NetUils.JsoupParse(currentUrl);
            } catch (IOException e) {
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
            if (isFirst){
                removeTitleAndUrl();
                getTitleAndUrl(entities);
                adapter.addAll(entities);
                adapter.notifyDataSetChanged();
                ToolUtils.setArrayListToACache(entities,aCache,thisChannelUrl);
                isFirst=false;
            }
            if (!list.equals(entities)&& pull) {

                L.i("数据不同上拉");
                list.clear();
                adapter.removeAllData();
                removeTitleAndUrl();
                adapter.addAll(entities);
                getTitleAndUrl(entities);
                adapter.notifyDataSetChanged();
                ToolUtils.setArrayListToACache(entities,aCache,thisChannelUrl);
                pull=false;
            }else if (!list.equals(entities) && drop){
                //对下面的数据不进行缓存
                adapter.addAll(entities);
                getTitleAndUrl(entities);
                adapter.notifyDataSetChanged();
                drop=false;
            }
        }
    }

    /**清除不同位置Url和Title
     *
     */
    private void removeTitleAndUrl() {
        mTitles.clear();
        mUrls.clear();
    }
}
