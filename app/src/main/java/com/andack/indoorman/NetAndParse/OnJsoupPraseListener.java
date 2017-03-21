package com.andack.indoorman.NetAndParse;

import com.andack.indoorman.entity.ZaiNanFuLiEntity;

import java.util.ArrayList;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/21
 * 邮箱：    1160083806@qq.com
 * 描述：    解析数据的回调接口
 */

public interface OnJsoupPraseListener {
    void onSuccess(ArrayList<ZaiNanFuLiEntity> entities);
    void onFailure(Exception e);
}
