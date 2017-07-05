package com.yutou.bean;

import java.util.List;

/**
 *描    述：产品实体类
 *创 建 人：ZJY
 *创建日期：2017/4/7 9:12
 *修订历史：
 *修 改 人：
 */

public class ProductBean {

    /**
     * code : 40000
     * hint :
     * list : {"carousel":[{"thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20160803/20160803095744_54736.jpg","name":"泰国ELE ELE 减少黑眼圈消除斑纹美容套装","url":"http://huisou.m.huisou.com/?g=app&m=apps&a=product_detail&id=317","href_type":"2","href_model":"product_detail","data_id":"317"},{"thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20160803/20160803095721_74729.jpg","name":"薇姿Vichy泉之净清润爽肤水","url":"http://huisou.m.huisou.com/?g=app&m=apps&a=product_detail&id=316","href_type":"2","href_model":"product_detail","data_id":"316"},{"thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20160803/20160803095627_97513.jpg","name":"薇姿净颜无瑕面部护肤两步曲套装","url":"http://huisou.m.huisou.com/?g=app&m=apps&a=product_detail&id=316","href_type":"2","href_model":"product_detail","data_id":"316"}],"product_list":[{"product_id":"556","title":"空气净化器","short_title":"空气净化器","price":"2690.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202154416_66135.jpg","sale_num":"28人购买"},{"product_id":"555","title":"净水器","short_title":"净水器","price":"2680.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202153845_84691.jpg","sale_num":"13人购买"},{"product_id":"551","title":"山茶油","short_title":"山茶油","price":"399.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202113939_34806.jpg","sale_num":"14人购买"},{"product_id":"550","title":"纳米卫生巾","short_title":"纳米卫生巾","price":"106.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202105743_32968.jpg","sale_num":"58人购买"},{"product_id":"549","title":"把根留住洗发水200ml ","short_title":"把根留住洗发水200ml","price":"100.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202105445_83468.jpg","sale_num":"9人购买"},{"product_id":"548","title":"把根留住洗发水300ml","short_title":"把根留住洗发水300ml","price":"228.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202105133_29708.jpg","sale_num":"10人购买"},{"product_id":"547","title":"紫椴蜂蜜","short_title":"紫椴蜂蜜","price":"368.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161201/20161201172232_52677.jpg","sale_num":"7人购买"},{"product_id":"546","title":"糠椴蜂蜜","short_title":"糠椴蜂蜜","price":"168.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161201/20161201172142_64588.jpg","sale_num":"11人购买"},{"product_id":"545","title":"硅藻泥面膜","short_title":"硅藻泥面膜","price":"458.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161201/20161201172021_40458.jpg","sale_num":"9人购买"},{"product_id":"544","title":"绿盛功夫牛肉 买就送公仔","short_title":"绿盛功夫牛肉 买就送公仔","price":"188.00","img":"http://china.m.huisou.com/Uploads/Admin/image/20161201/20161201165401_85514.jpg","sale_num":"1人购买"}]}
     */

    private String code;
    private String hint;
    private ListBean list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * thumbnail : https://hssc.m.huisou.com/Uploads/Admin/image/20160803/20160803095744_54736.jpg
         * name : 泰国ELE ELE 减少黑眼圈消除斑纹美容套装
         * url : http://huisou.m.huisou.com/?g=app&m=apps&a=product_detail&id=317
         * href_type : 2
         * href_model : product_detail
         * data_id : 317
         */

        private List<CarouselBean> carousel;
        /**
         * product_id : 556
         * title : 空气净化器
         * short_title : 空气净化器
         * price : 2690.00
         * img : http://china.m.huisou.com/Uploads/Admin/image/20161202/20161202154416_66135.jpg
         * sale_num : 28人购买
         */

        private List<ProductListBean> product_list;

        public List<CarouselBean> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<CarouselBean> carousel) {
            this.carousel = carousel;
        }

        public List<ProductListBean> getProduct_list() {
            return product_list;
        }

        public void setProduct_list(List<ProductListBean> product_list) {
            this.product_list = product_list;
        }

        public static class CarouselBean {
            private String thumbnail;
            private String name;
            private String url;
            private String href_type;
            private String href_model;
            private String data_id;

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getHref_type() {
                return href_type;
            }

            public void setHref_type(String href_type) {
                this.href_type = href_type;
            }

            public String getHref_model() {
                return href_model;
            }

            public void setHref_model(String href_model) {
                this.href_model = href_model;
            }

            public String getData_id() {
                return data_id;
            }

            public void setData_id(String data_id) {
                this.data_id = data_id;
            }
        }

        public static class ProductListBean {
            private String product_id;
            private String title;
            private String short_title;
            private String price;
            private String img;
            private String sale_num;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShort_title() {
                return short_title;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSale_num() {
                return sale_num;
            }

            public void setSale_num(String sale_num) {
                this.sale_num = sale_num;
            }
        }
    }
}
