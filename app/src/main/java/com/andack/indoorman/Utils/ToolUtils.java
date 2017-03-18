package com.andack.indoorman.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/18
 * 邮箱：    1160083806@qq.com
 * 描述：    工具类
 */

public class ToolUtils {
    //设置字体
    public static void setFont(Context context, TextView textView){
        Typeface fontType=Typeface.createFromAsset(context.getAssets(),"fonts/YYG.TTF");
        textView.setTypeface(fontType);
    }
}
