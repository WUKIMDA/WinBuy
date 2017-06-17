package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;

/**
 * Created by BUTTON on 2017-06-17.
 */

public class CheckoutPresent extends BaseNetPresenter<CheckoutAllBean>{

    public void upCheckout(String userid,String sku){
        RetrofitUtil.getApiService().checkout(userid,sku).enqueue(mCallBack);
    }
    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(CheckoutAllBean bean) {
        if ("checkOut".equals(bean.getResponse())){
            //提交成功
        }else{
            //提交失败
        }
    }
}
