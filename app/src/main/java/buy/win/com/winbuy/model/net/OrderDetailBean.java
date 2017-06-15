package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class OrderDetailBean extends BaseBean {

    private AddressInfoBean addressInfo;
    private CheckoutAddupBean checkoutAddup;
    private DeliveryInfoBean deliveryInfo;
    private InvoiceInfoBean invoiceInfo;
    private OrderInfoBean orderInfo;
    private PaymentInfoBean paymentInfo;
    private List<ProductListBean> productList;
    private List<String> prom;

    public void setAddressInfo(AddressInfoBean addressInfo) {
        this.addressInfo = addressInfo;
    }

    public void setCheckoutAddup(CheckoutAddupBean checkoutAddup) {
        this.checkoutAddup = checkoutAddup;
    }

    public void setDeliveryInfo(DeliveryInfoBean deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public void setInvoiceInfo(InvoiceInfoBean invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public void setPaymentInfo(PaymentInfoBean paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public void setProm(List<String> prom) {
        this.prom = prom;
    }

    public AddressInfoBean getAddressInfo() {
        return addressInfo;
    }

    public CheckoutAddupBean getCheckoutAddup() {
        return checkoutAddup;
    }

    public DeliveryInfoBean getDeliveryInfo() {
        return deliveryInfo;
    }

    public InvoiceInfoBean getInvoiceInfo() {
        return invoiceInfo;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public PaymentInfoBean getPaymentInfo() {
        return paymentInfo;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public List<String> getProm() {
        return prom;
    }

    public static class AddressInfoBean {
        /**
         * addressArea : 洪山区
         * addressDetail : 街道口地铁c口
         * id : 139
         * name : itcast
         */

        private String addressArea;
        private String addressDetail;
        private int id;
        private String name;

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddressArea() {
            return addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class CheckoutAddupBean {
        /**
         * freight : 10
         * totalCount : 5
         * totalPoint : 30
         * totalPrice : 1250
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

    public static class DeliveryInfoBean {
        /**
         * type : 1
         */

        private String type;

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static class InvoiceInfoBean {
        /**
         * invoiceContent : 传智慧播客教育科技有限公司
         * invoiceTitle : 传智慧播客教育科技有限公司
         */

        private String invoiceContent;
        private String invoiceTitle;

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }
    }

    public static class OrderInfoBean {
        /**
         * flag : 3
         * orderId : 098593
         * status : 未处理
         * time : 1449107098604
         */

        private int flag;
        private String orderId;
        private String status;
        private String time;

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getFlag() {
            return flag;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }
    }

    public static class PaymentInfoBean {
        /**
         * type : 1
         */

        private int type;

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    public static class ProductListBean {
        /**
         * prodNum : 3
         * product : {"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"颜色","v":"绿色"}]}
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
             * productProperty : [{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"颜色","v":"绿色"}]
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
                 * id : 0
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
