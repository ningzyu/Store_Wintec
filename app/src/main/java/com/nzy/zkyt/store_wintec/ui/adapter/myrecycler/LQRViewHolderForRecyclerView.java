package com.nzy.zkyt.store_wintec.ui.adapter.myrecycler;

/**
 * 作者：宁震宇on 2017/11/3.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */


import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerView通用的ViewHodler
 */
public class LQRViewHolderForRecyclerView extends LQRViewHolder {

    public LQRViewHolderForRecyclerView(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();

        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(LQRViewHolderForRecyclerView.this, null, v, getPosition());
                }
            }
        });

        mConvertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(LQRViewHolderForRecyclerView.this, null, v, getPosition());
                }
                return false;
            }
        });

        mConvertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mOnItemTouchListener != null) {
                    return mOnItemTouchListener.onItemTouch(LQRViewHolderForRecyclerView.this, v, event, getPosition());
                }
                return false;
            }
        });

    }
}

