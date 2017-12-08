package com.nzy.zkyt.store_wintec.ui.data;

import com.nzy.zkyt.store_wintec.Model.Banners;
import com.nzy.zkyt.store_wintec.Model.HomeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：宁震宇on 2017/12/4.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class AnalogData {


    public static List getHomeData() {
        List data = new ArrayList();
        data.add(new Banners(Arrays.asList(
                "https://images.pexels.com/photos/428124/pexels-photo-428124.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/418281/pexels-photo-418281.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/428062/pexels-photo-428062.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/569098/pexels-photo-569098.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb"
        ), Arrays.asList("轮播图1", "轮播图2", "轮播图3", "轮播图4")));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png", "必备", "TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png", "必玩", "TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png", "热门", "TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png", "分类", "TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png", "游戏", "TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png", "微信", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_taobao.png", "淘宝", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_qq.png", "QQ", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_tctv.png", "腾讯视频", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weibo.png", "微博", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "美拍", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png", "微信", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_taobao.png", "淘宝", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_qq.png", "QQ", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_tctv.png", "腾讯视频", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weibo.png", "微博", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "美拍", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png", "微信", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_taobao.png", "淘宝", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_qq.png", "QQ", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_tctv.png", "腾讯视频", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weibo.png", "微博", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "美拍", "APK"));
        return data;
    }

    public static List newHomeData() {
        List data = new ArrayList();
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png", "更多", "APK"));
        return data;
    }


}
