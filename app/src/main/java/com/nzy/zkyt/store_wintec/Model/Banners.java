package com.nzy.zkyt.store_wintec.Model;

import java.util.List;

/**
 * 作者：宁震宇on 2017/12/2.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class Banners {
    private List<String> imgs;
    private List<String> title;

    public Banners(List<String> imgs, List<String> title) {
        this.imgs = imgs;
        this.title = title;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }
}
