package com.nzy.zkyt.store_wintec.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.model.ApkInfo;
import com.nzy.zkyt.store_wintec.ui.adapter.LocalInstallApkAdapter;
import com.nzy.zkyt.store_wintec.util.ApkUtils;

import java.util.ArrayList;
import java.util.List;

public class LocalInstalledApkActivity extends AppCompatActivity {

    ListView listView;
    private Handler handler = new Handler();
    private Context context;
    private List<ApkInfo> data = new ArrayList<ApkInfo>();
    private LocalInstallApkAdapter apkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_installed_apk);

        initView();
        initData();
        initListener();
    }

    public void initView(){
        listView = (ListView) findViewById(R.id.local_install_apk_listview);
    }

    public void initData(){
        context = this;

        new Thread(new Runnable(){
            @Override
            public void run(){
                data=ApkUtils.scanLocalInstallAppList(context.getPackageManager());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        apkAdapter = new LocalInstallApkAdapter(context, data);
                        listView.setAdapter(apkAdapter);
                    }
                });
            }
        }).start();
    }

    public void initListener(){

    }
}
