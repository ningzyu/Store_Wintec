package com.nzy.zkyt.store_wintec;

import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.nzy.zkyt.store_wintec.ui.adapter.CommonFragmentPagerAdapter;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.data.BottomNavigation;
import com.nzy.zkyt.store_wintec.ui.fragment.FragmentFactory;
import com.nzy.zkyt.store_wintec.ui.presenter.MainPresenter;
import com.nzy.zkyt.store_wintec.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页   Home
 * 分类   classify
 * 我的   my
 */


public class MainActivity  extends BaseActivity<MainView,MainPresenter> implements MainView {
    @BindView(R.id.main_bar)
    BottomNavigationBar bar;
    @BindView(R.id.vpContent)
    ViewPager viewPager;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    @Override
    protected void initView() {
        //设置ViewPager的最大缓存页面
        viewPager.setOffscreenPageLimit(3);
        mFragmentList.add(FragmentFactory.getInstance().getFg01());
        mFragmentList.add(FragmentFactory.getInstance().getFg02());
        mFragmentList.add(FragmentFactory.getInstance().getFg03());
        BottomNavigation.getInstance(bar).setBar(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position, false);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
        viewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                bar.selectTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    protected String getBarTitle() {
        return "";
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
}
