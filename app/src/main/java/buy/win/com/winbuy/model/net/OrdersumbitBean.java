package buy.win.com.winbuy.model.net;

import java.io.Serializable;

/**
 * Created by Ziwen on 2017/6/20.
 */

public class OrdersumbitBean extends BaseBean{

    /**
     * orderInfo : {"orderId":"149739","paymentType":1,"price":450}
     */

    public OrderInfoBean orderInfo;

    public static class OrderInfoBean implements Serializable{
        /**
         * orderId : 149739
         * paymentType : 1
         * price : 450
         */

        public String orderId;
        public int paymentType;
        public int price;
    }
}
