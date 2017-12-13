package com.nzy.zkyt.store_wintec.ui.fragment;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.MainActivity;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgHomePresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgHomeView;
import com.nzy.zkyt.store_wintec.util.UIUtils;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment<FgHomeView, FgHomePresenter> implements FgHomeView {
    @BindView(R.id.rv_home)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_search)
    LinearLayout search;
    @BindView(R.id.search_et_input)
    EditText et_srarch;
    @BindView(R.id.fake_status_bar)
    View bar;
    @BindView(R.id.line)
    View line;

    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;
    @Override
    protected FgHomePresenter createPresenter() {
        return new FgHomePresenter((MainActivity) getActivity());
    }

    @Override
    public void initView(View rootView) {
        laParams = search.getLayoutParams();
        currentWidth= (float) (UIUtils.getDisplayWidth() * 0.3);
        laParams.width = (int)currentWidth;
        search.setLayoutParams(laParams);
        setSearch(0);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter.setMessage();

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public RecyclerView getRv() {
        return rv;
    }

    @Override
    public LinearLayout getSearch() {
        return search;
    }
    private float currentWidth;
    private int speed = 55;
    private ViewGroup.LayoutParams laParams;//
    private Float addwith=new Float((UIUtils.getDisplayWidth() * 0.6) / speed);
    @Override
    public void setSearch(float Alpha) {
        if (Alpha == 0) {//当前位置为顶部
            et_srarch.setHint("搜索");
            if (isChange) {
                subscribe(emitter -> {
                    for (int i = 0; i < speed; i++) {
                        emitter.onNext(0-addwith);
                        Thread.sleep(5);
                    }
                    emitter.onComplete();
                });
                isChange = false;
            }

        } else if ((int) Alpha == 1) {//当前位置为banner下方
            et_srarch.setHint("搜索应用的关键字或分类");
            if (!isChange) {
                subscribe(emitter -> {
                    for (int i = 0; i < speed; i++) {
                        emitter.onNext(addwith);
                        Thread.sleep(5);
                    }
                    emitter.onComplete();
                });
                isChange = true;
            }
        }
        //搜索框初始透明度
        float initalpha = 130;
        float alpha = (255 * Alpha);
        float alphaSearch = ((255 - initalpha) * Alpha) + initalpha;
        toolbar.setBackgroundColor(Color.argb((int) alpha, 250, 250, 250));
        search.getBackground().setAlpha((int) alphaSearch);
        bar.getBackground().setAlpha((int) alpha);
        line.getBackground().setAlpha((int) alpha);
    }
    boolean isChange = false;

    /**
     * 为控件添加尺寸渐变动画
     */

    //当前宽度
    public void subscribe(ObservableOnSubscribe<Float> bservable) {
        Observable.create(bservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);


    }
    Consumer<Float> consumer= new Consumer<Float>() {
        @Override
        public void accept(@NonNull Float f) throws Exception {
            currentWidth += f.floatValue();
            Log.i("widthwwww","当前宽度====="+currentWidth+"");
            laParams.width = (int) currentWidth;
            search.setLayoutParams(laParams);
        }
    };































    //                int duration = 0;
//                for(int i=0;i<speed;i++) {
//                    duration+=10;
//                    handler.postDelayed(() -> {
//                        currentWidth-=addwith;
//                        Log.i("widthwwww","当前宽度====="+currentWidth+"");
//                        laParams.width = (int) currentWidth;
//                        search.setLayoutParams(laParams);
//
//                    }, duration);
//                }
}
