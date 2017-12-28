package com.nzy.zkyt.store_wintec.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.util.LocaleUtils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listViewSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    public void initView(){
        listViewSetting = (ListView) findViewById(R.id.listview_setting);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
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
