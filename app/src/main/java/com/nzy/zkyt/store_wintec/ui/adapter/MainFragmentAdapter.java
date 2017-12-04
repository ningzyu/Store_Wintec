//package com.nzy.zkyt.store_wintec.ui.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.nzy.zkyt.store_wintec.Model.HomeItem;
//import com.nzy.zkyt.store_wintec.R;
//import com.nzy.zkyt.store_wintec.ui.data.MyBanner;
//import com.youth.banner.Banner;
//
//import java.util.List;
//
///**
// * Created by wm on 2016/3/10.
// */
//public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ItemHolder> {
//    private List<HomeItem> data;
//    private Context mContext;
//
//    public MainFragmentAdapter(Context context,List<HomeItem> data) {
//        this.mContext = context;
//        this.data=data;
//    }
//
//    @Override
//    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        switch (viewType) {
//            case 0:
//                View v0 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_banner, viewGroup, false);
//                ItemHolder ml0 = new ItemHolder(v0);
//                return ml0;
//            case 1:
//                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item, viewGroup, false);
//                ItemHolder ml1 = new ItemHolder(v1);
//                return ml1;
//            case 2:
//                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item, viewGroup, false);
//                ItemHolder ml2 = new ItemHolder(v2);
//                return ml2;
//
//        }
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(final ItemHolder vh, int i) {
//        switch (getItemViewType(i)) {
//            case 1:
//                MyBanner.getInstance(vh.mBanner).setBar(data.get(i).getImgs(), null, position -> {
//                });
//                setOnListener(vh, i);
//                break;
//            case 2:
//                vh.tv.setText(data.get(i).getName());
//                Glide.with(mContext)
//                        .load(data.get(i).getImg())//图片地址
//                        .crossFade()
//                        .into(vh.iv);
//                setOnListener(vh, i);
//                break;
//            case 3:
//                vh.tv.setText(data.get(i).getName());
//                Glide.with(mContext)
//                        .load(data.get(i).getImg())//图片地址
//                        .crossFade()
//                        .into(vh.iv);
//                setOnListener(vh, i);
//                setOnListener(vh, i);
//                break;
//        }
//    }
//
//    @Override
//    public void onViewRecycled(ItemHolder itemHolder) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return data == null ? data.size() : data.size() + 1;
//    }
//
//    private void setOnListener(ItemHolder itemHolder, final int position) {
//        switch (position) {
//            case 0:
//                itemHolder.itemView.setOnClickListener(v -> {
////                        final Handler handler = new Handler();
////                        handler.postDelayed(() -> {
////                            Intent intent = new Intent(mContext, TabActivity.class);
////                            intent.putExtra("page_number", 0);
////                            mContext.startActivity(intent);
////                        }, 60);
//
//                });
//                break;
//            case 1:
////                itemHolder.itemView.setOnClickListener(v -> {
////                    final Handler handler = new Handler();
////                    handler.postDelayed(() -> {
////                        Intent intent = new Intent(mContext, RecentActivity.class);
////                        mContext.startActivity(intent);
////                    }, 60);
////                });
//
//
//                break;
//            case 2:
////                itemHolder.itemView.setOnClickListener(v -> {
////                    final Handler handler = new Handler();
////                    handler.postDelayed(() -> {
////                        Intent intent = new Intent(mContext, DownActivity.class);
////                        mContext.startActivity(intent);
////                    }, 60);
////
////                });
//                break;
//            case 3:
////                itemHolder.itemView.setOnClickListener(v -> {
////                    Intent intent = new Intent(mContext, TabActivity.class);
////                    intent.putExtra("page_number", 1);
////                    mContext.startActivity(intent);
////
////                });
//        }
//
//    }
//
//    private void setSectionListener(final ItemHolder itemHolder, int position) {
////        itemHolder.sectionMenu.setOnClickListener(v -> {
////                Intent intent = new Intent(mContext, PlaylistManagerActivity.class);
////                mContext.startActivity(intent);
////        });
//    }
//
//
//    @Override
//    public int getItemViewType(int position) {
//        switch (data.get(position).getType()) {
//            case 1:
//                return 1;
//            case 2:
//                return 2;
//            case 3:
//                return 3;
//            default:
//                return 4;
//        }
//
////        if (itemResults.get(position) instanceof MainFragmentItem)
//    }
//
//
//    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        //        protected TextView itemtitle, title, count, songcount, sectionItem;
////        protected ImageView menu, sectionImg, sectionMenu;
////        @BindView(R.id.iv_home_item)
////        ImageView iv;
////        @BindView(R.id.tv_home_item)
////        TextView tv;
////        @BindView(R.id.main_banner)
////        Banners mBanner;
//        protected ImageView iv;
//        protected TextView tv;
//        protected Banner mBanner;
//        public ItemHolder(View view) {
//            super(view);
////            ButterKnife.bind(this, view);
//            iv= (ImageView) view.findViewById(R.id.iv_home_item);
//            tv= (TextView) view.findViewById(R.id.tv_home_item);
//            mBanner= (Banner) view.findViewById(R.id.main_banner);
//            view.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
////            ObjectAnimator anim = null;
////            anim = ObjectAnimator.ofFloat(sectionImg, "rotation", 90, 0);
////            anim.setDuration(100);
////            anim.setRepeatCount(0);
////            anim.setInterpolator(new LinearInterpolator());
////            switch (getItemViewType()) {
////                case 2:
////
////                    break;
////
////                case 3:
////
////                    break;
////            }
//        }
//
//
//    }
//}
