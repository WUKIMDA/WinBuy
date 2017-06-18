package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.GoodsBean;
import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.GoodsShowActivity;

/**
 * Created by lenovo on 2017/6/15.
 */

public class GoodsShowPresenter extends BaseNetPresenter<GoodsBean> {

    private GoodsShowActivity mGoodsShowActivity;

    public GoodsShowPresenter(GoodsShowActivity goodsShowActivity) {
        mGoodsShowActivity = goodsShowActivity;
    }

    public void loadGoodsShowData(String page, String pageNum, String cId, String orderby) {
        RetrofitUtil.getApiService().getGoodsProduct(page,pageNum,cId,orderby).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(GoodsBean bean) {
        mGoodsShowActivity.onGoodsShowSuccess(bean);
    }


}
