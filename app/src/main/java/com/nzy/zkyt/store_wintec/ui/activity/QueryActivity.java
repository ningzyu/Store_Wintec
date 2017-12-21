package com.nzy.zkyt.store_wintec.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.db.DatabaseHelper;
import com.nzy.zkyt.store_wintec.db.DatabaseManager;
import com.nzy.zkyt.store_wintec.model.SearchHistoryItem;
import com.nzy.zkyt.store_wintec.model.SearchResultItem;
import com.nzy.zkyt.store_wintec.ui.adapter.HistoryAdapter;
import com.nzy.zkyt.store_wintec.ui.adapter.RecommendAdapter;
import com.nzy.zkyt.store_wintec.ui.adapter.SearchResultAdapter;
import com.nzy.zkyt.store_wintec.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ImageView imageReturn;      //返回按钮
    EditText editTextSearch;    //搜索输入框
    ImageView imageSearch;      //搜索按钮

    ImageView imageRefresh;     //刷新图标
    ImageView imageLoading;     //等待图标
    TextView textRefresh;       //刷新

    ListView listViewRecommend; //推荐列表
    ListView listViewHistory;   //历史列表
    ListView listViewResult;    //搜索结果列表

    LinearLayout linearlayoutRecommend; //推荐布局
    FrameLayout frameLayoutResult;      //搜索结果布局
    RelativeLayout relativeLayoutLoading;   //加载布局

    Context context;
    private Animation animRefresh, animLoadingRotate, animLoadingScale ,animLoadingScaleEnd;
    private RecommendAdapter recommendAdapter;
    private HistoryAdapter historyAdapter;
    private SearchResultAdapter searchResultAdapter;
    private List<String> labelList;                 //标签列表
    private List<List<String>> recommendList;       //推荐列表
    private List<SearchHistoryItem> historyList;    //历史记录列表
    private List<SearchResultItem> resultList;      //搜索结果列表
    private boolean isShowResult = false;          //是否显示搜索结果

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Window window = getWindow();
        //设置沉浸
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        }
        initView();
        initVisible(false);
        initData();
        initListener();
    }

    public void initView(){
        imageReturn = (ImageView) findViewById(R.id.btn_return);
        editTextSearch = (EditText) findViewById(R.id.edit_search);
        imageSearch = (ImageView) findViewById(R.id.image_search);
        imageRefresh = (ImageView) findViewById(R.id.image_refresh);
        textRefresh = (TextView) findViewById(R.id.text_refresh);
        listViewRecommend = (ListView) findViewById(R.id.listview_recommend);
        listViewHistory = (ListView) findViewById(R.id.listview_history);
        listViewResult = (ListView) findViewById(R.id.listview_result);
        linearlayoutRecommend = (LinearLayout) findViewById(R.id.linearlayout_recommend);
        frameLayoutResult = (FrameLayout) findViewById(R.id.framelayout_result);
        imageLoading = (ImageView) findViewById(R.id.icon_loading);
        relativeLayoutLoading = (RelativeLayout) findViewById(R.id.rl_loading);
    }

    /**
     * 设置view可见，用于搜索界面与搜索结果界面的切换
     * @param isShowResult
     */
    public void initVisible(boolean isShowResult){
        if(!isShowResult){
            linearlayoutRecommend.setVisibility(View.VISIBLE);
            listViewHistory.setVisibility(View.VISIBLE);
            listViewRecommend.setVisibility(View.VISIBLE);
            listViewResult.setVisibility(View.GONE);
            frameLayoutResult.setVisibility(View.GONE);
        }else{
            listViewResult.setVisibility(View.VISIBLE);
            linearlayoutRecommend.setVisibility(View.GONE);
            listViewRecommend.setVisibility(View.GONE);
            listViewHistory.setVisibility(View.GONE);
            frameLayoutResult.setVisibility(View.VISIBLE);
        }
    }

    public void initData(){
        context = this;
        animRefresh = AnimationUtils.loadAnimation(context, R.anim.refresh_rotate);
        animLoadingRotate = AnimationUtils.loadAnimation(context, R.anim.loading_rotate);
        animLoadingScale = AnimationUtils.loadAnimation(context, R.anim.loading_scale);
        animLoadingScaleEnd = AnimationUtils.loadAnimation(context, R.anim.loading_scale_end);

        listViewRecommend.setFocusable(false);
        listViewRecommend.setItemsCanFocus(true);
        //模拟数据
        simulatedLabelData();

        recommendList = new ArrayList<List<String>>();
        // 初始化推荐的列表
        initRecomendLabel();

        recommendAdapter = new RecommendAdapter(context, recommendList);
        listViewRecommend.setAdapter(recommendAdapter);
        // 初始化历史列表
        historyList = new ArrayList<SearchHistoryItem>();
        historyList = DatabaseManager.getmInstance(context).getSearchHistory();
        historyAdapter = new HistoryAdapter(context, historyList);

        listViewHistory.setAdapter(historyAdapter);
        listViewHistory.setOnItemClickListener(this);
        resultList = new ArrayList<SearchResultItem>();
    }

    public void initListener(){
        imageReturn.setOnClickListener(this);
        imageSearch.setOnClickListener(this);
        imageRefresh.setOnClickListener(this);
        textRefresh.setOnClickListener(this);
    }

    /**
     * 按键监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //后退按钮
            case R.id.btn_return:
                finish();
                break;

            //搜索按钮
            case R.id.image_search:
                String keyWord = editTextSearch.getText().toString().trim();
                searchByKeyword(keyWord);
                break;

            //刷新按钮
            case R.id.image_refresh:
                refreshLabel();
                break;

            //刷新按钮
            case R.id.text_refresh:
                refreshLabel();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        searchByHistory(historyList.get(position).keyword);
    }

    /**
     * 显示等待框
     */
    public void showLoadingWaitingDialog(){
        imageLoading.setVisibility(View.VISIBLE);
        relativeLayoutLoading.setVisibility(View.VISIBLE);
        imageLoading.setAnimation(animLoadingRotate);
        relativeLayoutLoading.setAnimation(animLoadingScale);
        relativeLayoutLoading.startAnimation(animLoadingScale);
        imageLoading.startAnimation(animLoadingRotate);
    }

    /**
     * 停止等待框
     */
    public void stopLoadingWaitingDialog(){

        relativeLayoutLoading.clearAnimation();
        animLoadingScaleEnd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageLoading.clearAnimation();
                relativeLayoutLoading.clearAnimation();
                imageLoading.setVisibility(View.GONE);
                relativeLayoutLoading.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        relativeLayoutLoading.setAnimation(animLoadingScaleEnd);
        relativeLayoutLoading.startAnimation(animLoadingScaleEnd);

    }

    /**
     * 刷新标签
     */
    public void refreshLabel(){
        imageRefresh.startAnimation(animRefresh);
        simulatedLabelData();
        recommendList.clear();
        recommendAdapter.notifyDataSetChanged();
        listViewRecommend.setAdapter(recommendAdapter);
        initRecomendLabel();
        listViewRecommend.setAdapter(recommendAdapter);
    }

    /**
     * 模拟标签数据
     */
    public void simulatedLabelData(){
        if(labelList!=null)
            labelList.clear();
        labelList = new ArrayList<String>();
        String[] data = {"火狐浏览器","中科英泰","android studio","可乐","数据结构与算法分析","西红柿鸡蛋"};
        int count = (int)(6+Math.random()*(10-6+1));
        for(int i=0;i<count;i++){
            int num = (int)(0+Math.random()*(5-0+1));
            labelList.add(data[num]);
        }

    }

    /**
     * 计算一行每行能摆下多少标签
     */
    public void initRecomendLabel(){
        int line = 0;
        int index = 0;          //当前下标
        int lineWidth = 0;      //一行的宽度

        int ScreenWidth = UIUtils.getDisplayWidth() * 9/10;    //0.9屏幕宽度
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        List<String> tempList = new ArrayList<String>();    //临时储存列表
        for(int i=index; i<labelList.size(); i++){
            textView.setText(labelList.get(i));
            textView.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lineWidth += textView.getMeasuredWidth()+ ScreenWidth/15;
            if(lineWidth<ScreenWidth ){      // 当前宽度未超过屏幕宽度
                tempList.add(labelList.get(i));

                if(i==labelList.size()-1){
                    recommendList.add(new ArrayList<String>());
                    recommendList.get(line).addAll(tempList);
                    lineWidth=0;
                    index = i;
                    line++;
                    tempList.clear();
                }

            }  else{                         // 当前宽度超过屏幕宽度
                recommendList.add(new ArrayList<String>());
                recommendList.get(line).addAll(tempList);
                lineWidth = 0;
                index = i;
                line++;
                tempList.clear();
                tempList.add(labelList.get(i));
                lineWidth += textView.getMeasuredWidth()+ ScreenWidth/15;
                if(i==labelList.size()-1){
                    recommendList.add(new ArrayList<String>());
                    recommendList.get(line).addAll(tempList);
                }
            }



        }
    }

    /**
     * 删除搜索记录
     */
    public void deleteSearchHistory(int position){
        DatabaseManager.getmInstance(context).deleteSearchHistory(historyList.get(position).id);
        historyList.remove(position);
        historyAdapter.notifyDataSetChanged();
        listViewHistory.setAdapter(historyAdapter);
    }

    /**
     * 通过关键词搜索
     * @param keyword
     */
    public void searchByKeyword(String keyword){
        if(TextUtils.isEmpty(editTextSearch.getText())){
            Toast.makeText(context, "搜索信息不得为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(isShowResult){
            resultList.clear();
            searchResultAdapter.notifyDataSetChanged();
            listViewResult.setAdapter(searchResultAdapter);
        }
        isShowResult = true;
        //存入本地数据库
        DatabaseManager.getmInstance(context).insertSearchHistory(keyword);
        initVisible(true);
        //显示加载等待动画
        showLoadingWaitingDialog();
        //获取查询结果
        getQueryResult();
    }

    Handler handler = new Handler();

    /**
     * 获取查询结果
     */
    public void getQueryResult(){

        new Thread(new Runnable() {
            @Override
            public void run() {


                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        int count = (int)(6+Math.random()*(9-6+1));
                        count=10;
                        for(int i=0; i<count; i++){
                            SearchResultItem item = new SearchResultItem();
                            item.imageUrl = "http://avatar.csdn.net/F/F/9/3_javaforandroid.jpg";
                            item.appName = "微信";
                            item.appSize = "36.6M";
                            item.description = "大型即时通信交友APP";
                            resultList.add(item);
                        }

                        searchResultAdapter = new SearchResultAdapter(context, resultList);
                        stopLoadingWaitingDialog();
                        listViewResult.setAdapter(searchResultAdapter);

                    }
                }, 2000);
            }
        }).start();

    }

    /**
     * 通过历史记录搜索
     * @param keyword
     */
    public void searchByHistory(String keyword){
        //在搜索栏中显示
        editTextSearch.setText(keyword);
        isShowResult =true;
        initVisible(true);
        showLoadingWaitingDialog();
        getQueryResult();
    }

    /**
     * 返回搜索框
     * @return
     */
    public EditText getEditTextSearch(){
        return editTextSearch;
    }
}
