package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.presenter.SearchPresenter;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.adapter.SearchRvGridAdapter;
import buy.win.com.winbuy.view.adapter.SearchRvListAdapter;

/**
 * Created by lenovo on 2017/6/16.
 */

public class SearchResultActivity extends Activity {
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.et_searchtext_search)
    EditText mEtSearch;
    @Bind(R.id.iv_pic_type)
    ImageView mIvType;
    @Bind(R.id.search_tv_complex)
    TextView mTvComplex;
    @Bind(R.id.search_iv_complex)
    ImageView mIvComplex;
    @Bind(R.id.search_ll_complex)
    LinearLayout mComplex;
    @Bind(R.id.search_tv_sale)
    TextView mTvSale;
    @Bind(R.id.search_tv_price)
    TextView mTvPrice;
    @Bind(R.id.search_result_price_up)
    ImageView mPriceUp;
    @Bind(R.id.search_result_price_down)
    ImageView mPriceDown;
    @Bind(R.id.search_rl_price)
    RelativeLayout mPrice;
    @Bind(R.id.search_tv_other)
    TextView mTvOther;
    @Bind(R.id.search_ll_other)
    LinearLayout mOther;
    @Bind(R.id.search_recycler_listview)
    RecyclerView mRvList;
    @Bind(R.id.search_recycler_gridview)
    RecyclerView mRvGrid;
    @Bind(R.id.search_empty)
    LinearLayout mSearchEmpty;
    private SearchRvListAdapter mRvListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SearchRvGridAdapter mRvGridAdapter;
    private GridLayoutManager mGridLayoutManager;
    private Context mContext;
    private String mKeyword;
    private SearchPresenter mSearchPresenter;
    private String mOrderby;
    private String mPage;
    private String mPageNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        mContext = this;
        mSearchPresenter = new SearchPresenter(this);
        initData();
        init();
    }

    private void initData() {
        if (getIntent() != null) {
            mKeyword = getIntent().getStringExtra("keyword");
            UiUtils.logD(SearchResultActivity.class, "getExtra: " + mKeyword);
        }

        mPage = "1";
        mPageNum = "6";
        mOrderby = Constant.SALEDOWN;
        mSearchPresenter.loadSearchData(mKeyword, mPage, mPageNum, Constant.SHELVESDOWN);
    }

    public void onSearchSuccess(SearchBean bean) {
//        mSearchBean.addAll(bean.getProductList());
        UiUtils.logD(SearchResultActivity.class, "onSearchSuccessResult: " + bean.getProductList() + "size: " + bean.getProductList().size());
        if (bean.getProductList().size() <= 0) {
            Toast.makeText(mContext, "没有该商品", Toast.LENGTH_SHORT).show();
            mSearchEmpty.setVisibility(View.VISIBLE);
        } else {
            mRvListAdapter.setBean(bean.getProductList());
            mRvGridAdapter.setSearchBean(bean.getProductList());
        }
    }

    private void init() {

        mEtSearch.setText(mKeyword);
        mEtSearch.setCursorVisible(false);
        mComplex.setSelected(true);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRvList.setLayoutManager(mLinearLayoutManager);
        mRvListAdapter = new SearchRvListAdapter(mContext);
//        UiUtils.logD(SearchResultActivity.class,"getRvListAdapter: " + mSearchBean.toString());
        mRvList.setAdapter(mRvListAdapter);

        mGridLayoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
        mRvGrid.setLayoutManager(mGridLayoutManager);
        mRvGridAdapter = new SearchRvGridAdapter(mContext);
        mRvGrid.setAdapter(mRvGridAdapter);

    }

    private boolean isPriceUp = true;
    private boolean isRvList = true;

    @OnClick({R.id.iv_back, R.id.et_searchtext_search, R.id.iv_pic_type, R.id.search_ll_complex, R.id.search_tv_sale, R.id.search_rl_price, R.id.search_ll_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_searchtext_search:
                finish();
                break;
            case R.id.iv_pic_type:
                // 搜索结果显示切换
                if (isRvList) {
                    mIvType.setImageResource(R.mipmap.viewgallery);
                    mRvGrid.setVisibility(View.VISIBLE);
                    mRvList.setVisibility(View.GONE);
                    isRvList = false;
                } else {
                    mIvType.setImageResource(R.mipmap.viewlist);
                    mRvList.setVisibility(View.VISIBLE);
                    mRvGrid.setVisibility(View.GONE);
                    isRvList = true;
                }
                break;
            case R.id.search_ll_complex:
                // 综合排序 弹出popwindow
                // mSearchPresenter.loadSearchData(mKeyword,mPage,mPageNum, Constant.SALEDOWN);
                mComplex.setSelected(true);
                mTvSale.setSelected(false);
                mPrice.setSelected(false);
                mOther.setSelected(false);
                break;
            case R.id.search_tv_sale:
                // 销量排序
                mSearchPresenter.loadSearchData(mKeyword, mPage, mPageNum, Constant.SALEDOWN);
                mTvSale.setSelected(true);
                mComplex.setSelected(false);
                mPrice.setSelected(false);
                mOther.setSelected(false);
                break;
            case R.id.search_rl_price:
                // 价格排序
                mPrice.setSelected(true);
                mComplex.setSelected(false);
                mTvSale.setSelected(false);
                mOther.setSelected(false);
                if (isPriceUp) {
                    mSearchPresenter.loadSearchData(mKeyword, mPage, mPageNum, Constant.PRICEUP);
                    mPriceDown.setSelected(true);
                    mPriceUp.setSelected(false);
                    isPriceUp = false;
                } else {
                    mSearchPresenter.loadSearchData(mKeyword, mPage, mPageNum, Constant.PRICEDOWN);
                    mPriceUp.setSelected(true);
                    mPriceDown.setSelected(false);
                    isPriceUp = true;
                }
                break;
            case R.id.search_ll_other:
                // 其他排序
                mOther.setSelected(true);
                mComplex.setSelected(false);
                mTvSale.setSelected(false);
                mPrice.setSelected(false);
                break;
        }
    }

    public void onSearchError(String message) {

    }

}
