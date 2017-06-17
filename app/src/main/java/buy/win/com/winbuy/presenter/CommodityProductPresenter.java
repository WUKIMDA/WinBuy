package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.CommodityProductBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.CommodityActivity;

/**
 * Created by BUTTON on 2017-06-16.
 */

public class CommodityProductPresenter extends BaseNetPresenter<CommodityProductBean> {

    CommodityActivity mCommodityActivity;

    public CommodityProductPresenter(CommodityActivity commodityActivity) {
        mCommodityActivity = commodityActivity;
    }

    public void loadCommdityProductData(String pId){
        RetrofitUtil.getApiService().getCommodityProdectData(pId).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
    }

    @Override
    public void onServerBug(int code) {
    }

    @Override
    public void onSuccess(CommodityProductBean bean) {
        mCommodityActivity.onSuccess(bean);
    }
}
