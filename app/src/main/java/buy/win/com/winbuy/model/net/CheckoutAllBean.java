package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-17.
 */

public class CheckoutAllBean extends  BaseBean{

    private AddressInfoBean addressInfo;
    private CheckoutAddupBean checkoutAddup;
    private List<String> checkoutProm;
    private List<DeliveryListBean> deliveryList;
    private List<PaymentListBean> paymentList;
    private List<ProductListBean> productList;

    public void setAddressInfo(AddressInfoBean addressInfo) {
        this.addressInfo = addressInfo;
    }

    public void setCheckoutAddup(CheckoutAddupBean checkoutAddup) {
        this.checkoutAddup = checkoutAddup;
    }

    public void setCheckoutProm(List<String> checkoutProm) {
        this.checkoutProm = checkoutProm;
    }

    public void setDeliveryList(List<DeliveryListBean> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public void setPaymentList(List<PaymentListBean> paymentList) {
        this.paymentList = paymentList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public AddressInfoBean getAddressInfo() {
        return addressInfo;
    }

    public CheckoutAddupBean getCheckoutAddup() {
        return checkoutAddup;
    }

    public List<String> getCheckoutProm() {
        return checkoutProm;
    }

    public List<DeliveryListBean> getDeliveryList() {
        return deliveryList;
    }

    public List<PaymentListBean> getPaymentList() {
        return paymentList;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public static class AddressInfoBean {
        @Override
        public String toString() {
            return name+"/r/n"+phoneNumber+"/r/n"+province+","+city+","+addressArea+","+addressDetail+","+zipCode;
//            return "AddressInfoBean{" +
//                    "地区'" + addressArea + '\'' +
//                    ", 地址详情'" + addressDetail + '\'' +
//                    ", 城市:'" + city + '\'' +
//                    ", id=" + id +
//                    ", isDefault=" + isDefault +
//                    ", name='" + name + '\'' +
//                    ", phoneNumber='" + phoneNumber + '\'' +
//                    ", province='" + province + '\'' +
//                    ", zipCode='" + zipCode + '\'' +
//                    '}';
        }

        /**
         * addressArea : 不要告诉你
         * addressDetail : 84639806@qq.com
         * city : 自贡
         * id : 147
         * isDefault : 1
         * name : Billy
         * phoneNumber : 18682036558
         * province : 四川
         * zipCode : 643109
         */

        private String addressArea;
        private String addressDetail;
        private String city;
        private int id;
        private int isDefault;
        private String name;
        private String phoneNumber;
        private String province;
        private String zipCode;

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getAddressArea() {
            return addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public String getCity() {
            return city;
        }

        public int getId() {
            return id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public String getZipCode() {
            return zipCode;
        }
    }

    public static class CheckoutAddupBean {
        @Override
        public String toString() {
            return "邮费"+freight+"总数"+totalCount+"总价"+totalPrice+"总Point"+totalPoint;
//            return "CheckoutAddupBean{" +
//                    "freight=" + freight +
//                    ", totalCount=" + totalCount +
//                    ", totalPoint=" + totalPoint +
//                    ", totalPrice=" + totalPrice +
//                    '}';
        }

        /**
         * freight : 10
         * totalCount : 5
         * totalPoint : 30
         * totalPrice : 450
         */

        private int freight;
        private int totalCount;
        private int totalPoint;
        private int totalPrice;

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public void setTotalPoint(int totalPoint) {
            this.totalPoint = totalPoint;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getFreight() {
            return freight;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public int getTotalPoint() {
            return totalPoint;
        }

        public int getTotalPrice() {
            return totalPrice;
        }
    }

    public static class DeliveryListBean {
        /**
         * des : 周一至周五送货
         * type : 1
         */

        private String des;
        private int type;

        public void setDes(String des) {
            this.des = des;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDes() {
            return des;
        }

        public int getType() {
            return type;
        }
    }

    public static class PaymentListBean {
        /**
         * des : 到付-现金
         * type : 1
         */

        private String des;
        private int type;

        public void setDes(String des) {
            this.des = des;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDes() {
            return des;
        }

        public int getType() {
            return type;
        }
    }

    public static class ProductListBean {
        /**
         * prodNum : 3
         * product : {"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]}
         */

        private int prodNum;
        private ProductBean product;

        public void setProdNum(int prodNum) {
            this.prodNum = prodNum;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public int getProdNum() {
            return prodNum;
        }

        public ProductBean getProduct() {
            return product;
        }

        public static class ProductBean {
            /**
             * id : 1
             * name : 韩版时尚蕾丝裙
             * pic : /images/product/detail/c3.jpg
             * price : 350
             * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]
             */

            private int id;
            private String name;
            private String pic;
            private int price;
            private List<ProductPropertyBean> productProperty;

            public void setId(int id) {
                this.id = id;
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

            public void setProductProperty(List<ProductPropertyBean> productProperty) {
                this.productProperty = productProperty;
            }

            public int getId() {
                return id;
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
            }
        }
    }
}
