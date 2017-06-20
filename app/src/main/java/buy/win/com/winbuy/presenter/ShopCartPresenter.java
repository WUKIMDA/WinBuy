package buy.win.com.winbuy.presenter;

import android.app.Activity;
import android.content.Intent;

import java.util.List;

import buy.win.com.winbuy.model.net.SelectCartBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.CheckoutActivity;
import buy.win.com.winbuy.view.fragment.ShoppingCartFragment;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class ShopCartPresenter extends BaseNetPresenter<SelectCartBean> {

    private ShoppingCartFragment mShoppingCartFragment;
    private Activity mActivity;

    public ShopCartPresenter(ShoppingCartFragment shoppingCartFragment) {
        super();
        mShoppingCartFragment = shoppingCartFragment;
    }

    public ShopCartPresenter() {
    }

    public ShopCartPresenter(Activity activity) {
        mActivity = activity;
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
        if (mShoppingCartFragment != null) {
            mShoppingCartFragment.onSuccess(bean);
        }else if (mActivity != null){
            List<SelectCartBean.CartBean> cart = bean.getCart();
                StringBuffer buff = new StringBuffer("");
                for (int i = 0; i < cart.size(); i++) {
                    StringBuffer goods = new StringBuffer(cart.get(i).getProductId() + ":" + cart.get(i).getProductCount() + ":" + cart.get(i).getPropertyId() + "|");
                    buff.append(goods);
                }
                String sku = buff.deleteCharAt(buff.length()-1).toString();
            Intent intent = new Intent(mActivity, CheckoutActivity.class);
            intent.putExtra("sku",sku);
            mActivity.startActivity(intent);
    }
}
}
