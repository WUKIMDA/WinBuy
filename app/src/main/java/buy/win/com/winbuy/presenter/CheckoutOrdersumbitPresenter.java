package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.OrdersumbitBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.CheckoutActivity;

/**
 * Created by Ziwen on 2017/6/20.
 */

public class CheckoutOrdersumbitPresenter extends BaseNetPresenter<OrdersumbitBean> {
    private CheckoutActivity mCheckoutActivity;

    public CheckoutOrdersumbitPresenter(CheckoutActivity checkoutActivity) {
        mCheckoutActivity = checkoutActivity;
    }

    public void checkoutOrdersumbit(String userid, String sku){
        RetrofitUtil.getApiService().checkout(userid,sku).enqueue(mCallBack);
    }
    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(OrdersumbitBean bean) {
        mCheckoutActivity.ordersumbitSuccess(bean);
    }
}
