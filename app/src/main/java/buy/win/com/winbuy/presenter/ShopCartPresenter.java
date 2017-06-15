package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.SelectCartBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.ShopCartFragment;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class ShopCartPresenter extends BaseNetPresenter<SelectCartBean> {

    ShopCartFragment mShopCartFragment;

    public void loadShopCartFragment(String userId){
        RetrofitUtil.getApiService().getSelectCartProduct(userId).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        mShopCartFragment.onConnectError(message);
    }

    @Override
    public void onServerBug(int code) {
        mShopCartFragment.onServerBug(code);
    }

    @Override
    public void onSuccess(SelectCartBean bean) {
        mShopCartFragment.onSuccess(bean);
    }
}
