package buy.win.com.winbuy.presenter;

import android.widget.Toast;

import buy.win.com.winbuy.model.net.CartAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.UiUtils;

/**
 * Created by BUTTON on 2017-06-17.
 */

public class AddCartPresenter extends BaseNetPresenter<CartAllBean> {
    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(CartAllBean bean) {
        if ("addCart".equals(bean.getResponse())){
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "添加失败,请登录", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void addCard(String userId, String productId, String productCount, String propertyId) {
        RetrofitUtil.getApiService().addCart(userId, productId, productCount, propertyId).enqueue(mCallBack);

    }
}
