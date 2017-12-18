package com.nzy.zkyt.store_wintec.ui.fragment;


import com.nzy.zkyt.store_wintec.ui.activity.MainActivity;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgClassifyPresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgClassifyView;

public class ClassifyFragment extends BaseFragment<FgClassifyView, FgClassifyPresenter> implements FgClassifyView {

    @Override
    protected FgClassifyPresenter createPresenter() {
        return new FgClassifyPresenter((MainActivity)getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_classify;
    }

}
