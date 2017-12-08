package com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * 作者：宁震宇on 2017/12/6.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class NZYRecyclerHolder  extends NZYViewHolder {

    public NZYRecyclerHolder(Context context, View itemView,int viewType) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();

        mConvertView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(NZYRecyclerHolder.this, v, getLayoutPosition(),viewType);
            }
        });

        mConvertView.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                return mOnItemLongClickListener.onItemLongClick(NZYRecyclerHolder.this, null, v, getLayoutPosition());
            }
            return false;
        });

        mConvertView.setOnTouchListener((v, event) -> {
            if (mOnItemTouchListener != null) {
                return mOnItemTouchListener.onItemTouch(NZYRecyclerHolder.this, v, event, getLayoutPosition());
            }
            return false;
        });

    }
}

