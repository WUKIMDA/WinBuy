package buy.win.com.winbuy.presenter;

import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import buy.win.com.winbuy.model.net.OrderCancleBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.UiUtils;

/**
 * Created by BUTTON on 2017-06-19.
 */

public class OrderCanclePresenter extends BaseNetPresenter<OrderCancleBean> {


    @Override
    public void onConnectError(String message) {
    }

    @Override
    public void onServerBug(int code) {
    }

    @Override
    public void onSuccess(OrderCancleBean bean) {
        if ("1533".equals(bean.getError_code())) {
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "请重新登录", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if ("orderCancel".equals(bean.getResponse())) {
            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), "取消成功", Toast.LENGTH_SHORT).show();
                }
            });
            EventBus.getDefault().post(bean);
        }



    }

    public void orderCancle(String userid, String orderId) {
        RetrofitUtil.getApiService().orderCancelService(userid, orderId).enqueue(mCallBack);
    }
}
