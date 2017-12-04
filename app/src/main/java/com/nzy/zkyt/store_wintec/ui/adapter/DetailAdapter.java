package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nzy.zkyt.store_wintec.Model.Banners;
import com.nzy.zkyt.store_wintec.Model.HomeItem;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.data.MyBanner;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：宁震宇on 2017/12/2.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //轮播
    public static final int ITEM_HEAD = 0;
    //分类
    public static final int ITEM_TYPES = 1;
    //APP
    public static final int ITEM_APK = 2;
    private Context context;
    private List datas = new ArrayList();
    public DetailAdapter(Context context,List datas) {
        this.context=context;
        this.datas=datas;
    }
    /**
     * 头部
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder
    {
        public Banner banner;
        public HeadViewHolder(View itemView)
        {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.main_banner);
        }
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv;
        public TextView tv;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv_home_item);
            tv= (TextView) itemView.findViewById(R.id.tv_home_item);
        }
    }
    public class TypesViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv;
        public TextView tv;
        public TypesViewHolder(View itemView)
        {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv_home_types);
            tv= (TextView) itemView.findViewById(R.id.tv_home_types);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ITEM_HEAD:
                return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner, parent, false));
            case ITEM_TYPES:
                return new TypesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_types, parent, false));
            case ITEM_APK:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        switch (getItemViewType(i)) {
        if (holder instanceof HeadViewHolder){
            Banners mbanner=(Banners)datas.get(position);
            MyBanner.getInstance(((HeadViewHolder) holder).banner).setBar(mbanner.getImgs(), mbanner.getTitle(), i -> {

            });
        }else if (holder instanceof ItemViewHolder){
            HomeItem homeItem= (HomeItem) datas.get(position);
            Glide.with(context)
                    .load(homeItem.getShowImg())
                    .into(((ItemViewHolder) holder).iv);
            ((ItemViewHolder) holder).tv.setText(homeItem.getName());
        }else if (holder instanceof TypesViewHolder){
            HomeItem homeItem= (HomeItem) datas.get(position);
            Glide.with(context)
                    .load(homeItem.getShowImg())
                    .into(((TypesViewHolder) holder).iv);
            ((TypesViewHolder) holder).tv.setText(homeItem.getName());
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? datas.size() : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ITEM_HEAD;
        }else if (((HomeItem)datas.get(position)).getType().equals("TYPE")){//datas.get(position) instanceof HomeItem&&
            return ITEM_TYPES;
        }else {
            return ITEM_APK;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (getItemViewType(position)){
                    case ITEM_HEAD:
                        return 5;
                    case ITEM_TYPES:
                        return 1;
                    case ITEM_APK:
                        return 5;
                }
                return 1;
            }
        });
    }
}
