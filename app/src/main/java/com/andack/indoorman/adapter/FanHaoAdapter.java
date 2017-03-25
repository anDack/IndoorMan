package com.andack.indoorman.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andack.indoorman.R;
import com.andack.indoorman.Utils.L;
import com.andack.indoorman.Utils.PicassoUtils;
import com.andack.indoorman.entity.FanHaoEntity;

import java.util.ArrayList;
import java.util.Random;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/24
 * 邮箱：    1160083806@qq.com
 * 描述：    TODO
 */

public class FanHaoAdapter extends RecyclerView.Adapter<FanHaoAdapter.ViewHolder> {
    private ArrayList<FanHaoEntity> fanHaoEntities;
    private Context context;
    private LayoutInflater inflater;
    private int Width,Height;
    private static int Num=0;
    public FanHaoAdapter(ArrayList<FanHaoEntity> fanHaoEntities, Context context){
        this.context=context;
        this.fanHaoEntities=fanHaoEntities;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Width = dm.widthPixels;
        Height=dm.heightPixels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.fanhao_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public void removeAll(){
        int size=fanHaoEntities.size();
        fanHaoEntities.clear();
        notifyItemRangeRemoved(0,size);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int HeightTemp=Height/3-new Random().nextInt(120);
//        L.i(HeightTemp+"");
        holder.textView.setText(fanHaoEntities.get(position).getFanhao());
//        PicassoUtils.DefultLoadImg(context,fanHaoEntities.get(position).getImageUrl(),holder.imageView);
        PicassoUtils.HaveSizeLoadImg(context,fanHaoEntities.get(position).getImageUrl(),holder.imageView,
                            Width/2,HeightTemp);
    }
    public void addAll(ArrayList<FanHaoEntity> entities){
        L.i("添加数据");
        fanHaoEntities.addAll(entities);
        notifyItemRangeInserted(Num,entities.size());
        Num+=entities.size();
    }

    @Override
    public int getItemCount() {
        return fanHaoEntities.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.fanhao_img);
            textView= (TextView) itemView.findViewById(R.id.fanhao_tv);
        }
    }
}
