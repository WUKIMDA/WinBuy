package buy.win.com.winbuy.other.xzwtempfile;

import buy.win.com.winbuy.model.net.BrandBeanBean;
import buy.win.com.winbuy.presenter.BaseNetPresenter;
import buy.win.com.winbuy.utils.RetrofitUtil;

/**
 * Created by Ziwen on 2017/6/18.
 */

public class TjppActivityPresenter extends BaseNetPresenter<BrandBeanBean> {
    private TjppActivity mTjppActivity;

    public TjppActivityPresenter(TjppActivity tjppActivity) {
        mTjppActivity = tjppActivity;
    }
    public void loadBrandData() {
        RetrofitUtil.getApiService().getBrand().enqueue(mCallBack);
    }
    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(BrandBeanBean bean) {
        mTjppActivity.onBrandSuccess(bean);
    }
}
