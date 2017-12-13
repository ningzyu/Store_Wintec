package com.nzy.zkyt.store_wintec.ui.fragment;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.MainActivity;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgMyPresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgMyView;


public class MyFragment  extends BaseFragment<FgMyView, FgMyPresenter> implements FgMyView {
    @Override
    protected FgMyPresenter createPresenter() {
        return new FgMyPresenter((MainActivity) getActivity());
    }
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_my;
    }

}
