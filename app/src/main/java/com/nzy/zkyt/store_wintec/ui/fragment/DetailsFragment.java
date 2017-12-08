package com.nzy.zkyt.store_wintec.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nzy.zkyt.store_wintec.R;

/**
 * 作者：宁震宇on 2017/12/8.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class DetailsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        return view;
    }
}
