package buy.win.com.winbuy.presenter;

import android.util.Log;

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

    public void checkoutOrdersumbit(String userid, String sku, String addressId, String paymentType, String deliveryType, String invoiceType, String invoiceTitle, String invoiceContent) {
        RetrofitUtil.getApiService().ordersumbitService(userid, sku, addressId, paymentType, deliveryType, invoiceType, invoiceTitle, invoiceContent).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        Log.e(TAG, "message: "+message );
    }

    @Override
    public void onServerBug(int code) {
        Log.e(TAG, "code: "+code );
    }

    private static final String TAG = "CheckoutOrdersumbitPres";
    @Override
    public void onSuccess(OrdersumbitBean bean) {
        mCheckoutActivity.ordersumbitSuccess(bean);
    }
}
