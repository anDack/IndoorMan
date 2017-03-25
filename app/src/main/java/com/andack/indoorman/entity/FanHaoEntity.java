package com.andack.indoorman.entity;

import com.andack.indoorman.Utils.ContentClass;

/**
 * 项目名称：IndoorMan
 * 项目作者：anDack
 * 项目时间：2017/3/24
 * 邮箱：    1160083806@qq.com
 * 描述：    番号的Adapter
 */

public class FanHaoEntity {
    private String imageUrl;
    private String fanhao;

    public String getImageUrl() {
        return ContentClass.FANHAO_BASEURL+imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFanhao() {
        return fanhao;
    }

    public void setFanhao(String fanhao) {
        this.fanhao = fanhao;
    }
}
