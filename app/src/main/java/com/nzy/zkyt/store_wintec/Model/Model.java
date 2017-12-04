package com.nzy.zkyt.store_wintec.Model;

import java.util.List;

/**
 * 作者：宁震宇on 2017/12/2.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class Model {
    private List<Items> banner;//轮播
    private List<Items> types;//分类
    private List<Items> datas;//分类

    public List<Items> getBanner() {
        return banner;
    }

    public void setBanner(List<Items> banner) {
        this.banner = banner;
    }

    public List<Items> getTypes() {
        return types;
    }

    public void setTypes(List<Items> types) {
        this.types = types;
    }

    public List<Items> getDatas() {
        return datas;
    }

    public void setDatas(List<Items> datas) {
        this.datas = datas;
    }

    public class Items{
        private String showImg;//展示图片
        private String detailsImg;//详情置顶图片
        private String name;//名称    （1，我是轮播，2，分类1,3，苏宁易购）
        private String type;//类型    （1，轮播，2，分类，3，app）
        private String Point;//指向    （1，app,2 分类列表）

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

}
