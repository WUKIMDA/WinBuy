package buy.win.com.winbuy.presenter;

import android.widget.Toast;

import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.activity.CheckoutActivity;

/**
 * Created by BUTTON on 2017-06-17.
 */

public class CheckoutPresent extends BaseNetPresenter<CheckoutAllBean>{
private CheckoutActivity mCheckoutActivity;

    public CheckoutPresent(CheckoutActivity checkoutActivity) {
        mCheckoutActivity = checkoutActivity;
    }

    public void upCheckout(String userid, String sku){
        RetrofitUtil.getApiService().checkout(userid,sku).enqueue(mCallBack);
    }
    @Override
    public void onConnectError(String message) {
        UiUtils.postTask(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UiUtils.getContext(), "onConnectError", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onServerBug(int code) {
        UiUtils.postTask(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UiUtils.getContext(), "onServerBug", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccess(CheckoutAllBean bean) {
        if ("checkOut".equals(bean.getResponse())){
            //提交成功
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                }
            });
            mCheckoutActivity.checkOutSuccess(bean);
        }else{
            //提交失败
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "请登录", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
