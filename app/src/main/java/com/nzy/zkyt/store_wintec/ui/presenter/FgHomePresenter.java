package com.nzy.zkyt.store_wintec.ui.presenter;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.nzy.zkyt.store_wintec.Model.HomeItem;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.base.AppConst;
import com.nzy.zkyt.store_wintec.ui.DetailsActivity;
import com.nzy.zkyt.store_wintec.ui.adapter.HomeAdapter;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.base.BasePresenter;
import com.nzy.zkyt.store_wintec.ui.data.AnalogData;
import com.nzy.zkyt.store_wintec.ui.view.FgHomeView;
import com.nzy.zkyt.store_wintec.util.UIUtils;

import java.util.List;

import static com.nzy.zkyt.store_wintec.ui.adapter.HomeAdapter.APK_LAYOUT;
import static com.nzy.zkyt.store_wintec.ui.adapter.HomeAdapter.TYPES_LAYOUT;
import static com.nzy.zkyt.store_wintec.util.UIUtils.getString;

/**
 * 作者：宁震宇on 2017/11/30.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class FgHomePresenter extends BasePresenter<FgHomeView> {
    private List data;
//    private DetailAdapter detailAdapter;
    private HomeAdapter adapter;

    public FgHomePresenter(BaseActivity context) {
        super(context);
    }

    public void setMessage() {
        data= AnalogData.getHomeData();//模拟数据
        getView().getRv().setLayoutManager(new GridLayoutManager(mContext, 10, GridLayoutManager.VERTICAL, false));
        adapter = new HomeAdapter(mContext, data);
        getView().getRv().setAdapter(adapter);
        getView().getRv().addOnScrollListener(new RecyclerViewScrollListener());
        adapter.setOnItemClickListener((helper, itemView, position, viewType) -> {
            Log.i("aaaaaaaaaaaaaaa",position+"----"+viewType);
            switch (viewType){
                case TYPES_LAYOUT:
                    UIUtils.showToast("点击了分类"+position);
                    break;
                case APK_LAYOUT:
//                    mContext.jumpToActivity(DetailsActivity.class);
                    HomeItem homeItem = (HomeItem) adapter.getItem(position);
                    homeItem.setDetailsImg("ssss");
                    homeItem.setPoint("ssss");
                    Log.i("homehome",homeItem.toString());
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("homeItem", homeItem);
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(mContext,
                                    (ImageView)helper.getView(R.id.iv_home_item), getString(R.string.transition_book_img));
                    ActivityCompat.startActivity(mContext, intent, options.toBundle());
                    break;
            }
        });
    }

    class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        boolean isSlidingToLast = false;
        int overallXScroll = 0;//当前列表高度
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (data.size() < 1) {
                return;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                GridLayoutManager manager = (GridLayoutManager)recyclerView.getLayoutManager();
                //当前RecyclerView显示出来的最后一个的item的position,默认为-1   first
//            int lastPosition = (layoutManager).findLastVisibleItemPosition();
                int lastPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstPosition =manager.findFirstVisibleItemPosition();
                int totalItemCount =manager.getItemCount();
                if (lastPosition ==(totalItemCount - 1) && isSlidingToLast) {
                    //加载更多功能的代码
                    adapter.addMoreData(AnalogData.newHomeData());
                }
            }
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
            if (dy > 0) {
                //大于0表示正在向右滚动
                isSlidingToLast = true;
            } else {
                //小于等于0表示停止或向左滚动
                isSlidingToLast = false;
            }
            overallXScroll = overallXScroll + dy;// 累加y值 解决滑动一半y值为0
            if (overallXScroll <= 0) {   //设置标题的背景颜色
                getView().setSearch(0);
            } else if (overallXScroll > 0 && overallXScroll <= AppConst.BANNER_HEIGHT) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                float scale = (float) overallXScroll / AppConst.BANNER_HEIGHT;//得到当前位置的百分比
                getView().setSearch(scale);
            } else {
                getView().setSearch(1);
            }
        }
    }
}
