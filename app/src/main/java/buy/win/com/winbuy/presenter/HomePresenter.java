package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class HomePresenter extends BaseNetPresenter<HomeAllBean>{


    public void loadHomeData(){
        RetrofitUtil.getApiService().getHomeAllProduct().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(HomeAllBean bean) {

    }

}
