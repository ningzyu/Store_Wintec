package com.nzy.zkyt.store_wintec.base;

import android.os.Environment;

import com.nzy.zkyt.store_wintec.util.UIUtils;

/**
 * 作者：宁震宇on 2017/11/1.
 * 邮箱：348723352@qq.com
 * 本类作用：全局常量
 */

public class AppConst {
    public static final String TAG = "CSDN_LQR";

    /**
     * SETTING
     * 设置
     */
    public static final class Settings {
        public static final String SHOP_ID = "shopid";
        public static final String IP = "ip";
        public static final String PORT = "port";
        public static String UserId="";
    }

    /**
     * PATH
     * 路径
     */
    public static final String SD_Directory= Environment.getExternalStorageDirectory().getPath();
    public static final String KUDI_PATH=SD_Directory+"/kudipets";
    public static final String GOODS_PATH=KUDI_PATH + "/goods.xls";


    /**
     * KEY
     * 键名
     * value    inventory       receive
     */
    public static final String KEY_SOURE="source";
    public static final String VALUE_RECEIVE="receive";//收货
    public static final String VALUE_INVENTORY="inventory";//盘点


    /**
     * 文件中使用的数值类型常量
     */
    public static final int BANNER_HEIGHT= UIUtils.getDisplayHeight()/ 3;;



}
