package com.nzy.zkyt.store_wintec.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.SettingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/12/28.
 */

public class SettingAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    Context context;
    List<SettingItem> list = new ArrayList<SettingItem>();
    LayoutInflater inflater;

    public SettingAdapter(Context context, List<SettingItem> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        public TextView textViewTitle;
        public TextView textViewBrief;
        public Switch settingSwitch;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_setting_item, null);
            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.setting_title);
            viewHolder.textViewBrief = (TextView) convertView.findViewById(R.id.setting_brief);
            viewHolder.settingSwitch = (Switch) convertView.findViewById(R.id.setting_switch);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewTitle.setText(list.get(position).title);
        viewHolder.textViewBrief.setText(list.get(position).brief);
        viewHolder.settingSwitch.setChecked(list.get(position).isOn);

        viewHolder.settingSwitch.setOnCheckedChangeListener(this);

        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
