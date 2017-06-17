package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by Ziwen on 2017/6/16.
 */

public class LimitbuyBean {

    /**
     * listCount : 10
     * productList : [{"id":2,"leftTime":17000,"limitPrice":1,"name":"粉色毛衣","pic":"/images/product/detail/q1.jpg","price":100},{"id":3,"leftTime":16000,"limitPrice":90,"name":"女裙","pic":"/images/product/detail/c1.jpg","price":300},{"id":4,"leftTime":15000,"limitPrice":98,"name":"帽子","pic":"/images/product/detail/b1.jpg","price":168},{"id":5,"leftTime":14000,"limitPrice":68,"name":"时尚女裙","pic":"/images/product/detail/a1.jpg","price":108},{"id":6,"leftTime":13000,"limitPrice":36,"name":"时尚秋装","pic":"/images/product/detail/w2.jpg","price":52},{"id":7,"leftTime":14000,"limitPrice":120,"name":"韩版外套","pic":"/images/product/detail/qun1.jpg","price":160},{"id":8,"leftTime":14000,"limitPrice":120,"name":"情女装","pic":"/images/product/detail/q6.jpg","price":160},{"id":9,"leftTime":14000,"limitPrice":120,"name":"女鞋","pic":"/images/product/detail/q7.jpg","price":200},{"id":10,"leftTime":14000,"limitPrice":120,"name":"韩版棉袄","pic":"/images/product/detail/q8.jpg","price":160},{"id":11,"leftTime":14000,"limitPrice":120,"name":"韩版秋装","pic":"/images/product/detail/q9.jpg","price":160}]
     * response : limitbuy
     */

    public int listCount;
    public String response;
    public List<ProductListBean> productList;

    public static class ProductListBean {
        /**
         * id : 2
         * leftTime : 17000
         * limitPrice : 1
         * name : 粉色毛衣
         * pic : /images/product/detail/q1.jpg
         * price : 100
         */

        public int id;
        public int leftTime;
        public int limitPrice;
        public String name;
        public String pic;
        public int price;
    }
}
