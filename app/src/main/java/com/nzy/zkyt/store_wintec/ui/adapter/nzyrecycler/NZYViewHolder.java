package com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * 作者：宁震宇on 2017/12/6.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class NZYViewHolder extends RecyclerView.ViewHolder {
    protected Context mContext;
    protected View mConvertView;
    protected SparseArray<View> mViews;
    protected int mMyPosition;//LQRViewHolderForAbsListView专用（另一个自带getPosition方法）

    protected NZYRecyclerAdapter.OnItemClickListener mOnItemClickListener;
    protected NZYRecyclerAdapter.OnItemLongClickListener mOnItemLongClickListener;
    protected NZYRecyclerAdapter.OnItemTouchListener mOnItemTouchListener;

    public NZYViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 根据id得到布局中的View(使用SparseArray保管，提高效率)
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    /**
     * 得到当前item对应的View
     */
    public View getConvertView() {
        return mConvertView;
    }

    public int getMyPosition() {
        return mMyPosition;
    }
    public void setMyPosition(int myPosition) {
        mMyPosition = myPosition;
    }

    public NZYRecyclerAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(NZYRecyclerAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public NZYRecyclerAdapter.OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(NZYRecyclerAdapter.OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public NZYRecyclerAdapter.OnItemTouchListener getOnItemTouchListener() {
        return mOnItemTouchListener;
    }

    public void setOnItemTouchListener(NZYRecyclerAdapter.OnItemTouchListener onItemTouchListener) {
        mOnItemTouchListener = onItemTouchListener;
    }

    /*================== 一切有可能的操作控件的方法 begin ==================*/

    /**
     * 设置TextView文字，并返回this
     */
    public NZYViewHolder setText(int viewId, String text) {
        Log.i("Aaaaaaaaaaaaaaaaaaaaaaaaaa",viewId+"-----------"+text);
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置TextView的文字颜色，并返回this
     */
    public NZYViewHolder setTextColor(int viewId, int colorId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public NZYViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public NZYViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public NZYViewHolder setImageFileResource(int viewId, String path) {
        ImageView iv = getView(viewId);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
        return this;
    }
    public NZYViewHolder setImagePicasso(int viewId, String url){
        Picasso.with(mContext).load(url).into((ImageView) getView(viewId));
        return this;
    }
    public NZYViewHolder setImageGlide(int viewId, String url){
        Glide.with(mContext).load(url).into((ImageView)getView(viewId));
        return this;
    }



    /**
     * 设置背景颜色，并返回this
     */
    public NZYViewHolder setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }


    /**
     * 设置背景资源，并返回this
     */
    public NZYViewHolder setBackgrounResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置显隐，并返回this
     */
    public NZYViewHolder setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置是否可用，并返回this
     */
    public NZYViewHolder setEnabled(int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 设置是否可获取焦点，并返回this
     */
    public NZYViewHolder setFocusable(int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }

    public NZYViewHolder setOnClick(int viewId,View.OnClickListener click){
        View view = getView(viewId);
        view.setOnClickListener(click);
        return this;
    }
    /*================== 一切有可能操作控件的方法 end ==================*/

}

