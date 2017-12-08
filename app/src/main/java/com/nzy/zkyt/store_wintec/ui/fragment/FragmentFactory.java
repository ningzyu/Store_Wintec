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

    private HomeFragment home_Fg;
    private ClassifyFragment classify_Fg;
    private MyFragment my_Fg;
    private DetailsFragment details_Fg;
    private AdviceFragment advice_Fg;

    public DetailsFragment getDetailsFg() {
        if (details_Fg == null) {
            synchronized (FragmentFactory.class) {
                if (details_Fg == null) {
                    details_Fg = new DetailsFragment();
                }
            }
        }
        return details_Fg;
    }
    public AdviceFragment getAdviceFg() {
        if (advice_Fg == null) {
            synchronized (FragmentFactory.class) {
                if (advice_Fg == null) {
                    advice_Fg = new AdviceFragment();
                }
            }
        }
        return advice_Fg;
    }


    public HomeFragment getFg01() {
        if (home_Fg == null) {
            synchronized (FragmentFactory.class) {
                if (home_Fg == null) {
                    home_Fg = new HomeFragment();
                }
            }
        }
        return home_Fg;
    }

    public ClassifyFragment getFg02() {
        if (classify_Fg == null) {
            synchronized (FragmentFactory.class) {
                if (classify_Fg == null) {
                    classify_Fg = new ClassifyFragment();
                }
            }
        }
        return classify_Fg;
    }

    public MyFragment getFg03() {
        if (my_Fg == null) {
            synchronized (FragmentFactory.class) {
                if (my_Fg == null) {
                    my_Fg = new MyFragment();
                }
            }
        }
        return my_Fg;
    }

}
