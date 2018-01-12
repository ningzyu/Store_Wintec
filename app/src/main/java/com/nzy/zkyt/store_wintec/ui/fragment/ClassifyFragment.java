package com.nzy.zkyt.store_wintec.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClassifyFragment extends BaseFragment<FgClassifyView, FgClassifyPresenter> implements FgClassifyView, AdapterView.OnItemClickListener, View.OnClickListener {

    ImageView searchImageView;      //搜索框
    ListView classifyListView;      //分类列表

    private Handler handler = new Handler();
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
        getClassifyFromServer();    //从服务器获取分类列表
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

    /**
     * 从服务器获取分类
     */
    public void getClassifyFromServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.12.69:8080/Wintec/AppService?methodCode=3").build();
                    Response response = null;
                    response = okHttpClient.newCall(request).execute();
                    if(response.isSuccessful()){
                        String result = response.body().string();
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        if(jsonObject.getString("code").equals("1")){
                            JSONArray array = JSONArray.fromObject(jsonObject.getString("values"));
                            classifyItemList = new ArrayList<ClassifyItem>();
                            for(int i=0;i<array.size();i++){
                                ClassifyItem item = new ClassifyItem();
                                item.ClassifyName = array.getJSONObject(i).getString("name");
                                item.ImageUrl = array.getJSONObject(i).getString("imag");
                                classifyItemList.add(item);
                            }
                            //通知listview绑定数据
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    classifyAdapter = new ClassifyAdapter(getContext(), classifyItemList);
                                    classifyListView.setAdapter(classifyAdapter);
                                }
                            });

                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
