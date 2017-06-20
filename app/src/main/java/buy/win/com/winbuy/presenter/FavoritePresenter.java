package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.utils.RetrofitUtil;

public class FavoritePresenter extends BaseNetPresenter {


    public void collect(String userId,String pId){
        RetrofitUtil.getApiService().upPidFavorites(userId,pId).enqueue(mCallBack);
    }
    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(Object bean) {

    }
}
