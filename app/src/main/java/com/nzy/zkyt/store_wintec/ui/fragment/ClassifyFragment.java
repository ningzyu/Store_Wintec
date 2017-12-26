package com.nzy.zkyt.store_wintec.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.nzy.zkyt.store_wintec.model.ClassifyItem;
import com.nzy.zkyt.store_wintec.ui.activity.MainActivity;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.activity.QueryActivity;
import com.nzy.zkyt.store_wintec.ui.adapter.ClassifyAdapter;
import com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler.NZYRecyclerAdapter;
import com.nzy.zkyt.store_wintec.ui.base.BaseFragment;
import com.nzy.zkyt.store_wintec.ui.presenter.FgClassifyPresenter;
import com.nzy.zkyt.store_wintec.ui.view.FgClassifyView;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment<FgClassifyView, FgClassifyPresenter> implements FgClassifyView, AdapterView.OnItemClickListener, View.OnClickListener {

    ImageView searchImageView;      //搜索框
    ListView classifyListView;      //分类列表

    private ClassifyAdapter classifyAdapter;
    private Context context;
    private List<ClassifyItem> classifyItemList;

    @Override
    protected FgClassifyPresenter createPresenter() {
        return new FgClassifyPresenter((MainActivity)getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        classifyListView = (ListView) rootView.findViewById(R.id.listview_classify);
        searchImageView = (ImageView) rootView.findViewById(R.id.image_classify_search);
    }

    @Override
    public void initData() {
        super.initData();
        classifyItemList = new ArrayList<ClassifyItem>();
        //模拟数据
        for(int i=0; i<8; i++){
            ClassifyItem item = new ClassifyItem();
            item.ClassifyName="实用工具";
            item.ImageUrl="http://avatar.csdn.net/D/C/C/1_caozhy.jpg";
            classifyItemList.add(item);
        }
        classifyAdapter = new ClassifyAdapter(getContext(), classifyItemList);
        classifyListView.setAdapter(classifyAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        classifyListView.setOnItemClickListener(this);
        searchImageView.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_classify_search:
                Intent intent = new Intent(getContext(), QueryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
