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
    private String[] categoryName=new String[]{
            "频道", "女神","电影", "技术宅", "资讯","ACG","百科"
    };
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
        //绑定ViewPager
        mTablayout.setupWithViewPager(mViewPager);

    }

    private void initData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        for (int i = 0; i < categoryName.length; i++) {
            fragments.add(ContentFragment.newInstance(categoryName[i]));
            titles.add(categoryName[i]);
        }
//        fragments.add(ContentFragment.newInstance("频道"));          //宅男频道
//        fragments.add(ContentFragment.newInstance("女神"));         //宅男女神
//        fragments.add(ContentFragment.newInstance("电影"));            //宅男电影
//        fragments.add(ContentFragment.newInstance("技术宅"));            //技术宅
//        fragments.add(ContentFragment.newInstance("资讯"));             //宅男资讯
//        fragments.add(ContentFragment.newInstance("ACG"));              //ACG社区
//        fragments.add(ContentFragment.newInstance("百科"));             //宅男百科
//        titles.add("频道");
//        titles.add("女神");
//        titles.add("电影");
//        titles.add("技术宅");
//        titles.add("资讯");
//        titles.add("ACG");
//        titles.add("百科");
    }
}