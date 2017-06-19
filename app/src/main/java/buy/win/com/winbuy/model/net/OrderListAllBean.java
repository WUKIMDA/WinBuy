package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-18.
 */

public class OrderListAllBean extends BaseBean {

    /**
     * orderList : [{"flag":3,"orderId":"004205","price":450,"status":"已完成","time":"1497774004287"},{"flag":3,"orderId":"021156","price":450,"status":"已完成","time":"1497774021199"}]
     */

    private List<OrderListBean> orderList;

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public static class OrderListBean {
        /**
         * flag : 3
         * orderId : 004205
         * price : 450
         * status : 已完成
         * time : 1497774004287
         */

        private int flag;
        private String orderId;
        private int price;
        private String status;
        private String time;

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setPrice(int price) {
            this.price = price;
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

        public int getPrice() {
            return price;
        }

        public String getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }
    }
}
