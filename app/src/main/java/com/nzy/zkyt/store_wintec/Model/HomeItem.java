package com.nzy.zkyt.store_wintec.Model;

/**
 * 作者：宁震宇on 2017/12/1.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class HomeItem {
    private String showImg;//展示图片
    private String detailsImg;//详情置顶图片
    private String name;//名称    （1，我是轮播，2，分类1,3，苏宁易购）
    private String type;//类型    （1，轮播，2，分类，3，app）
    private String Point;//指向    （1，app,2 分类列表）

    public HomeItem(String showImg, String name, String type) {
        this.showImg = showImg;
        this.name = name;
        this.type = type;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getDetailsImg() {
        return detailsImg;
    }

    public void setDetailsImg(String detailsImg) {
        this.detailsImg = detailsImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoint() {
        return Point;
    }

    public void setPoint(String point) {
        Point = point;
    }
}
