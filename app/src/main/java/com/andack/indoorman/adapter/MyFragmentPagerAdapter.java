package com.andack.indoorman.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/22
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    public Fragment currentFragment;
    private ArrayList<String> titles;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments,ArrayList<String>titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }
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
}
