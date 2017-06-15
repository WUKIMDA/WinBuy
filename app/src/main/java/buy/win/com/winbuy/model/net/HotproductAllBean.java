package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class HotproductAllBean extends BaseBean {

    private int listCount;
    private List<ProductListBean> productList;

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public int getListCount() {
        return listCount;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public static class ProductListBean {
        /**
         * id : 22
         * marketPrice : 200
         * name : 新款毛衣
         * pic : /images/product/detail/q20.jpg
         * price : 160
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
