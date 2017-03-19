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
import com.andack.indoorman.ViewPageFragment.IndoorCatAver;
import com.andack.indoorman.ViewPageFragment.IndoorCatBoring;
import com.andack.indoorman.ViewPageFragment.IndoorCatGentleman;
import com.andack.indoorman.ViewPageFragment.IndoorCatGif;
import com.andack.indoorman.ViewPageFragment.IndoorCatGodGirl;
import com.andack.indoorman.ViewPageFragment.IndoorCatPicture;
import com.andack.indoorman.ViewPageFragment.IndoorCatWelfare;

import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/19
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class IndoorManCat extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_indoormancat,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout= (TabLayout) view.findViewById(R.id.tablayout_indoormancat);
        mViewPager= (ViewPager) view.findViewById(R.id.viewpager_indoormancat);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        titles=new ArrayList<>();
        fragments=new ArrayList<>();
        titles.add("绅士学院");     //gentleman
        titles.add("日本女忧");     //AVer
        titles.add("宅男福利");     //welfare
        titles.add("宅男女神");     //godGirls
        titles.add("宅男图库");     //picture
        titles.add("GIF福利");      //GIF
        titles.add("不许无聊");     //Boring
        fragments.add(new IndoorCatGentleman());
        fragments.add(new IndoorCatAver());
        fragments.add(new IndoorCatWelfare());
        fragments.add(new IndoorCatGodGirl());
        fragments.add(new IndoorCatPicture());
        fragments.add(new IndoorCatGif());
        fragments.add(new IndoorCatBoring());
    }


}
