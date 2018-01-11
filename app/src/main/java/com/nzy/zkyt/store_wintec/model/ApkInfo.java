package com.nzy.zkyt.store_wintec.model;

import android.graphics.drawable.Drawable;

/**
 * Created by hasee on 2018/1/11.
 * 已安装APP实体类
 */

public class ApkInfo {

    private String apkName;
    private String apkPackageName;
    private Drawable apkIcon;
    private String apkSize;

    public ApkInfo(){
    }

    public ApkInfo(String apkName, String apkPackageName, Drawable apkIcon, String apkSize){
        this.apkName = apkName;
        this.apkPackageName = apkPackageName;
        this.apkIcon = apkIcon;
        this.apkSize = apkSize;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getApkPackageName() {
        return apkPackageName;
    }

    public void setApkPackageName(String apkPackageName) {
        this.apkPackageName = apkPackageName;
    }

    public Drawable getApkIcon() {
        return apkIcon;
    }

    public void setApkIcon(Drawable apkIcon) {
        this.apkIcon = apkIcon;
    }

    public String getApkSize() {
        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }
}
