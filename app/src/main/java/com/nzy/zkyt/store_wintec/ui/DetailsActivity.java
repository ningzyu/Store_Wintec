package com.nzy.zkyt.store_wintec.ui;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.HomeItem;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.fragment.FragmentFactory;
import com.nzy.zkyt.store_wintec.ui.presenter.DetailsPresenter;
import com.nzy.zkyt.store_wintec.ui.view.DetailsView;
import com.nzy.zkyt.store_wintec.util.ResourcesUtil;
import com.nzy.zkyt.store_wintec.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity<DetailsView, DetailsPresenter> implements DetailsView {
    @BindView(R.id.iv_Apk)
    ImageView ivApk;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bar_Title)
    TextView title;
    @BindView(R.id.details_tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;
    //状态值
    int value = 0;

    @Override
    protected void initView() {

        ViewGroup.LayoutParams layoutParams=appBarLayout.getLayoutParams();
        layoutParams.height=(int) UIUtils.getDisplayHeight()/4;
        appBarLayout.setLayoutParams(layoutParams);
        //初始化标题栏
        toolbar.getBackground().setAlpha(0);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.inflateMenu(R.menu.toolbar_menu);
//        HomeItem homeItem = (HomeItem) getIntent().getSerializableExtra("homeItem");
        HomeItem homeItem = new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png", "weixin", "cc");
        Log.i("DetailsActivityaaaa", homeItem.toString());
        appBarLayout.addOnOffsetChangedListener(listener);//设置滑动监听
        Glide.with(ivApk.getContext())
                .load(homeItem.getShowImg())
                .fitCenter()
                .into(ivApk);
        setupViewPager(viewPager);

    }

    AppBarLayout.OnOffsetChangedListener listener=(appBarLayout1, i) -> {
        if (value == i) {
            return;
        }
        value = i;
        int sum = appBarLayout.getTotalScrollRange();//总高度
        int verticalOffset = Math.abs(i);//当前高度
        float f = (float) verticalOffset / sum;
        toolbar.setNavigationIcon(ResourcesUtil.getInstance().setVector(R.drawable.back, ResourcesUtil.getInstance().getColor(f)));
        Log.i("appbarappbar", "3数值" + i + "------绝对值" + verticalOffset + "------百分比" + f + "-----总高度" + sum);
        toolbar.getBackground().setAlpha((int) (255*f));
        title.setTextColor(ResourcesUtil.getInstance().getColorAlpha(f));
        if (verticalOffset == 0) {
        } else if (verticalOffset == sum) {

        } else {
            title.setVisibility(View.VISIBLE);
        }

    };

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentFactory.getInstance().getDetailsFg(), "详情");
        adapter.addFragment(FragmentFactory.getInstance().getAdviceFg(), "推荐");
        mViewPager.setAdapter(adapter);
        tabs.addTab(tabs.newTab().setText("详情"));
        tabs.addTab(tabs.newTab().setText("推荐"));
        tabs.setupWithViewPager(viewPager);
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    protected String getBarTitle() {
        return "";
    }

    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_details;
    }
}
