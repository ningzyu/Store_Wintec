package com.nzy.zkyt.store_wintec.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.ui.adapter.HistoryAdapter;
import com.nzy.zkyt.store_wintec.ui.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QueryActivity extends AppCompatActivity {

    @BindView(R.id.btn_return)
    ImageView imageReturn;      //返回按钮

    @BindView(R.id.search_et_input)
    EditText editTextSearch;    //搜索输入框

    @BindView(R.id.image_refresh)
    ImageView imageRefresh;     //刷新图标

    @BindView(R.id.text_refresh)
    TextView textRefresh;       //刷新

    @BindView(R.id.listview_recommend)
    ListView listViewRecommend; //推荐列表

    @BindView(R.id.listview_history)
    ListView listViewHistory;   //历史列表

    Context context;
    RecommendAdapter recommendAdapter;
    HistoryAdapter historyAdapter;
    List<List<String>> recommendList;
    List<String> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        initData();
    }

    public void initData(){
        context = this;
        // 初始化推荐的列表
        recommendList = new ArrayList<List<String>>();
        for(int i=0; i<3; i++){
            List<String> lineList = new ArrayList<String>();
            lineList.add("余光中");
            lineList.add("Bilibili");
            lineList.add("元旦放假");
            recommendList.add(lineList);
        }
        recommendAdapter = new RecommendAdapter(context, recommendList);
        listViewRecommend.setAdapter(recommendAdapter);
        // 初始化历史列表
        historyList = new ArrayList<String>();
        historyList.add("第一条历史记录");
        historyList.add("第二条历史记录");
        historyList.add("第三条历史记录");
    }
}
