package com.andack.indoorman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/20
 * 邮箱：    1160083806@qq.com
 * 描述：    indoorManChannel的Adapter
 */

public class IndoorManChannelAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ZaiNanFuLiEntity> datas;
    private LayoutInflater inflater;
    public IndoorManChannelAdapter(Context context, ArrayList<ZaiNanFuLiEntity> arrayList){
        this.context=context;
        this.datas=arrayList;
        inflater= (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    class ViewHolder{

    }
}
