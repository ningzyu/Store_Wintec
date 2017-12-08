package com.nzy.zkyt.store_wintec.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.nzy.zkyt.store_wintec.ui.MainActivity;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgHomePresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgHomeView;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<FgHomeView, FgHomePresenter> implements FgHomeView {
    @BindView(R.id.rv_home)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_search)
    LinearLayout search;
//    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;
    @Override
    protected FgHomePresenter createPresenter() {
        return new FgHomePresenter((MainActivity)getActivity());
    }

    @Override
    public void initView(View rootView) {
        toolbar.getBackground().setAlpha(100);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.setMessage();
    }
//    List<String> list= Arrays.asList("http://pic1.win4000.com/wallpaper/d/591e65441ef19.jpg",
//            "http://pic1.win4000.com/wallpaper/8/591e647e75e83.jpg",
//            "http://pic1.win4000.com/wallpaper/4/591fe074e19b4.jpg",
//            "http://pic1.win4000.com/wallpaper/c/5912dab62a6f4.jpg",
//            "http://pic1.win4000.com/wallpaper/8/591e647f8a433.jpg",
//            "http://pic1.win4000.com/wallpaper/c/5912dab62a6f4.jpg",
//            "http://pic1.win4000.com/wallpaper/d/591e65478b938.jpg");
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public RecyclerView getRv() {
        return rv;
    }

    @Override
    public LinearLayout getSearch() {
        return search;
    }
    @Override
    public void setSearch(float Alpha) {
        //搜索框初始透明度
        float initalpha =130;
        float alpha = (255 * Alpha);
        float alphaSearch = ((255-initalpha)* Alpha)+initalpha;
        toolbar.setBackgroundColor(Color.argb((int) alpha, 204, 204, 204));
        search.getBackground().setAlpha((int) alphaSearch);
    }





}
