package com.nzy.zkyt.store_wintec.ui.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.HomeItem;
import com.nzy.zkyt.store_wintec.ui.base.BaseActivity;
import com.nzy.zkyt.store_wintec.ui.fragment.FragmentFactory;
import com.nzy.zkyt.store_wintec.ui.presenter.DetailsPresenter;
import com.nzy.zkyt.store_wintec.ui.view.DetailsView;
import com.nzy.zkyt.store_wintec.util.BitmapUtil;
import com.nzy.zkyt.store_wintec.util.GlideUtil;
import com.nzy.zkyt.store_wintec.util.ResourcesUtil;
import com.nzy.zkyt.store_wintec.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailsActivity extends BaseActivity<DetailsView, DetailsPresenter> implements DetailsView {
    @BindView(R.id.iv_Apk)
    ImageView ivApk;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bar_Title)
    TextView title;
    @BindView(R.id.bar_Left)
    ImageView barLeft;
    @BindView(R.id.bar_Right)
    ImageView barRight;
    @BindView(R.id.prLayout)
    RelativeLayout prLayout;


    @BindView(R.id.details_tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;
    @BindView(R.id.bar_layout)
    RelativeLayout barLayout;
    //下载按钮
    @BindView(R.id.tv_download)
    TextView download;
    //状态值
    int value = 0;

    @Override
    protected void initView() {
        /**  初始化AppBarLayout高度 */
        ViewGroup.LayoutParams layoutParams = appBarLayout.getLayoutParams();
        layoutParams.height = (int)(UIUtils.getDisplayHeight()*0.5);
        Log.i("cccccccccccccccccc",layoutParams.height+"------------"+layoutParams.width);
        appBarLayout.setLayoutParams(layoutParams);
        //初始化标题栏
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        barLeft.setImageResource(R.drawable.back);
        barRight.setImageResource(R.drawable.dow);
        barLeft.setOnClickListener(view -> {
            onBackPressed();
        });
        barRight.setOnClickListener(view -> {
            jumpToActivity(DownloadActivity.class);
        });
        toolbar.setBackgroundColor(ResourcesUtil.getInstance().getColor(0, 250));
//        toolbar.inflateMenu(R.menu.toolbar_menu);
        HomeItem homeItem = (HomeItem) getIntent().getSerializableExtra("homeItem");
        //渲染下载按钮颜色
        Drawable drawable=DrawableCompat.wrap(getDrawable(R.drawable.bg_download_btn));
//        DrawableCompat.setTint(drawable,getResources().getColor(R.color.colorAccent));
        download.setBackground(drawable);
        appBarLayout.addOnOffsetChangedListener(listener);//设置滑动监听
//        HomeItem homeItem = new HomeItem("http://www.xixihaha.xin/image/logo_weixin.png", "weixin", "cc");
        Log.i("DetailsActivityaaaa", homeItem.toString());
        Glide.with(ivApk.getContext())
                .load(homeItem.getShowImg())
                .fitCenter()
                .into(ivApk);
        setupViewPager(viewPager);
        Observable.just(homeItem.getShowImg())
                .subscribeOn(AndroidSchedulers.mainThread())//发送者线程
//                .observeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())//接受者线程
                .subscribe(s -> {
                    Bitmap bitmap= GlideUtil.getInstance(this).getBitmap(s);
                     Palette.generateAsync(bitmap, palette -> {
                        Palette.Swatch swatch1 = palette.getVibrantSwatch();////
                        Palette.Swatch swatch2 = palette.getMutedSwatch();
                        Palette.Swatch swatch3 = palette.getDarkVibrantSwatch();
                        Palette.Swatch swatch4 = palette.getDarkMutedSwatch();
                        Palette.Swatch swatch5 = palette.getLightVibrantSwatch();
                        Palette.Swatch swatch6 = palette.getLightMutedSwatch();
                        int [] colors={
                                swatch1.getRgb(),
                                swatch1.hashCode(),
                                swatch1.hashCode()
                        };
                        Drawable drawable1= BitmapUtil.getDraw((int)(UIUtils.getDisplayWidth()),(int)(UIUtils.getDisplayHeight()*0.5),colors);
                         runOnUiThread(() ->prLayout.setBackground(drawable1));
                    });
                });


    }

    AppBarLayout.OnOffsetChangedListener listener = (appBarLayout1, i) -> {
        if (value == i) {
            return;
        }
        value = i;
        int sum = appBarLayout.getTotalScrollRange();//总高度
        int verticalOffset = Math.abs(i);//当前高度
        float f = (float) verticalOffset / sum;
//        toolbar.setNavigationIcon(ResourcesUtil.getInstance().setVector(R.drawable.back, ResourcesUtil.getInstance().getColor(f)));
        barLeft.setImageDrawable(ResourcesUtil.getInstance().setVector(R.drawable.back, ResourcesUtil.getInstance().getColor(f)));
        barRight.setImageDrawable(ResourcesUtil.getInstance().setVector(R.drawable.dow, ResourcesUtil.getInstance().getColor(f)));
        Log.i("appbarappbar", "3数值" + i + "------绝对值" + verticalOffset + "------百分比" + f + "-----总高度" + sum);
        toolbar.setBackgroundColor(ResourcesUtil.getInstance().getColor((int) (255 * f), 250));
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
