package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;

import java.util.List;

/**
 * Created by user on 2017/12/18.
 * 搜索页面 历史记录列表适配器
 */

public class HistoryAdapter extends BaseAdapter {

    Context context;
    List<String> list;
    LayoutInflater layoutInflater;

    public HistoryAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
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
        TextView textView;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.layout_history, null);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.text_history);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.image_history_close);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position));

        return convertView;
    }
}
