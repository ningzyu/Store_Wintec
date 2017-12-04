package com.nzy.zkyt.store_wintec.base;

import android.content.res.Configuration;
import android.os.Build;

import com.nzy.zkyt.store_wintec.util.LocaleUtils;

import java.util.Locale;

/**
 * 作者：宁震宇on 2017/11/1.
 * 邮箱：348723352@qq.com
 * 本类作用：BaseApp的拓展，用于设置其他第三方的初始化
 */

public class MyApp extends BaseApp{

    @Override
    public void onCreate() {
        super.onCreate();
        //一些第三方的初始化
        Locale locale= LocaleUtils.getUserLocale(this);
        LocaleUtils.updateLocale(this, locale);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale locale=LocaleUtils.getUserLocale(this);
        //系统语言改变了应用保持之前设置的语言
        if (locale != null) {
            Locale.setDefault(locale);
            Configuration configuration = new Configuration(newConfig);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale =locale;
            }
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
    }
}
