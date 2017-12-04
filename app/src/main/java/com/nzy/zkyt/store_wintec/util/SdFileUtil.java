package com.nzy.zkyt.store_wintec.util;

import java.io.File;

/**
 * 作者：宁震宇on 2017/11/17.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class SdFileUtil {
    private static SdFileUtil sdFileUtil;
    private SdFileUtil() {}
    public static SdFileUtil getInstance() {

        if (sdFileUtil == null) {
            synchronized (SPUtils.class) {
                if (sdFileUtil == null) {
                    sdFileUtil = new SdFileUtil();
                    return sdFileUtil;
                }
            }
        }
        return sdFileUtil;
    }


    public File getFile(String filepath){
        return getFile(new File(filepath));
    }
    public File getFile(File file){
        if (file.exists()){
            return file;
        }else {
            UIUtils.showToast("文件不存在！");
            return null;
        }
    }


}
