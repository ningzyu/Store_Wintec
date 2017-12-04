package com.nzy.zkyt.store_wintec.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.nzy.zkyt.store_wintec.MainActivity;
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
    public Toolbar getBar() {
        return toolbar;
    }

    @Override
    public LinearLayout getSearch() {
        return search;
    }
}
