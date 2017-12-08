package com.nzy.zkyt.store_wintec.ui.data;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.util.SPUtils;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_RIPPLE;
import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED;

/**
 * 作者：宁震宇on 2017/11/30.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class BottomNavigation {
    private BottomNavigationBar bar;
    private static BottomNavigation sdFileUtil;
    private BottomNavigation(BottomNavigationBar bar) {
        this.bar=bar;
    }
    public static BottomNavigation getInstance(BottomNavigationBar bar) {
        if (sdFileUtil == null) {
            synchronized (SPUtils.class) {
                if (sdFileUtil == null) {
                    sdFileUtil = new BottomNavigation(bar);
                    return sdFileUtil;
                }
            }
        }
        return sdFileUtil;
    }
    public void setBar(BottomNavigationBar.OnTabSelectedListener tabSelectedListener){
        /**包含3种Mode:
         MODE_DEFAULT    如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         MODE_FIXED      填充模式，未选中的Item会显示文字，没有换挡动画。
         MODE_SHIFTING   换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         包含3种Style:
         BACKGROUND_STYLE_DEFAULT   如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
         如果Mode为MODE_SHIFTING   将使用BACKGROUND_STYLE_RIPPLE。
         *BACKGROUND_STYLE_STATIC   点击的时候没有水波纹效果
         *BACKGROUND_STYLE_RIPPLE   点击的时候有水波纹效果
         */
        bar
//                .setMode(MODE_FIXED).setBackgroundStyle(BACKGROUND_STYLE_STATIC);
                .setMode(MODE_FIXED).setBackgroundStyle(BACKGROUND_STYLE_RIPPLE);
//                .setMode(MODE_SHIFTING).setBackgroundStyle(BACKGROUND_STYLE_STATIC);
//                .setMode(MODE_SHIFTING).setBackgroundStyle(BACKGROUND_STYLE_RIPPLE);
        bar
                .setActiveColor(R.color.white)//选中颜色 图标和文字
                .setInActiveColor(R.color.colorPrimary)//默认未选择颜色
                .setBarBackgroundColor(R.color.g);//默认背景色
        bar
                .addItem(new BottomNavigationItem(R.mipmap.icon_home, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_classify, "分类"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_my, "我的"))
//                .addItem(new BottomNavigationItem(R.mipmap.icon_home,"首页").setActiveColor("#8e8e8e").setInActiveColor("#ECECEC"))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();//所有的设置需在调用该方法前完成
        //设置lab点击事件
        bar.setTabSelectedListener(tabSelectedListener);
    }

}
