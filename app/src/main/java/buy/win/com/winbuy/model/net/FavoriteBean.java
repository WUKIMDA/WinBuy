package buy.win.com.winbuy.model.net;

import java.io.Serializable;
import java.util.List;

/**
 * Created by demo on 2017/6/20.
 */

public class FavoriteBean extends ErrorBean implements Serializable{


    /**
     * productList : [{"id":"1","name":"韩版时尚蕾丝裙","pic":"","marketPrice":"500","price":"350"},{"id":"2","name":"粉色毛衣","pic":"","marketPrice":"79","price":"78"}]
     * listCount : 2
     */

    private String listCount;
    private List<ProductListBean> productList;

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean implements Serializable{
        /**
         * id : 1
         * name : 韩版时尚蕾丝裙
         * pic :
         * marketPrice : 500
         * price : 350
         */

        private String id;
        private String name;
        private String pic;
        private String marketPrice;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
