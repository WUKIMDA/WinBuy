package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HotSearchBean;
import buy.win.com.winbuy.presenter.HotSearchPresenter;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.searchview.mSearchLayout;


/**
 * Created by lenovo on 2017/6/15.
 */

public class SearchActivity extends Activity {
    public mSearchLayout mSearch;
    @Bind(R.id.search_layout)
    buy.win.com.winbuy.view.searchview.mSearchLayout mSearchLayout;
    private Context mContext;
    private HotSearchPresenter mHotSearchPresenter;
    private List<String> mSearchKeywords = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearch = (mSearchLayout) findViewById(R.id.search_layout);
        mHotSearchPresenter = new HotSearchPresenter(this);
        initData();
    }

    protected void initData() {
        // 获取热门搜索字段
        mHotSearchPresenter.loadHotSearch();
        String shareData = "奶粉,奇妮孕妇装,莫施,Bio-oil,快乐宝贝,爱家,Nutrilon";
        List<String> record = Arrays.asList(shareData.split(","));
        //UiUtils.logD(SearchActivity.class,record.toString());

        mSearch.initOldRecord(record);

        mSearch.SetCallBackListener(new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行联网搜索
                mSearch.et_searchtext_search.setText(str);
                mSearch.et_searchtext_search.setSelection(str.length());
                //Toast.makeText(SearchActivity.this, str, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SearchResultActivity.class);
                intent.putExtra("keyword", str);
                startActivity(intent);
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
        UiUtils.logD(SearchActivity.class, "初始化热门搜索: " + mSearchKeywords.toString());
    }

    public void onSearchError(String message) {

    }
}
