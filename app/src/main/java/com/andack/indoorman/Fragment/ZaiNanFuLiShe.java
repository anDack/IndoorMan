package com.andack.indoorman.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andack.indoorman.R;
import com.andack.indoorman.ViewPageFragment.IndoorManACG;
import com.andack.indoorman.ViewPageFragment.IndoorManChannel;
import com.andack.indoorman.ViewPageFragment.IndoorManGodGirls;
import com.andack.indoorman.ViewPageFragment.IndoorManMovie;
import com.andack.indoorman.ViewPageFragment.IndoorManNews;
import com.andack.indoorman.ViewPageFragment.IndoorManSkill;
import com.andack.indoorman.ViewPageFragment.IndoorManWiki;
import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/18
 * 邮箱：    1160083806@qq.com
 * 描述：    宅男福利社的Fragment
 */

public class ZaiNanFuLiShe extends Fragment {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private int PageNum = 0;
    private ArrayList<ZaiNanFuLiEntity> entities;
    //    private MyFragmentPagerAdapter adapter;
//    private Fragment currentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zainanfulishe, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTablayout = (TabLayout) view.findViewById(R.id.tablayout_zainanfuli);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_zainanfuli);
        //ViewPager预加载
        mViewPager.setOffscreenPageLimit(fragments.size());
        //如果是多层嵌套Fragment，就必须使用getChildFragmentManager()方法去获得fragmentManager对象否则只能运行以此
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                PageNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//            public Fragment currentFragment;
//
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//            @Override
//            public void setPrimaryItem(ViewGroup container, int position, Object object) {
//                this.currentFragment= (Fragment) object;
//                super.setPrimaryItem(container, position, object);
//            }
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titles.get(position);
//            }
//        });
        //绑定ViewPager
        mTablayout.setupWithViewPager(mViewPager);

    }

    private void initData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new IndoorManChannel());          //宅男频道
        fragments.add(new IndoorManGodGirls());         //宅男女神
        fragments.add(new IndoorManMovie());            //宅男电影
        fragments.add(new IndoorManSkill());            //技术宅
        fragments.add(new IndoorManNews());             //宅男资讯
        fragments.add(new IndoorManACG());              //ACG社区
        fragments.add(new IndoorManWiki());             //宅男百科
        titles.add("频道");
        titles.add("女神");
        titles.add("电影");
        titles.add("技术宅");
        titles.add("资讯");
        titles.add("ACG");
        titles.add("百科");
//        final ACache aCache = ACache.get(getContext());
//        entities = new ArrayList<>();
//        L.i("初始化数据");
//        if (ShareUtil.GetBool(getContext(), "isLaunch", false)) {
//            L.i("the not first time");
//            entities = ToolUtils.getCacheToArrayList(aCache);
//            L.i("get the entities" + entities.size());
//        } else {
//            //第一次请求
//            //缓存数据
//            L.i("the first time");
//            ShareUtil.putBool(getContext(), "isLunched", true);
//            //请求当前页的数据
//            ContentThread thread = new ContentThread(ContentClass.IndoorManUrls[PageNum], new OnJsoupPraseListener() {
//                @Override
//                public void onSuccess(ArrayList<ZaiNanFuLiEntity> tempEntity) {
//                    entities = tempEntity;
//                    ToolUtils.setArrayListToACache(entities, aCache);
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    Toast.makeText(getContext(), "请求似乎失败了", Toast.LENGTH_SHORT).show();
//                }
//            });
//            thread.start();
//
//        }
    }
}