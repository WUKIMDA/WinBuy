package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.SearchResultActivity;

/**
 * Created by lenovo on 2017/6/15.
 */

public class SearchPresenter extends BaseNetPresenter<SearchBean> {

    private SearchResultActivity mSearchResultActivity;

    public SearchPresenter(SearchResultActivity searchResultActivity) {
        mSearchResultActivity = searchResultActivity;
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

//    public void loadSearchData(String page, String pageNum, String cId, String orderby) {
//        RetrofitUtil.getApiService().getGoodsProduct(page,pageNum,cId,orderby).enqueue(mCallBack);
//    }

    @Override
    public void onConnectError(String message) {
        mSearchResultActivity.onSearchError(message);
    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(SearchBean bean) {
        mSearchResultActivity.onSearchSuccess(bean);
    }


}
