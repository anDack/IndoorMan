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
    private String[] categoryName=new String[]{
            "绅士学院","日本女忧","宅男福利","宅男女神","宅男图库","GIF福利"
    };
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
        for (int i = 0; i < categoryName.length; i++) {
            fragments.add(ContentFragment.newInstance(categoryName[i]));
            titles.add(categoryName[i]);
        }

    }


}
