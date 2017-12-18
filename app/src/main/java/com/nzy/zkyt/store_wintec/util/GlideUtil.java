package com.nzy.zkyt.store_wintec.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import java.util.concurrent.ExecutionException;

/**
 * 作者：宁震宇on 2017/12/11.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class GlideUtil {

    private Context context;
    private static GlideUtil glideUtil;//
    private GlideUtil(Context context) {
        this.context=context;
    }

    public static GlideUtil getInstance(Context context) {
        if (glideUtil == null) {
            synchronized (GlideUtil.class) {
                if (glideUtil == null) {
                    glideUtil = new GlideUtil(context);
                    return glideUtil;
                }
            }
        }
        return glideUtil;
    }

    public Bitmap getBitmap(String url){
        Bitmap myBitmap = null;
        try {
            myBitmap = Glide.with(context)
                    .load(url)
                    .asBitmap() //必须
                    .centerCrop()
                    .into(500, 500)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return myBitmap;
    }

    public static class GlideImageLoader extends ImageLoader {
        public static GlideImageLoader imageLoader=new GlideImageLoader();//
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
            Glide.with(context)
                    .load(path)//图片地址
                    .crossFade()
                    .into(imageView);
        }
    }
}
