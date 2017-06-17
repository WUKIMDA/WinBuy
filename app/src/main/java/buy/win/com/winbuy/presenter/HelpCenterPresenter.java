package buy.win.com.winbuy.presenter;

import android.os.SystemClock;

import java.util.List;

import buy.win.com.winbuy.model.net.HelpDetailBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.HelpCenterFragment;

/**
 * Created by 林特烦 on 2017/6/17.
 */

public class HelpCenterPresenter extends BaseNetPresenter<HelpDetailBean> {
    private HelpCenterFragment mHelpCenterFragment;

    public HelpCenterPresenter(HelpCenterFragment helpCenterFragment) {
        mHelpCenterFragment = helpCenterFragment;
    }

    public void loadHelpDetail() {
        for (int i = 1; i < 5; i++) {
            RetrofitUtil.getApiService().getHelpDetailProduct(i + "").enqueue(mCallBack);
            SystemClock.sleep(20);
        }
    }

    @Override
    public void onConnectError(String message) {
    }

    @Override
    public void onServerBug(int code) {
    }

    @Override
    public void onSuccess(HelpDetailBean bean) {
        List<HelpDetailBean.HelpDetailListBean> helpDetailList = bean.getHelpDetailList();
        mHelpCenterFragment.onSuccess(helpDetailList);
    }
}
