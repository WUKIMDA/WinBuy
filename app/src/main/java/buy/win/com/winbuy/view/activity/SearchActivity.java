package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HotSearchBean;
import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.presenter.HotSearchPresenter;
import buy.win.com.winbuy.presenter.SearchPresenter;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.searchview.mSearchLayout;


/**
 * Created by lenovo on 2017/6/15.
 */

public class SearchActivity extends Activity {
    public mSearchLayout mSearch;
    private Context mContext;
    private SearchPresenter mSearchPresenter;
    private List<SearchBean.ProductListBean> mProductList = new ArrayList<>();
    private HotSearchPresenter mHotSearchPresenter;
    private List<String> mSearchKeywords = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearch = (mSearchLayout) findViewById(R.id.search_layout);
        mSearchPresenter = new SearchPresenter(this);
        mHotSearchPresenter = new HotSearchPresenter(this);
        initData();

    }

    protected void initData() {
        // 获取热门搜索字段
        mHotSearchPresenter.loadHotSearch();

        String shareData = "澳洲美食,长沙美食,韩国料理,日本料理,舌尖上的中国,意大利餐,山西菜";
        List<String> record = Arrays.asList(shareData.split(","));
        //UiUtils.logD(SearchActivity.class,record.toString());

        mSearch.initOldRecord(record);

        final String page = "0";
        final String pageNum = "10";
        final String orderby = "saleDown";

        mSearch.SetCallBackListener(new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行联网搜索
                Toast.makeText(SearchActivity.this, str, Toast.LENGTH_SHORT).show();
                mSearchPresenter.loadSearchData(str,page,pageNum,orderby);
            }

            @Override
            public void Back() {
                finish();
            }

            @Override
            public void ClearOldData() {

            }

            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {

            }
        });
    }

    public void onHotSuccess(HotSearchBean bean) {
        mSearchKeywords.addAll(bean.getSearchKeywords());
//        if(mSearchKeywords == null) {
//            Toast.makeText(mContext, "网络连接异常", Toast.LENGTH_SHORT).show();
//        }
        mSearch.initHotSearch(mSearchKeywords);
        UiUtils.logD(SearchActivity.class, "初始化热门搜索: " +mSearchKeywords.toString());
    }

    public void onSearchError(String message) {
        //Toast.makeText(mContext, "网络连接异常" + message, Toast.LENGTH_SHORT).show();
    }

    public void onSearchSuccess(SearchBean bean) {
        mProductList.addAll(bean.getProductList());
        UiUtils.logD(SearchActivity.class,mProductList.toString());
    }

}
