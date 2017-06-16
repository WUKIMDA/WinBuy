package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.SearchActivity;

/**
 * Created by lenovo on 2017/6/15.
 */

public class SearchPresenter extends BaseNetPresenter<SearchBean> {

    private SearchActivity mSearchActivity;

    public SearchPresenter(SearchActivity searchActivity) {
        mSearchActivity = searchActivity;
    }

    /**
     * 加载搜索结果
     *
     * @param keyword 搜索关键字// 奶粉 电脑
     * @param page    第几页
     * @param pageNum 每页多少个
     * @param orderby 排序方式
     *                saleDown(销量降序)，
     *                priceUp(价格升序)，priceDown(价格降序)，
     *                commentDown(评价降序)，shelvesDown(上架降序)。
     *                目前只有价格有双向排序，其他都只有降序，其中默认为saleDown
     */
    public void loadSearchData(String keyword, String page, String pageNum, String orderby) {
        RetrofitUtil.getApiService().getSearchProduct(keyword, page, pageNum, orderby).enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        mSearchActivity.onSearchError(message);
    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(SearchBean bean) {
        mSearchActivity.onSearchSuccess(bean);
    }
}