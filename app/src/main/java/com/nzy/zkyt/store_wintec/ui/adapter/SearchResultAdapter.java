package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.SearchResultItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/21.
 */

public class SearchResultAdapter extends BaseAdapter {

    Context context;
    List<SearchResultItem> data = new ArrayList<SearchResultItem>();
    LayoutInflater inflater;

    public SearchResultAdapter(Context context, List<SearchResultItem> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewSize;
        public TextView textViewDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if( convertView==null ){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_search_result_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_head);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.text_title);
            viewHolder.textViewSize = (TextView) convertView.findViewById(R.id.text_size);
            viewHolder.textViewDescription = (TextView) convertView.findViewById(R.id.textdescripe);

            convertView.setTag(viewHolder);

        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewName.setText(data.get(position).appName);
        viewHolder.textViewSize.setText(data.get(position).appSize);
        viewHolder.textViewDescription.setText(data.get(position).description);
        // 加载网络图片
     //   Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context)
                .load(data.get(position).imageUrl)
                .into(viewHolder.imageView)
               ;

        return convertView;
    }
}
