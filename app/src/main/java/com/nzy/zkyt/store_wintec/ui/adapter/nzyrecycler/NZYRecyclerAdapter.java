package com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：宁震宇on 2017/12/6.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public abstract class NZYRecyclerAdapter<T> extends RecyclerView.Adapter<NZYRecyclerHolder> {
    protected Context mContext;//上下文
    protected List<T> mData;//数据源
    private int mDefaultLayoutId = 0;//布局ID


    public NZYRecyclerAdapter(Context context,List datas) {
        this.mContext=context;
        this.mData=datas;
    }
    @Override
    public NZYRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NZYRecyclerHolder holder = new NZYRecyclerHolder(mContext, View.inflate(mContext, viewType, null),viewType);
        holder.setOnItemClickListener(mOnItemClickListener);
        holder.setOnItemLongClickListener(mOnItemLongClickListener);
        holder.setOnItemTouchListener(mOnItemTouchListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(NZYRecyclerHolder holder, int position) {
        convert(holder, mData.get(position), position,getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? mData.size() : mData.size();
    }

    public abstract void convert(NZYRecyclerHolder helper, T item, int position,int type);
    public abstract int getSpan(int type,T item);
    public abstract int getType(int position,T item);
    /**
     * 当需要使用多itemType时，请重写该方法，返回值就是对应类型的布局id
     */
    @Override
    public int getItemViewType(int position) {
        return getType(position,mData.get(position));
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return getSpan(getItemViewType(position),mData.get(position));
            }
        });
    }

     /*================== 数据操作相关 begin ==================*/

    /**
     * 获取指定索引位置的数据模型
     */
    public T getItem(int position) {
        return mData.get(position);
    }
    /**
     * 获取数据集合
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 数据局部刷新
     */
    public final void notifyItemRangeInsertedWrapper(int positionStart, int itemCount) {
        notifyItemRangeInserted(positionStart, itemCount);
    }




    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据列表）
     */
    public void setData(List<T> data) {
        if (data != null) {
            mData = data;
        } else {
            mData.clear();
        }
        notifyDataSetChangedWrapper();
    }
    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合）
     */
    public void addNewData(List<T> data) {
        if (data != null) {
            mData.addAll(0, data);
            notifyItemRangeInsertedWrapper(0, data.size());
        }
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        mData.clear();
        notifyDataSetChangedWrapper();
    }
    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     */
    public void addMoreData(List<T> data) {
        if (data != null) {
            mData.addAll(mData.size(), data);
            notifyItemRangeInsertedWrapper(mData.size(), data.size());
        }
    }

    /**
     * 数据全局刷新
     */
    public final void notifyDataSetChangedWrapper() {
        notifyDataSetChanged();
    }
    /**
     * 数据移除刷新
     */
    public final void notifyItemRemoveWrapper(int position) {
        notifyItemRemoved(position);
    }

    /**
     * 删除指定索引数据条目
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoveWrapper(position);
    }

    /**
     * 删除指定数据条目
     */
    public void removeItem(T model) {
        removeItem(mData.indexOf(model));
    }

    /**
     * 数据添加刷新
     */
    public final void notifyItemInsertedWrapper(int position) {
        notifyItemInserted(position);
    }

    /**
     * 在指定位置添加数据条目
     */
    public void addItem(int position, T model) {
        mData.add(position, model);
        notifyItemInsertedWrapper(position);
    }

    /**
     * 在集合头部添加数据条目
     */
    public void addFirstItem(T model) {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     */
    public void addLastItem(T model) {
        addItem(mData.size(), model);
    }

    /**
     * 数据变化刷新
     */
    public final void notifyItemChangedWrapper(int position) {
        notifyItemChanged(position);
    }

    /**
     * 替换指定索引的数据条目
     */
    public void setItem(int position, T newModel) {
        mData.set(position, newModel);
        notifyItemChangedWrapper(position);
    }

    /**
     * 替换指定数据条目
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mData.indexOf(oldModel), newModel);
    }

    /**
     * 数据移动刷新
     */
    public final void notifyItemMoveWrapper(int fromPosition, int toPosition) {
//        if (mHeaderAndFooterAdapter == null) {
        notifyItemMoved(fromPosition, toPosition);
//        } else {
//            mHeaderAndFooterAdapter.notifyItemMoved(mHeaderAndFooterAdapter.getHeadersCount() + fromPosition, mHeaderAndFooterAdapter.getHeadersCount() + toPosition);
//        }
    }

    /**
     * 移动数据条目的位置
     */
    public void moveItem(int fromPosition, int toPosition) {
        notifyItemChangedWrapper(fromPosition);
        notifyItemChangedWrapper(toPosition);
        //要先执行上面的notifyItemChanged，然后再执行下面的moveItem事件
        mData.add(toPosition, mData.remove(fromPosition));
        notifyItemMoveWrapper(fromPosition, toPosition);
    }

    /**
     * 获取第一个数据模型
     */
    public T getFirstItem() {
        return getItemCount() > 0 ? getItem(0) : null;
    }



    /**
     *  事件接口
     */
    public interface OnItemClickListener {
        void onItemClick(NZYViewHolder helper, View itemView, int position,int viewType);
    }
    public interface OnItemLongClickListener {
        boolean onItemLongClick(NZYViewHolder helper, ViewGroup parent, View itemView, int position);
    }
    public interface OnItemTouchListener {
        boolean onItemTouch(NZYViewHolder helper, View childView, MotionEvent event, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemTouchListener mOnItemTouchListener;

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public OnItemTouchListener getOnItemTouchListener() {
        return mOnItemTouchListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        mOnItemTouchListener = onItemTouchListener;
    }


}
