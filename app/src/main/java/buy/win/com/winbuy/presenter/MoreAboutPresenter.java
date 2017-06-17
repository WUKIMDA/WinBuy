package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.VersionAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.AboutFragment;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class MoreAboutPresenter extends BaseNetPresenter<VersionAllBean> {
    private AboutFragment mAboutFragment;
    public MoreAboutPresenter(AboutFragment aboutFragment) {
        mAboutFragment = aboutFragment;
    }
    public void loadVersion() {
        RetrofitUtil.getApiService().getVersionProduct().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
    }

    @Override
    public void onServerBug(int code) {
    }

    @Override
    public void onSuccess(VersionAllBean bean) {
        mAboutFragment.onSuccess(bean);
    }
}
