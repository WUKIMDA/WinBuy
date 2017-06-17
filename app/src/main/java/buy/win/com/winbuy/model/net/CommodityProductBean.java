package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-16.
 */

public class CommodityProductBean extends BaseBean {



    private ProductBean product;

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public ProductBean getProduct() {
        return product;
    }

    public static class ProductBean {

        private boolean available;
        private int buyLimit;
        private int commentCount;
        private int id;
        private String inventoryArea;
        private int leftTime;
        private int limitPrice;
        private int marketPrice;
        private String name;
        private int price;
        private int score;
        private List<String> bigPic;
        private List<String> pics;
        private List<ProductPropertyBean> productProperty;

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setInventoryArea(String inventoryArea) {
            this.inventoryArea = inventoryArea;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setBigPic(List<String> bigPic) {
            this.bigPic = bigPic;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public void setProductProperty(List<ProductPropertyBean> productProperty) {
            this.productProperty = productProperty;
        }

        public boolean getAvailable() {
            return available;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public int getId() {
            return id;
        }

        public String getInventoryArea() {
            return inventoryArea;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getScore() {
            return score;
        }

        public List<String> getBigPic() {
            return bigPic;
        }

        public List<String> getPics() {
            return pics;
        }

        public List<ProductPropertyBean> getProductProperty() {
            return productProperty;
        }

        public static class ProductPropertyBean {
            /**
             * id : 1
             * k : 颜色
             * v : 红色
             */

            private int id;
            private String k;
            private String v;

            public void setId(int id) {
                this.id = id;
            }

            public void setK(String k) {
                this.k = k;
            }

            public void setV(String v) {
                this.v = v;
            }

            public int getId() {
                return id;
            }

            public String getK() {
                return k;
            }

            public String getV() {
                return v;
            }

            @Override
            public String toString() {
                return "ProductPropertyBean{" +
                        "id=" + id +
                        ", k='" + k + '\'' +
                        ", v='" + v + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "CommodityProductBean{" +
                "product=" + product +
                '}';
    }
}
