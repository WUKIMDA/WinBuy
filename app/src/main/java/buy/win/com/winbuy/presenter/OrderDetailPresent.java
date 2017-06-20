package buy.win.com.winbuy.presenter;

import android.widget.Toast;

import buy.win.com.winbuy.model.net.OrderDetailBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.activity.OrderDetailActivity;

/**
 * Created by BUTTON on 2017-06-18.
 */

public class OrderDetailPresent extends BaseNetPresenter<OrderDetailBean> {
    OrderDetailActivity mOrderDetailActivity;

    public OrderDetailPresent(OrderDetailActivity orderDetailActivity) {
        mOrderDetailActivity = orderDetailActivity;
    }

    //userid
    //orderId
    public void orderDetail(String userid, String orderId) {
        RetrofitUtil.getApiService().getOrderDetailProduct(userid, orderId).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(OrderDetailBean bean) {
        final String response = bean.getResponse();
        if ("orderDetail".equals(response)) {
            System.out.println("获取订单详情成功");
        }
        if ("1538".equals(bean.getError_code())) {
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "没有该订单详情", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }
        mOrderDetailActivity.onSuccess(bean);

    }
}
