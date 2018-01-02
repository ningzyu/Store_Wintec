package com.nzy.zkyt.store_wintec.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.SettingItem;
import com.nzy.zkyt.store_wintec.ui.adapter.SettingAdapter;
import com.nzy.zkyt.store_wintec.ui.adapter.nzyrecycler.NZYRecyclerAdapter;
import com.nzy.zkyt.store_wintec.util.CacheUtils;
import com.nzy.zkyt.store_wintec.util.LocaleUtils;
import com.nzy.zkyt.store_wintec.util.SPUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listViewSetting;
    TextView textViewClearCache;
    LinearLayout linearLayoutClearCache;
    ImageView imageViewReturn;

    private List<SettingItem> settingItemListlist;
    private SettingAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
        initListener();
    }

    public void initView(){
        listViewSetting = (ListView) findViewById(R.id.listview_setting);
        textViewClearCache = (TextView) findViewById(R.id.clear_cache_brief);
        linearLayoutClearCache = (LinearLayout) findViewById(R.id.linearlayout_clear_cache);
        imageViewReturn = (ImageView) findViewById(R.id.image_return);
    }

    public void initData(){
        //初始化设置数据
        context = this;
        settingItemListlist = new ArrayList<SettingItem>();
        initSetting();
        adapter = new SettingAdapter(context, settingItemListlist);
        listViewSetting.setAdapter(adapter);

        try{
            textViewClearCache.setText("包括图片、安装包缓存：（共"+ CacheUtils.getTotalCacheSize(getApplicationContext())+"）");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void initListener(){
        //设置监听
        linearLayoutClearCache.setOnClickListener(this);
        imageViewReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回
            case R.id.image_return:
                finish();
                break;
            //清除缓存
            case R.id.linearlayout_clear_cache:
                try{
                    String cacheSize = CacheUtils.getTotalCacheSize(getApplicationContext());
                    CacheUtils.clearAllCache(getApplicationContext());
                    textViewClearCache.setText("包括图片、安装包缓存：（共"+ CacheUtils.getTotalCacheSize(getApplicationContext()) +"）");
                    Toast.makeText(getApplicationContext(), "清理缓存成功，一共清理了"+ cacheSize, Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }



    public void initSetting(){
        SettingItem item;
        //通知栏
        item = new SettingItem();
        item.title = "消息提示";
        item.brief = "通知栏显示活动信息";
        item.isOn = SPUtils.getInstance(context).getBoolean("MessageNotice", true);
        settingItemListlist.add(item);
        //省流量模式
        item = new SettingItem();
        item.title = "省流量模式";
        item.brief = "仅在WIFI模式下加载高质量图片";
        item.isOn = SPUtils.getInstance(context).getBoolean("ProvincialFlow", false);
        settingItemListlist.add(item);
        //自动删除安装包
        item = new SettingItem();
        item.title = "自动删除安装包";
        item.brief = "下载安装成功后自动删除安装包";
        item.isOn = SPUtils.getInstance(context).getBoolean("AutoDelete", true);
        settingItemListlist.add(item);
        //自动更新
        item = new SettingItem();
        item.title = "WLAN闲时自动更新";
        item.brief = "空闲条件：WLAN网络下，CPU使用率低于80%，电量高于30%";
        item.isOn = SPUtils.getInstance(context).getBoolean("AutoUpdate", false);
        settingItemListlist.add(item);
    }

    /**
     * 重启当前Activity
     */
    private void restartAct() {
        finish();
        Intent _Intent = new Intent(this, MainActivity.class);
        startActivity(_Intent);
        //清除Activity退出和进入的动画
        overridePendingTransition(0, 0);
    }
}
