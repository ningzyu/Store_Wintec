package com.nzy.zkyt.store_wintec.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nzy.zkyt.store_wintec.R;
import com.nzy.zkyt.store_wintec.util.BitmapUtil;
import com.nzy.zkyt.store_wintec.util.GlideUtil;

public class DownloadActivity extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Bitmap bmp;
    private ImageView iv;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
         layout= (LinearLayout) findViewById(R.id.layout);
        tv1= (TextView) findViewById(R.id.tv_1);
        tv2= (TextView) findViewById(R.id.tv_2);
        tv3= (TextView) findViewById(R.id.tv_3);
        tv4= (TextView) findViewById(R.id.tv_4);
        iv= (ImageView) findViewById(R.id.iv);
//        GlidePalette.with(url)
//                .use(GlidePalette.Profile.MUTED_DARK)
//                .intoBackground(textView)
//                .intoTextColor(textView)

    }
    public void BonClick(View v){
        switch (v.getId()){
            case R.id.b1:
//                iv.setImageResource(R.drawable.icon);
//                 bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                Glide.with(this).load("http://www.xixihaha.xin/image/logo_weixin.png").into(iv);
                sss("http://www.xixihaha.xin/image/logo_weixin.png");
                break;
            case R.id.b2:
                Glide.with(this).load("http://www.xixihaha.xin/image/logo_taobao.png").into(iv);
                sss("http://www.xixihaha.xin/image/logo_taobao.png");


//                iv.setImageResource(R.drawable.logo_meipai);
//                 bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_meipai);
//                setView(bmp);
                break;
            case R.id.b3:

                Glide.with(this).load("http://www.xixihaha.xin/image/logo_qq.png").into(iv);
                sss("http://www.xixihaha.xin/image/logo_qq.png");
//                iv.setImageResource(R.drawable.logo_qq);
//                 bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_qq);
//                setView(bmp);
                break;
            case R.id.b4:

                Glide.with(this).load("http://www.xixihaha.xin/image/logo_weibo.png").into(iv);
                sss("http://www.xixihaha.xin/image/logo_weibo.png");
//                iv.setImageResource(R.drawable.logo_taobao);
//                 bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_taobao);
//                setView(bmp);
                break;
            case R.id.b5:
                Glide.with(this).load("http://www.xixihaha.xin/image/logo_meipai.png").into(iv);
                sss("http://www.xixihaha.xin/image/logo_meipai.png");
//                iv.setImageResource(R.drawable.logo_weibo);
//                 bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_weibo);
//                setView(bmp);
                break;
            default:
                break;
        }
    }

    public void sss(String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=GlideUtil.getInstance(DownloadActivity.this).getBitmap(url);
                Message message=new Message();
                message.obj=bitmap;
                handler.sendMessage(message);
            }
        }).start();
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            setView((Bitmap) message.obj);
            return false;
        }
    });




    public void setView(Bitmap view) {
        Palette.generateAsync(view, palette -> {
            //4.柔色
//            Palette.Swatch swatch1 = palette.getMutedSwatch();
            //1.活力颜色
            Palette.Swatch swatch2 = palette.getVibrantSwatch();////
            //暗的活力颜色
//            Palette.Swatch swatch3 = palette.getDarkMutedSwatch();
            Palette.Swatch swatch4 = palette.getDarkVibrantSwatch();
//            Palette.Swatch swatch5 = palette.getLightMutedSwatch();
            Palette.Swatch swatch6 = palette.getLightVibrantSwatch();
            if (swatch2 != null) {
                tv1.setTextColor(swatch2.getRgb());
            }else {
                Log.i("cccccccccccccccccc","swatch2=====空===");
            }
            if (swatch4 != null) {
                tv2.setTextColor(swatch4.getRgb());
            }else {
                Log.i("cccccccccccccccccc","swatch4=====空===");
            }
            if (swatch6 != null) {
                tv3.setTextColor(swatch6.getRgb());
            }else {
                Log.i("cccccccccccccccccc","swatch6=====空===");
                swatch6.getBodyTextColor();
            }
            int [] colors={swatch2.getRgb(),
                    swatch6.getRgb(),
                    swatch6.getRgb(),
                    swatch6.getRgb(),
                    swatch6.getRgb()
            };
            layout.setBackground(BitmapUtil.getDraw(100,200,colors));
        });
    }
}
