package com.nzy.zkyt.store_wintec.ui.presenter;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nzy.zkyt.store_wintec.Model.Banners;
import com.nzy.zkyt.store_wintec.Model.HomeItem;
import com.nzy.zkyt.store_wintec.base.AppConst;
import com.nzy.zkyt.store_wintec.ui.adapter.DetailAdapter;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.base.BasePresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgHomeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：宁震宇on 2017/11/30.
 * 邮箱：348723352@qq.com
 * 本类作用：
 *
 */

public class FgHomePresenter extends BasePresenter<FgHomeView> {
    private List data;
    private DetailAdapter detailAdapter;
    int overallXScroll = 0;
    public FgHomePresenter(BaseActivity context) {
        super(context);
    }
    public void setMessage(){
        data();
        getView().getRv().setLayoutManager(new GridLayoutManager(mContext, 5, GridLayoutManager.VERTICAL, false));
        detailAdapter = new DetailAdapter(mContext,data);
        getView().getRv().setAdapter(detailAdapter);

        getView().getRv().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                //当前RecyclerView显示出来的最后一个的item的position,默认为-1   first
//                int lastPosition =((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                int firstPosition=((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
//                if (firstPosition>=1){
//                    getView().getBar().getBackground().setAlpha(255);
//                }
//                Log.i("aaaaaaaaaaa","当前显示第一项="+firstPosition+"---当前显示最后="+lastPosition);
//                if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                    overallXScroll = overallXScroll + dy;// 累加y值 解决滑动一半y值为0
                    if (overallXScroll <= 0) {   //设置标题的背景颜色
                        getView().getBar().setBackgroundColor(Color.argb( 0, 204, 204, 204));
                        getView().getSearch().getBackground().setAlpha(125);
                    } else if (overallXScroll > 0 && overallXScroll <= AppConst.BANNER_HEIGHT) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                        float scale = (float) overallXScroll / AppConst.BANNER_HEIGHT;//得到当前位置的百分比
                        float alpha = (255 * scale);
                        float alphaSearch = (130 * scale)+125;
                        getView().getBar().setBackgroundColor(Color.argb((int) alpha, 204, 204, 204));
                        getView().getSearch().getBackground().setAlpha((int) alphaSearch);
                    } else {
                        getView().getBar().setBackgroundColor(Color.argb(255, 204, 204, 204));
                        getView().getSearch().getBackground().setAlpha(255);
                    }
                }
        });
    }
//    /**
//     * RecyclerView 滑动监听器
//     */
//    class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            if (mList.size() < 1) {
//                return;
//            }
//            //如果正在下拉刷新则放弃监听状态
//            if (mRefreshLayout.isRefreshing()) {
//                return;
//            }
//
//        }
//    }
    private void data(){
        data=new ArrayList();
        data.add(new Banners(Arrays.asList(
                "https://images.pexels.com/photos/428124/pexels-photo-428124.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/418281/pexels-photo-418281.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/428062/pexels-photo-428062.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/569098/pexels-photo-569098.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb"
        ),Arrays.asList("轮播图1","轮播图2","轮播图3","轮播图4")));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png","必备","TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png","必玩","TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png","热门","TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png","分类","TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/rn.png","游戏","TYPE"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png","微信","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_taobao.png","淘宝","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_qq.png","QQ","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_tctv.png","腾讯视频","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weibo.png","微博","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png","美拍","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png","微信","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_taobao.png","淘宝","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_qq.png","QQ","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_tctv.png","腾讯视频","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weibo.png","微博","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png","美拍","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png","微信","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_taobao.png","淘宝","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_qq.png","QQ","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_tctv.png","腾讯视频","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_weibo.png","微博","APK"));
        data.add(new HomeItem("http://www.xixihaha.xin/image/logo_meipai.png","美拍","APK"));
    }



}
