package com.nzy.zkyt.store_wintec.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.base.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：宁震宇on 2017/11/1.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;
    protected FrameLayout baseView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.barTitle)
    TextView barTitle;
    @BindView(R.id.barMenu_txt)
    TextView barMenu_txt;
    @BindView(R.id.barMenu_img)
    ImageView barMenu_img;
    OnClickListener onClickListenerTopLeft;
    public interface OnClickListener {
        void onClick();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.activities.add(this);
        init();
        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(R.layout.base_toolbar);
        baseView = (FrameLayout) findViewById(R.id.baseView);
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(provideContentViewId(), baseView);
        ButterKnife.bind(this);
        setupAppBarAndToolbar();
        initView();
        initData();
        initListener();
    }

    /**
     * 设置AppBar和Toolbar
     */
    private void setupAppBarAndToolbar() {
        //初始化设置 Toolbar

//        Window window = getWindow();
//        //隐藏状态栏
//        //定义全屏参数
//        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        //设置当前窗体为全屏显示
//        window.setFlags(flag, flag);
        //沉浸式状态栏
//        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.colorPrimaryDark), 10);
        if (getBarTitle()==null||getBarTitle().equals("")){
            toolbar.setVisibility(View.GONE);
        }else {
            toolbar.setVisibility(View.VISIBLE);
        }
        barTitle.setText(getBarTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init() {
    }

    protected abstract void initView();

    protected abstract String getBarTitle();

    protected  void initData(){};

    public void initListener() {
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected void setTopLeftButton() {
        setTopLeftButton(R.drawable.back, () -> finish());
    }

    protected void setTopLeftButton(OnClickListener onClickListener) {
        setTopLeftButton(R.drawable.back, onClickListener);
    }

    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener) {
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

    protected void setTopRightButton(String menuStr, View.OnClickListener onClickListener) {
        barMenu_txt.setVisibility(View.VISIBLE);
        barMenu_img.setVisibility(View.GONE);
        barMenu_txt.setText(menuStr);
        barMenu_txt.setOnClickListener(onClickListener);
    }

    protected void setTopRightButton(int menuResId,View.OnClickListener onClickListener) {
//        barMenu.setBackground(getDrawable(menuResId));
        barMenu_img.setVisibility(View.VISIBLE);
        barMenu_txt.setVisibility(View.GONE);
        barMenu_img.setImageResource(menuResId);
        barMenu_img.setOnClickListener(onClickListener);
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
    public void jumpToActivity(Class activity,String key,String extra) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(key,extra);
        startActivity(intent);
    }
    public void jumpToWebViewActivity(String url) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra("url", url);
//        jumpToActivity(intent);
    }


    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}

