package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by lenovo on 2017/6/18.
 */

public class GoodsBean extends BaseBean {

    /**
     * commentCount : 1
     * id : 8
     * marketPrice : 200
     * name : 情女装
     * pic : /images/product/detail/q6.jpg
     * price : 160
     */

    private List<ProductListBean> productList;

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        private int commentCount;
        private int id;
        private int marketPrice;
        private String name;
        private String pic;
        private int price;

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
