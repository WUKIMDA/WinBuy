package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.SelectCartBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.ShoppingCartFragment;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class ShopCartPresenter extends BaseNetPresenter<SelectCartBean> {

    private ShoppingCartFragment mShoppingCartFragment;

    public ShopCartPresenter(ShoppingCartFragment shoppingCartFragment) {
        super();
        mShoppingCartFragment = shoppingCartFragment;
    }

    public ShopCartPresenter() {
    }

    public void loadShopCartFragment(String userId) {
        RetrofitUtil.getApiService().getSelectCartProduct(userId).enqueue(mCallBack);
    }

    public void deleteSelectCartProduct(String userId, String productId) {
        RetrofitUtil.getApiService().deleteSelectCartProduct(userId, productId).enqueue(mCallBack);
    }

    public void updataSelectCartProduct(String userId, String productId,
                                        String productCount, String propertyId) {
        RetrofitUtil.getApiService().updataSelectCartProduct(userId,productId,productCount,propertyId).enqueue(mCallBack);
    }


    @Override
    public void onConnectError(String message) {
        if (mShoppingCartFragment != null) {
            mShoppingCartFragment.onConnectError(message);
        }
    }

    @Override
    public void onServerBug(int code) {

        if (mShoppingCartFragment != null) {
            mShoppingCartFragment.onServerBug(code);
        }
    }

    @Override
    public void onSuccess(SelectCartBean bean) {

        //删除item
        if (mShoppingCartFragment != null) {
            mShoppingCartFragment.onSuccess(bean);
        }
    }
}
