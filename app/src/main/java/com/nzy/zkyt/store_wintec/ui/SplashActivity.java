package com.nzy.zkyt.store_wintec.ui;

import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.base.BasePresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.activity_splash)
    RelativeLayout layout;
    @Override
    public void initData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                jumpToActivity(MainActivity.class);
                finish();
            }
        }, 2000);
    }
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
//        PermissionGen.needPermission(this,200,Manifest.permission.CAMERA);
//        PermissionGen.with(this)
//                .addRequestCode(100)
//                .permissions(
//                        //电话通讯录
//                        Manifest.permission.GET_ACCOUNTS,
//                        Manifest.permission.READ_PHONE_STATE,
//                        //位置
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        //相机、麦克风
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WAKE_LOCK,
//                        Manifest.permission.CAMERA,
//                        //存储空间
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_SETTINGS
//                ).request();


    }

    @Override
    protected void initView() {
    }

    @Override
    protected String getBarTitle() {
        return null;
    }
}
