package com.nzy.zkyt.store_wintec.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.nzy.zkyt.store_wintec.model.ApkInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2018/1/11.
 * 获取、管理本机已安装APP工具类
 */

public class ApkUtils {

    public static List<ApkInfo> localInstallAppList = null;   //已安装的APP列表

    /**
     * 获取已经安装的APP列表
     * @param packageManager
     * @return
     */
    public static List<ApkInfo> scanLocalInstallAppList(PackageManager packageManager){
        localInstallAppList = new ArrayList<ApkInfo>();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for(int i=0; i<packageInfoList.size();i++){
            PackageInfo packageInfo = packageInfoList.get(i);
            //过滤掉系统APP
            if((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0)
                continue;
            ApkInfo apkInfo = new ApkInfo();
            apkInfo.setApkPackageName(packageInfo.packageName);
            apkInfo.setApkName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
            apkInfo.setApkIcon(packageInfo.applicationInfo.loadIcon(packageManager));
            try {
                apkInfo.setApkSize(new SimpleDateFormat("yyyy-MM-dd").format(new Date(packageInfo.firstInstallTime )) );
            } catch (Exception e){
                e.printStackTrace();
            }

            localInstallAppList.add(apkInfo);
        }

        return localInstallAppList;
    }

}
