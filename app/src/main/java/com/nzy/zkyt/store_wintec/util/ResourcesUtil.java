package com.nzy.zkyt.store_wintec.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * 作者：宁震宇on 2017/12/11.
 * 邮箱：348723352@qq.com
 * 本类作用：关于drawable文件和Color的一些操作
 */

public class ResourcesUtil {
    private static ResourcesUtil resUtil;
    private ResourcesUtil() {}
    public static ResourcesUtil getInstance() {

        if (resUtil == null) {
            synchronized (SPUtils.class) {
                if (resUtil == null) {
                    resUtil = new ResourcesUtil();
                    return resUtil;
                }
            }
        }
        return resUtil;
    }

    public VectorDrawableCompat setVector(int drawable,int color){
        VectorDrawableCompat aa = VectorDrawableCompat.create(UIUtils.getContext().getResources(),drawable, UIUtils.getContext().getTheme());
//        aa.setTint(UIUtils.getContext().getResources().getColor(color)); //设置单一的颜色
        aa.setTint(color); //设置单一的颜色
        //用这个v4提供的也可，这个适用于任意的drawable着色
//        DrawableCompat.setTint(aa,UIUtils.getContext().getResources().getColor(color));
        return aa;
    }

    Random random = new Random();
    int color = 0xff000000 | random.nextInt(0xffffff);
    //白变黑，透明度不变为1的时候为黑色
    public int getColor(float f) {
        return getColor(f,255);
    }

    public int getColor(float f, int alpha){
        int i= (int) (f*250);
        int value=250-i;
        Log.i("colorcolor","颜色值"+i+"----"+f);
        return Color.argb(alpha, value, value, value);
    }
    public int getColor(int alpha,int value){
        return Color.argb(alpha, value, value, value);
    }
    public int getColorAlpha(float f){
        int i= (int) (f*255);
        return getColor(f,i);
    }


    public void setAlpha(View view, int alpha, int red, int green, int blue){
        view.setBackgroundColor(Color.argb( 0, 204, 204, 204));
    }
    public void setAlpha(View view, int alpha){
        view.getBackground().setAlpha(125);
    }

//    colors = new int[]{
//        Color.BLACK,
//                getResources().getColor(android.R.color.holo_red_light),
//                getResources().getColor(android.R.color.holo_blue_bright),
//                getResources().getColor(android.R.color.holo_orange_dark),
//                getResources().getColor(android.R.color.holo_green_dark),
//                getResources().getColor(android.R.color.holo_purple),
//                getResources().getColor(R.color.colorPrimaryDark),
//                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
//    };

    public Bitmap getBitmap(int drawable){
        return BitmapFactory.decodeResource(UIUtils.getContext().getResources(),drawable);
    }


    /**
     * 颜色相关
     */
    GradientDrawable grad = new GradientDrawable(//渐变色
            GradientDrawable.Orientation.TOP_BOTTOM,
            new int[]{Color.BLACK, Color.WHITE,Color.BLACK});
}
