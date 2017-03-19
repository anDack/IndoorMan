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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zainanfulishe,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTablayout= (TabLayout) view.findViewById(R.id.tablayout_zainanfuli);
        mViewPager= (ViewPager) view.findViewById(R.id.viewpager_zainanfuli);
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
        //绑定ViewPager
        mTablayout.setupWithViewPager(mViewPager);

    }

    private void initData() {
        fragments=new ArrayList<>();
        titles=new ArrayList<>();
        fragments.add(new IndoorManChannel());          //宅男频道
        fragments.add(new IndoorManGodGirls());         //宅男女神
        fragments.add(new IndoorManMovie());            //宅男电影
        fragments.add(new IndoorManSkill());            //技术宅
        fragments.add(new IndoorManNews());             //宅男资讯
        fragments.add(new IndoorManACG());              //ACG社区
        fragments.add(new IndoorManWiki());             //宅男百科
        //TODO:将魔法值写入xml
        titles.add("频道");
        titles.add("女神");
        titles.add("电影");
        titles.add("技术宅");
        titles.add("资讯");
        titles.add("ACG");
        titles.add("百科");

    }


}
