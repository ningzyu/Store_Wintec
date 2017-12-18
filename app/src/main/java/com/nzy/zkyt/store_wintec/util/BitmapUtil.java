package com.nzy.zkyt.store_wintec.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static android.graphics.Bitmap.createBitmap;

/**
 * 作者：宁震宇on 2017/12/15.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class BitmapUtil {
    /**
     *  Drawble转Bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     *  从资源中获取Bitmap
     * @param context  上下文
     * @param id       Drawable的id    R.id.xxx
     * @return
     */
    public static Bitmap getBitmapFromRes(Context context, int id) {
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, id);
        return bmp;

    }

    /**
     *  Bitmap → byte[]
     * @param bm
     * @return
     */
    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     *  byte[] → Bitmap
     * @param b
     * @return
     */
    private Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * 将assets文件中资源取出,并将图片从bitmap转换成drawable格式
     * @param context
     * @param fileName
     * @return
     */
    public static Drawable getDrawableFromAssetFile(Context context, String fileName) {
        Bitmap image = null;
        BitmapDrawable drawable = null;
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            drawable = new BitmapDrawable(context.getResources(), image);
            is.close();
        } catch (Exception e) {
        }
        return drawable;
    }



    /**
     * 获取圆角图片
     * @param bitmap
     * @param roundPx
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx){
        Bitmap output = null;
        try {
            if(bitmap == null)
                return null;
            output = createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);

            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (OutOfMemoryError e) {
            System.gc();
            output = null;
        }
        return output;
    }



    public static Drawable  getDraw(int width,int height,int [] colors){
        Bitmap bitmap = createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(width, 0, 0, height,colors, null, Shader.TileMode.CLAMP);
//        LinearGradient backGradient = new LinearGradient(0, 0, 0, height, new int[]{colorStart, color1 ,colorEnd}, null, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);
        Drawable drawable = new BitmapDrawable(UIUtils.getContext().getResources(),bitmap);
        return drawable;
    }

}
