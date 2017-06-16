package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.HotSearchBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.SearchActivity;

/**
 * Created by lenovo on 2017/6/15.
 */

public class HotSearchPresenter extends BaseNetPresenter<HotSearchBean> {

    private SearchActivity mSearchActivity;

    public HotSearchPresenter(SearchActivity searchActivity) {
        mSearchActivity = searchActivity;
    }

    public void loadHotSearch() {
        RetrofitUtil.getApiService().getHotBrand().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        mSearchActivity.onSearchError(message);
    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(HotSearchBean bean) {
        mSearchActivity.onHotSuccess(bean);
    }
}
