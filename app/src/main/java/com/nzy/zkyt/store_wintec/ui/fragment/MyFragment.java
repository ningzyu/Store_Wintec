package com.nzy.zkyt.store_wintec.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.activity.LocalInstalledApkActivity;
import com.nzy.zkyt.store_wintec.ui.activity.MainActivity;
import com.nzy.zkyt.store_wintec.ui.activity.QueryActivity;
import com.nzy.zkyt.store_wintec.ui.activity.SettingActivity;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgMyPresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgMyView;

import butterknife.BindView;


public class MyFragment  extends BaseFragment<FgMyView, FgMyPresenter> implements FgMyView, View.OnClickListener {

    @BindView(R.id.image_manage_search)
    ImageView btn_manage_search;
    @BindView(R.id.btn_update_manage)
    LinearLayout btn_update_manage;
    @BindView(R.id.btn_apk_manage)
    LinearLayout btn_apk_manage;
    @BindView(R.id.btn_install_manage)
    LinearLayout btn_install_manage;
    @BindView(R.id.btn_clear_manage)
    LinearLayout btn_clear_manage;
    @BindView(R.id.btn_setting_manage)
    LinearLayout btn_setting_manage;

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        btn_manage_search.setOnClickListener(this);
        btn_update_manage.setOnClickListener(this);
        btn_setting_manage.setOnClickListener(this);
        btn_apk_manage.setOnClickListener(this);
        btn_install_manage.setOnClickListener(this);
    }

    /**
     * 按键监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.image_manage_search:
                Intent intent = new Intent(getContext(), QueryActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_apk_manage:

                break;

            case R.id.btn_clear_manage:
                break;

            case R.id.btn_install_manage:
                Intent intent1 = new Intent(getContext(), LocalInstalledApkActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_update_manage:
                break;

            case R.id.btn_setting_manage:
                Intent intentSetting = new Intent(getContext(), SettingActivity.class);
                startActivity(intentSetting);
                break;
        }
    }

    @Override
    protected FgMyPresenter createPresenter() {
        return new FgMyPresenter((MainActivity) getActivity());
    }
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_my;
    }

    @Override
    public ImageView getSearchBtn() {
        return btn_manage_search;
    }

    @Override
    public LinearLayout getUpdateManageBtn() {
        return btn_update_manage;
    }

    @Override
    public LinearLayout getInstallManageBtn() {
        return btn_install_manage;
    }

    @Override
    public LinearLayout getApkManageBtn() {
        return btn_apk_manage;
    }

    @Override
    public LinearLayout getClearBtn() {
        return btn_clear_manage;
    }

    @Override
    public LinearLayout getSettingBtn() {
        return btn_setting_manage;
    }
}
