package com.nzy.zkyt.store_wintec.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.nzy.zkyt.store_wintec.R;
import com.zhy.android.percent.support.PercentRelativeLayout;

/**
 * 作者：宁震宇on 2017/12/15.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class MyView  extends PercentRelativeLayout {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();
        int colorStart = getResources().getColor(R.color.colorPrimary);
        int color1 = Color.GRAY;
        int colorEnd = getResources().getColor(R.color.colorAccent);
        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(width, 0, 0, height, new int[]{colorEnd, colorStart ,colorEnd}, null, Shader.TileMode.CLAMP);
//        LinearGradient backGradient = new LinearGradient(0, 0, 0, height, new int[]{colorStart, color1 ,colorEnd}, null, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);
    }
}