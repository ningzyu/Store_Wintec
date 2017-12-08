package com.nzy.zkyt.store_wintec.util;

import android.graphics.Color;
import android.view.View;

/**
 * 作者：宁震宇on 2017/12/4.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class ColorUtil {

    public void setAlpha(View view, int alpha, int red, int green, int blue){
        view.setBackgroundColor(Color.argb( 0, 204, 204, 204));
    }
    public void setAlpha(View view, int alpha){
        view.getBackground().setAlpha(125);
    }

}
