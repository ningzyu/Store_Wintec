package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.os.Handler;

import com.nzy.zkyt.store_wintec.model.Banners;
import com.nzy.zkyt.store_wintec.model.HomeItem;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler.NZYRecyclerAdapter;
import com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler.NZYRecyclerHolder;
import com.nzy.zkyt.store_wintec.ui.data.MyBanner;
import com.nzy.zkyt.store_wintec.util.UIUtils;
import com.nzy.zkyt.store_wintec.widget.DownloadProgressButton;
import com.youth.banner.Banner;

import java.util.List;

/**
 * 作者：宁震宇on 2017/12/6.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class HomeAdapter extends NZYRecyclerAdapter {
    public static final int BANNER_LAYOUT=R.layout.home_banner;
    public static final int TYPES_LAYOUT=R.layout.home_types;
    public static final int APK_LAYOUT=R.layout.home_item;
    public HomeAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public void convert(NZYRecyclerHolder helper, Object item, int position, int type) {
        switch (type){
            case BANNER_LAYOUT:
                Banners mbanner=(Banners)item;
                MyBanner.getInstance((Banner)helper.getView(R.id.main_banner)).setBar(mbanner.getImgs(), mbanner.getTitle(), i -> {
                    UIUtils.showToast(mbanner.getTitle().get(i));
                });
                break;
            case TYPES_LAYOUT:
                helper.setImageGlide(R.id.iv_home_types,((HomeItem) item).getShowImg());
                helper.setText(R.id.tv_home_types,((HomeItem) item).getName());

                break;
            case APK_LAYOUT:
                helper.setImageGlide(R.id.iv_home_item,((HomeItem) item).getShowImg());
                helper.setText(R.id.tv_home_item,((HomeItem) item).getName());
//                DownloadProgressButton button=helper.getView(R.id.dowBtn);
//                button.setShowBorder(false);
//                button.setCurrentText("安装");
//                button.setOnClickListener(v ->
//                        showTheButton(button)
//                );
//                button.setButtonRadius( button.getHeight() / 2);
//                button.postInvalidate();
                break;
        }
    }
    private void showTheButton(DownloadProgressButton button) {
        if (button.getProgress() + 10 > 100) {
            button.setState(DownloadProgressButton.STATE_FINISH);
            button.setCurrentText("安装中");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    button.setState(DownloadProgressButton.STATE_NORMAL);
                    button.setCurrentText("打开");
                }
            }, 2000);   //2秒
        }
        if (button.getState() == DownloadProgressButton.STATE_NORMAL
                || button.getState() == DownloadProgressButton.STATE_PAUSE) {
            button.setState(DownloadProgressButton.STATE_DOWNLOADING);
            button.setProgressText("下载中", button.getProgress() + 8);
            return;
        }
        if (button.getState() == DownloadProgressButton.STATE_DOWNLOADING) {
            button.setState(DownloadProgressButton.STATE_PAUSE);
            button.setCurrentText("继续");
            return;
        }
    }
    @Override
    public int getSpan(int type, Object item) {
        switch (type){
            case BANNER_LAYOUT:
                return 10;
            case TYPES_LAYOUT:
                return 2;
            case APK_LAYOUT:
                if (UIUtils.getScreen()==0){
                    return 10;
                }else {
                    return 5;
                }

        }
        return 1;
    }

    @Override
    public int getType(int position, Object item) {
        if (position==0){
            return BANNER_LAYOUT;
        }else if (((HomeItem)item).getType().equals("TYPE")){
            return TYPES_LAYOUT;
        }else {
            return APK_LAYOUT;
        }
    }
}
