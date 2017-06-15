package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class CartAll extends BaseBean{


    /**
     * cart : [{"id":1,"product":{"buyLimit":10,"id":2,"name":"粉色毛衣","number":"13","pic":"/images/product/detail/q1.jpg","price":100},"productCount":1,"productId":2,"property":{"id":1,"k":"颜色","v":"红色"},"propertyId":1,"userId":20428}]
     */

    private List<CartBean> cart;

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public static class CartBean {
        /**
         * id : 1
         * product : {"buyLimit":10,"id":2,"name":"粉色毛衣","number":"13","pic":"/images/product/detail/q1.jpg","price":100}
         * productCount : 1
         * productId : 2
         * property : {"id":1,"k":"颜色","v":"红色"}
         * propertyId : 1
         * userId : 20428
         */

        private int id;
        private ProductBean product;
        private int productCount;
        private int productId;
        private PropertyBean property;
        private int propertyId;
        private int userId;

        public void setId(int id) {
            this.id = id;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public void setProperty(PropertyBean property) {
            this.property = property;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public ProductBean getProduct() {
            return product;
        }

        public int getProductCount() {
            return productCount;
        }

        public int getProductId() {
            return productId;
        }

        public PropertyBean getProperty() {
            return property;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public int getUserId() {
            return userId;
        }

        public static class ProductBean {
            /**
             * buyLimit : 10
             * id : 2
             * name : 粉色毛衣
             * number : 13
             * pic : /images/product/detail/q1.jpg
             * price : 100
             */

            private int buyLimit;
            private int id;
            private String name;
            private String number;
            private String pic;
            private int price;

            public void setBuyLimit(int buyLimit) {
                this.buyLimit = buyLimit;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getBuyLimit() {
                return buyLimit;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getNumber() {
                return number;
            }

            public String getPic() {
                return pic;
            }

            public int getPrice() {
                return price;
            }
        }

        public static class PropertyBean {
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
        }
    }
}
