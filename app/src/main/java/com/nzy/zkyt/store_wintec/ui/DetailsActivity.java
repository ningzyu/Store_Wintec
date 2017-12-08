package com.nzy.zkyt.store_wintec.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nzy.zkyt.store_wintec.Model.HomeItem;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.fragment.FragmentFactory;
import com.nzy.zkyt.store_wintec.ui.presenter.DetailsPresenter;
import com.nzy.zkyt.store_wintec.ui.view.DetailsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity<DetailsView,DetailsPresenter> implements DetailsView {
    @BindView(R.id.iv_Apk)
    ImageView ivApk;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.details_tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.coll_toolbar)
    CollapsingToolbarLayout collToolbar;
    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//        HomeItem homeItem = (HomeItem) getIntent().getSerializableExtra("homeItem");
        HomeItem homeItem = new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png","weixin","cc");
        Log.i("DetailsActivityaaaa",homeItem.toString());
//        collToolbar.setTitle(homeItem.getName());
//        collToolbar.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
//        collToolbar.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置
//        collToolbar.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
//        collToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后标题的颜色
        Glide.with(ivApk.getContext())
                .load(homeItem.getShowImg())
                .fitCenter()
                .into(ivApk);


        collToolbar.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return false;

            }
        });
        setupViewPager(viewPager);
        tabs.addTab(tabs.newTab().setText("详情"));
        tabs.addTab(tabs.newTab().setText("推荐"));
        tabs.setupWithViewPager(viewPager);



    }
    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentFactory.getInstance().getDetailsFg(), "详情");
        adapter.addFragment(FragmentFactory.getInstance().getAdviceFg(), "推荐");
        mViewPager.setAdapter(adapter);
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
