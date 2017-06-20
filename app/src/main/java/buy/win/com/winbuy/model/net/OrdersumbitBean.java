package buy.win.com.winbuy.model.net;

/**
 * Created by Ziwen on 2017/6/20.
 */

public class OrdersumbitBean extends BaseBean{

    /**
     * orderInfo : {"orderId":"296372","paymentType":1,"price":450}
     * response : orderSubmit
     */

    public OrderInfoBean orderInfo;
    public String response;

    public static class OrderInfoBean {
        /**
         * orderId : 296372
         * paymentType : 1
         * price : 450
         */

        public String orderId;
        public int paymentType;
        public int price;
    }
}
