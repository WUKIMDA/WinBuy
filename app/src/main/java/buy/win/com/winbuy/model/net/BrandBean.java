package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class BrandBean extends BaseBean {

   //plist?page=1&pageNum=8&id=1210&orderby=saleDown

    private List<ProductListBean> productList;

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public static class ProductListBean {
        /**
         * id : 16
         * marketPrice : 400
         * name : 韩版秋装
         * pic : /images/product/detail/q14.jpg
         * price : 300
         */

        private int id;
        private int marketPrice;
        private String name;
        private String pic;
        private int price;

        public void setId(int id) {
            this.id = id;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }

        public int getPrice() {
            return price;
        }
    }
}
