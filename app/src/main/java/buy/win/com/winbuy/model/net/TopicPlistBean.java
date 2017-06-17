package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopicPlistBean {

    /**
     * productList : [{"id":18,"marketPrice":200,"name":"短裙","pic":"/images/product/detail/q16.jpg","price":160},{"id":19,"marketPrice":200,"name":"新款秋装","pic":"/images/product/detail/q17.jpg","price":160},{"id":20,"marketPrice":200,"name":"妈妈新款","pic":"/images/product/detail/q18.jpg","price":160},{"id":24,"marketPrice":200,"name":"春秋新款外套","pic":"/images/product/detail/q22.jpg","price":160},{"id":25,"marketPrice":150,"name":"新款秋装","pic":"/images/product/detail/q23.jpg","price":160},{"id":26,"marketPrice":200,"name":"粉色系暖心套装","pic":"/images/product/detail/q24.jpg","price":200},{"id":27,"marketPrice":150,"name":"韩版粉嫩外套","pic":"/images/product/detail/q25.jpg","price":160},{"id":28,"marketPrice":300,"name":"春装新款","pic":"/images/product/detail/q26.jpg","price":200},{"id":29,"marketPrice":180,"name":"日本奶粉","pic":"/images/product/detail/q26.jpg","price":160},{"id":30,"marketPrice":200,"name":"超凡奶粉","pic":"/images/product/detail/q26.jpg","price":160}]
     * response : topicProductList
     */

    public String response;
    public List<ProductListBean> productList;

    public static class ProductListBean {
        /**
         * id : 18
         * marketPrice : 200
         * name : 短裙
         * pic : /images/product/detail/q16.jpg
         * price : 160
         */

        public int id;
        public int marketPrice;
        public String name;
        public String pic;
        public int price;
    }
}
