package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/18.
 * 搜索界面，推荐列表适配器
 */

public class RecommendAdapter extends BaseAdapter {

    Context context;
    List<List<String>> list;
    LayoutInflater layoutInflater;

    public RecommendAdapter(Context context, List<List<String>> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
        LinearLayout linearLayout;
        List<TextView> textViewList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.layout_search_label, null);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearlayout_label);
            holder.textViewList = new ArrayList<TextView>();
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //动态添加标签
            for(String label: list.get(position)){
                TextView textView = new TextView(context);
                textView.setLayoutParams(params);
        //        textView.setText(label);
        //        holder.linearLayout.addView(textView);
                holder.textViewList.add(textView);
            }
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        for(int i=0;i<holder.textViewList.size();i++){
            TextView textView = holder.textViewList.get(i);
            textView.setText(list.get(position).get(i));
            holder.linearLayout.addView(textView);
        }

        return convertView;
    }
}
