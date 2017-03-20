package com.andack.indoorman.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andack.indoorman.R;
import com.andack.indoorman.Utils.PicassoUtils;
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
    private int Width;
    public IndoorManChannelAdapter(Context context, ArrayList<ZaiNanFuLiEntity> arrayList){
        this.context=context;
        this.datas=arrayList;
        inflater= (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Width = dm.widthPixels;    //得到宽度
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
        ViewHolder viewHolder=null;
        if (convertView==null) {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.listview_indoor_channel_zainanfulishe,parent,false);
            viewHolder.title= (TextView) convertView.findViewById(R.id.title_indoor);
            viewHolder.content= (TextView) convertView.findViewById(R.id.content_indoor);
            viewHolder.borwse= (TextView) convertView.findViewById(R.id.browse_indoor);
            viewHolder.time= (TextView) convertView.findViewById(R.id.time_indoor);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.image_indoor);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(datas.get(position).getTitle());
        viewHolder.content.setText(datas.get(position).getShortContent());
        viewHolder.borwse.setText(datas.get(position).getBrowse());
        viewHolder.time.setText(datas.get(position).getTime());
        //加载图片
        PicassoUtils.HaveSizeLoadImg(context,datas.get(position).getImageUrl(),viewHolder.imageView,Width/3,200);
        return convertView;
    }
    class ViewHolder{
        private TextView title;
        private TextView content;
        private TextView time;
        private TextView borwse;
        private ImageView imageView;
    }
}
