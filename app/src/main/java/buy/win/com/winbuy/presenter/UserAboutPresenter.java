package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.UserVersionBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.AboutFragment;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class UserAboutPresenter extends BaseNetPresenter<UserVersionBean> {
    AboutFragment mAboutFragment;
    public UserAboutPresenter(AboutFragment aboutFragment) {
        mAboutFragment = aboutFragment;
    }
    public void loadVersion() {
        RetrofitUtil.getApiService().getVersion().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        mAboutFragment.onError();
    }

    @Override
    public void onServerBug(int code) {
        mAboutFragment.onServerBug();
    }

    @Override
    public void onSuccess(UserVersionBean bean) {
        mAboutFragment.onSuccess(bean);
    }
}
