package com.nzy.zkyt.store_wintec.ui.fragment;

/**
 * @创建者 CSDN_LQR
 * @描述 主界面4个Fragment工厂
 */
public class FragmentFactory {

    static FragmentFactory mInstance;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private HomeFragment home_fg;
    private ClassifyFragment classify_fg;
    private MyFragment my_fg;

    public HomeFragment getFg01() {
        if (home_fg == null) {
            synchronized (FragmentFactory.class) {
                if (home_fg == null) {
                    home_fg = new HomeFragment();
                }
            }
        }
        return home_fg;
    }

    public ClassifyFragment getFg02() {
        if (classify_fg == null) {
            synchronized (FragmentFactory.class) {
                if (classify_fg == null) {
                    classify_fg = new ClassifyFragment();
                }
            }
        }
        return classify_fg;
    }

    public MyFragment getFg03() {
        if (my_fg == null) {
            synchronized (FragmentFactory.class) {
                if (my_fg == null) {
                    my_fg = new MyFragment();
                }
            }
        }
        return my_fg;
    }

}
