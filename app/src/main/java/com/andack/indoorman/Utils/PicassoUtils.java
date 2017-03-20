package com.andack.indoorman.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.andack.indoorman.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 项目名称：MyMoocProject2
 * 项目作者：anDack
 * 项目时间：2017/2/7
 * 邮箱：    1160083806@qq.com
 * 描述：    对Picasso进行一个简单的封装
 */

public class PicassoUtils {
    public static void DefultLoadImg(Context context, String url, ImageView imageView)
    {
        Picasso.with(context).load(url).placeholder(R.drawable.loading).into(imageView);
    }
    public static void HaveSizeLoadImg(Context context, String url, ImageView imageView,
                                       int width,int height)
    {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context)
                    .load(url)
                    .resize(width, height)
                    .error(R.drawable.error)
                    .centerCrop()
                    .placeholder(R.drawable.ic_loading)
                    .into(imageView);
        }


    }
    public static void HaveMultipleLoadImg(Context context, String url, ImageView imageView,
                                           int width,int height)
    {
        Picasso.with(context)
                .load(url)
                .transform(new CropSquareTransformation())
                .into(imageView);
    }
    public static class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() {
            return "PcDack";
        }
    }
}
