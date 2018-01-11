package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.ApkInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by hasee on 2018/1/11.
 */

public class LocalInstallApkAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private List<ApkInfo> list = new ArrayList<ApkInfo>();
    private LayoutInflater layoutInflater;

    public LocalInstallApkAdapter(Context context, List<ApkInfo> list){
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

    private class ViewHolder{
        public ImageView icon;
        public TextView name;
        public TextView size;
        public TextView open;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.layout_local_install_apk, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.image_head);
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_app_name);
            viewHolder.size = (TextView) convertView.findViewById(R.id.text_app_size);
            viewHolder.open = (TextView) convertView.findViewById(R.id.open_app);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.open.setTag(position);
        viewHolder.icon.setBackground(list.get(position).getApkIcon());
        viewHolder.name.setText(list.get(position).getApkName());
        viewHolder.size.setText(list.get(position).getApkSize());

        viewHolder.open.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(list.get(position).getApkPackageName());
        context.startActivity(intent);

    }
}
