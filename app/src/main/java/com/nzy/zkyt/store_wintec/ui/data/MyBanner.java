package com.nzy.zkyt.store_wintec.ui.data;

import android.util.Log;
import android.view.ViewGroup;

import com.nzy.zkyt.store_wintec.base.AppConst;
import com.nzy.zkyt.store_wintec.util.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * 作者：宁震宇on 2017/12/1.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class MyBanner {
    private Banner mBanner;
    private static MyBanner sdFileUtil;
    private MyBanner(Banner mBanner) {
        this.mBanner=mBanner;
    }
    public static MyBanner getInstance(Banner bar) {
        if (sdFileUtil == null) {
            synchronized (MyBanner.class) {
                if (sdFileUtil == null) {
                    sdFileUtil = new MyBanner(bar);
                    return sdFileUtil;
                }
            }
        }
        return sdFileUtil;
    }
    public void setBar(List<?> imgs,List<String> titles,OnBannerListener onBannerListener){
        Log.i("aaaaaaaaa",imgs.size()+"长度");
        //设置banner的高度为手机屏幕的四分之一
        mBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppConst.BANNER_HEIGHT));
        mBanner.setImages(imgs)
                .setBannerStyle(BannerConfig.NOT_INDICATOR)//内置样式
                .setImageLoader(GlideUtil.GlideImageLoader.imageLoader)
                .setOnBannerListener(onBannerListener)
                .setDelayTime(3000)
                .start();
    }
}
