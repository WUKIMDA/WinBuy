package buy.win.com.winbuy.presenter;

import android.widget.Toast;

import buy.win.com.winbuy.model.net.OrderListAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.activity.OrderListActiivty;

/**
 * Created by BUTTON on 2017-06-18.
 */

public class OrderListsPresenter extends BaseNetPresenter<OrderListAllBean> {

    OrderListActiivty mOrderListActiivty;

    public OrderListsPresenter(OrderListActiivty orderListActiivty) {
        mOrderListActiivty = orderListActiivty;
    }

    public void loadOrderLists(String userid, String type, String page, String pageNum) {
        RetrofitUtil.getApiService().orderLists(userid, type, page, pageNum).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(OrderListAllBean bean) {
        String response = bean.getResponse();
        String error = bean.getError();
        if ("没有登录或则需要重新登录".equals(error)){
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "没有登录或则需要重新登录", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if ("orderList".equals(response)){
            //请求成功不一定有数据
            System.out.println("订单列表请求成功");
        }
        mOrderListActiivty.onSuccess(bean);

    }
}
