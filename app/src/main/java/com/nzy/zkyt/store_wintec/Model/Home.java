package com.nzy.zkyt.store_wintec.Model;

import java.util.List;

/**
 * 作者：宁震宇on 2017/11/30.
 * 邮箱：348723352@qq.com
 * 本类作用：
 */

public class Home {
    private List<BannersBean> banners;
    private List<DataBean> data;
    private List<FlagsBean> flags;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<FlagsBean> getFlags() {
        return flags;
    }

    public void setFlags(List<FlagsBean> flags) {
        this.flags = flags;
    }

    public  class BannersBean {
        /**
         * icon : image/icon_test.png
         * image : image/banner01.jpg
         * items : [{"image":"image/logo_meipai.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_qq.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_taobao.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_tctv.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_weibo.png","introduce":"这是一个App","type":"什么类型"}]
         * title : 轮播1
         */

        private String icon;
        private String image;
        private String title;
        private List<ItemsBean> items;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public  class ItemsBean {
            /**
             * image : image/logo_meipai.png
             * introduce : 这是一个App
             * type : 什么类型
             */

            private String image;
            private String introduce;
            private String type;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public  class DataBean {
        /**
         * image : image/logo_meipai.png
         * introduce : 这是一个App
         * type : 什么类型
         */

        private String image;
        private String introduce;
        private String type;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public  class FlagsBean {
        /**
         * icon : image/icon_test.png
         * image : image/banner01.jpg
         * items : [{"image":"image/logo_meipai.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_qq.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_taobao.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_tctv.png","introduce":"这是一个App","type":"什么类型"},{"image":"image/logo_weibo.png","introduce":"这是一个App","type":"什么类型"}]
         * title : 必备
         */

        private String icon;
        private String image;
        private String title;
        private List<ItemsBeanX> items;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ItemsBeanX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanX> items) {
            this.items = items;
        }

        public class ItemsBeanX {
            /**
             * image : image/logo_meipai.png
             * introduce : 这是一个App
             * type : 什么类型
             */

            private String image;
            private String introduce;
            private String type;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
