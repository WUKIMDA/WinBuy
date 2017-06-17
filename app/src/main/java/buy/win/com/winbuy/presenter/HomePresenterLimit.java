package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.LimitbuyBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.HomeFragment;

/**
 * Created by Ziwen on 2017/6/16.
 */

public class HomePresenterLimit extends BaseNetPresenter<LimitbuyBean> {

    HomeFragment mHomeFragment;

    public HomePresenterLimit(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }

    public void loadHomeBottomData() {
        RetrofitUtil.getApiService().getLimitBuy("1", "10").enqueue(mCallBack);
    }
    @Override
    public void onSuccess(LimitbuyBean bean) {
        mHomeFragment.onHomeSuccessLimit(bean);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }
}
