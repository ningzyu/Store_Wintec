package com.nzy.zkyt.store_wintec.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by user on 2017/11/28.
 */

public class ScrollBackListView extends ListView implements AbsListView.OnScrollListener {

    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    //是否拉到底
    private boolean isBottom;
    //是否拉到顶
    private boolean isTop;
    //初始Y轴坐标
    private float downY;
    //下滑还是上拉
    private boolean isPull;
    //最后Y轴坐标
    private float lastY;
    //滑动状态
    private int scrollState;
    //回弹速率
    private int speed;

    private Handler handler;

    public ScrollBackListView(Context context) {
        super(context);
        init();
    }

    public ScrollBackListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollBackListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    //初始化
    public void init(){
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        speed = 30;
        handler = new Handler();
        // 禁用下拉到两端发荧光的效果
        setOverScrollMode(OVER_SCROLL_NEVER);

        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //是否到顶
        isTop = firstVisibleItem ==0;
        //是否到底
        isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                handler.removeCallbacksAndMessages(null);
                lastY = ev.getY();
                int offset = (int) ((lastY - downY)/2.5);
                isPull = offset > 0;
                //下拉
                if(isPull){
                    if(isTop && scrollState!= SCROLL_STATE_FLING){
                        offset += paddingTop;
                        setPadding(paddingLeft, offset, paddingRight, paddingBottom);
                        setSelection(0);
                    }
                }
                //上滑
                else{
//                    if(isBottom && scrollState != SCROLL_STATE_FLING){
//                        offset -= paddingBottom;
//                        setPadding(paddingLeft, paddingTop, paddingRight, -offset);
//                        setSelection(getCount() -1);
//                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                //下拉
                if(isPull){
                    int top = getPaddingTop();
                    int duration = 0;

                    //下拉回弹
                    while(top > paddingTop){
                        top -= speed;
                        duration += 10;
                        final int t = top;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(t <= paddingTop){
                                    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
                                }
                                else
                                    setPadding(paddingLeft, t, paddingRight, paddingBottom);
                            }
                        }, duration);
                    }
                }
                //上拉
                else {
//                    int bottom = getPaddingBottom();
//                    int duration = 0;
//                    while (bottom < paddingBottom) {
//                        bottom -= speed;
//                        final int b = bottom;
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(b <= paddingBottom){
//                                    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
//                                }
//                                else
//                                    setPadding(paddingLeft, paddingTop, paddingRight, b);
//                            }
//                        }, duration);
//                    }
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

//    @Override
//    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
//                                   int scrollY, int scrollRangeX, int scrollRangeY,
//                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
//                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
//                isTouchEvent);
//    }
}
