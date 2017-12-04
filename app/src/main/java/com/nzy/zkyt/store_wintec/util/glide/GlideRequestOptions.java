package com.nzy.zkyt.store_wintec.util.glide;

/**
 * Glide4.0版本，设置错误图片，默认图片，设置圆形图等方法要用 RequestOptions
 */
public class GlideRequestOptions
{

    private static GlideRequestOptions instance;

    public static GlideRequestOptions getInstance()
    {
        if (instance == null)
        {
            synchronized (GlideRequestOptions.class)
            {
                if (instance == null)
                {
                    instance = new GlideRequestOptions();
                }
            }
        }
        return instance;
    }


}
