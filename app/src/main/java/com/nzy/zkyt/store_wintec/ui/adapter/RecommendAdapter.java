package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.activity.QueryActivity;
import com.nzy.zkyt.store_wintec.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/18.
 * 搜索界面，推荐列表适配器
 */

public class RecommendAdapter extends BaseAdapter implements View.OnClickListener {

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

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    class ViewHolder{
        LinearLayout linearLayout;

    }
    List<TextView> textViewList;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        textViewList = new ArrayList<TextView>();
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.layout_search_label, null);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearlayout_label);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(UIUtils.getDisplayWidth()/30, 0, UIUtils.getDisplayWidth()/30, 0);

            //动态添加标签
            for(String label: list.get(position)){
                TextView textView = new TextView(context);
                textView.setLayoutParams(params);
                textView.setBackground(context.getResources().getDrawable(R.drawable.bg_label));
                textView.setSingleLine(true);

                textViewList.add(textView);
            }
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        for(int i=0;i<textViewList.size();i++){
            TextView textView = textViewList.get(i);
            textView.setText(list.get(position).get(i));
            textView.setTag(list.get(position).get(i));
            textView.setOnClickListener(this);

            holder.linearLayout.addView(textView);
        }

        return convertView;
    }

    Handler handler = new Handler();

    @Override
    public void onClick(View v) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ((QueryActivity)context).getEditTextSearch().setText((String) v.getTag());
            }
        });
    }
}
