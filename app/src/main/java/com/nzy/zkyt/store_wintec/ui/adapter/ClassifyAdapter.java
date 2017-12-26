package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.ClassifyItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangtianyu on 2017/12/26.
 * 分类列表适配器
 */

public class ClassifyAdapter extends BaseAdapter {

    private Context context;
    private List<ClassifyItem> list = new ArrayList<ClassifyItem>();
    private LayoutInflater layoutInflater;

    public ClassifyAdapter(Context context, List<ClassifyItem> list){
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView imageViewHead;
        TextView  textView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.layout_classify_item, null);
            viewHolder.imageViewHead = (ImageView) convertView.findViewById(R.id.image_classify_head);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_classify_name);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position).ClassifyName);
        Picasso.with(context).load(list.get(position).ImageUrl).into(viewHolder.imageViewHead);

        return convertView;
    }


}
