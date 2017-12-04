package com.nzy.zkyt.store_wintec.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nzy.zkyt.store_wintec.MainActivity;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgMyPresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgMyView;


public class MyFragment  extends BaseFragment<FgMyView, FgMyPresenter> implements FgMyView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
    @Override
    protected FgMyPresenter createPresenter() {
        return new FgMyPresenter((MainActivity) getActivity());
    }
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_my;
    }

}
