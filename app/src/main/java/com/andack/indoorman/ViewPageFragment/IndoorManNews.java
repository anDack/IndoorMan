package com.andack.indoorman.ViewPageFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andack.indoorman.R;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/19
 * 邮箱：    1160083806@qq.com
 * 描述：    宅男福利社->宅男资讯
 */

public class IndoorManNews extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.indoor_news_zainanfulishe,container,false);

        return view;
    }
}
