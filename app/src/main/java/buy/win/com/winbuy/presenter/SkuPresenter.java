package buy.win.com.winbuy.presenter;

import java.util.List;

import buy.win.com.winbuy.model.net.SelectCartBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.CheckoutActivity;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class SkuPresenter extends BaseNetPresenter<SelectCartBean> {

    private CheckoutActivity mCheckoutActivity;

    public SkuPresenter(CheckoutActivity checkoutActivity) {
        super();
        mCheckoutActivity = checkoutActivity;
    }

    public void loadShopCartFragment(String userId) {
        RetrofitUtil.getApiService().getSelectCartProduct(userId).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        //mCheckoutActivity.onConnectError(message);
    }

    @Override
    public void onServerBug(int code) {

        //mCheckoutActivity.onServerBug(code);
    }

    @Override
    public void onSuccess(SelectCartBean bean) {

        List<SelectCartBean.CartBean> cart = bean.getCart();
        StringBuffer buff = new StringBuffer("");
        for (int i = 0; i < cart.size(); i++) {
            StringBuffer goods = new StringBuffer(cart.get(i).getProductId() + ":" + cart.get(i).getProductCount() + ":" + cart.get(i).getPropertyId() + "|");
            buff.append(goods);
        }
        String sku = buff.deleteCharAt(buff.length()-1).toString();

        //// TODO: 2017/6/20 0020

        mCheckoutActivity.loadView(sku);
    }
}
