package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import buy.win.com.winbuy.model.net.GoodsBean;
import buy.win.com.winbuy.presenter.GoodsShowPresenter;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.adapter.GoodsRvGridAdapter;
import buy.win.com.winbuy.view.adapter.GoodsRvListAdapter;

/**
 * Created by lenovo on 2017/6/16.
 */

public class GoodsShowActivity extends Activity {
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
    @Bind(R.id.search_orderby)
    LinearLayout mSearchOrderby;
    @Bind(R.id.search_error)
    ImageView mSearchError;
    //    @Bind(R.id.loadmore_list)
//    SwipeRefreshLayout mLoadmoreList;
    private GoodsRvListAdapter mRvListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GoodsRvGridAdapter mRvGridAdapter;
    private GridLayoutManager mGridLayoutManager;
    private Context mContext;
    private String sId;
    private GoodsShowPresenter mGoodsShowPresenter;
    private String mOrderby = "";
    private int mPage = 1;

    private int previousTotal = 0;
    private boolean loading = true;
    private int firstVisibleItem, visibleItemCount, totalItemCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        mContext = this;
        mGoodsShowPresenter = new GoodsShowPresenter(this);
        initData();
        initEvent();
        init();
    }

    private void initData() {
        if (getIntent() != null) {
            sId = getIntent().getStringExtra("sId");
            Log.e("onItemClick: ", sId);
        }
        mOrderby = Constant.SHELVESDOWN;
        loadSearchResult(String.valueOf(mPage), mOrderby);
    }

    public void onGoodsShowSuccess(GoodsBean bean) {
        mSearchOrderby.setVisibility(View.VISIBLE);
        UiUtils.logD(GoodsShowActivity.class, "onSearchSuccessResult: " + bean.getProductList() + "size: " + bean.getProductList().size());
        if (bean.getProductList().size() <= 0) {
            mSearchOrderby.setVisibility(View.GONE);
            Toast.makeText(mContext, "没有该商品", Toast.LENGTH_SHORT).show();
            mSearchEmpty.setVisibility(View.VISIBLE);
        } else {
            mRvListAdapter.setBean(bean.getProductList());
            mRvGridAdapter.setBean(bean.getProductList());
        }
    }

    private void init() {
        mComplex.setSelected(true);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRvList.setLayoutManager(mLinearLayoutManager);
        mRvListAdapter = new GoodsRvListAdapter(mContext);
        mRvList.setAdapter(mRvListAdapter);

        mGridLayoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
        mRvGrid.setLayoutManager(mGridLayoutManager);
        mRvGridAdapter = new GoodsRvGridAdapter(mContext);
        mRvGrid.setAdapter(mRvGridAdapter);
    }


    private void initEvent() {

//        mLoadmoreList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mOrderby = Constant.SHELVESDOWN;
//                loadSearchResult(String.valueOf(mPage), mOrderby);
//            }
//        });

        mRvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                //                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
//                //               滑动状态停止并且剩余两个item时自动加载
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItem + 2 >= mLinearLayoutManager.getItemCount()) {
//                    loadSearchResult(String.valueOf(mPage + 1), mOrderby);
//                }
//            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //                获取加载的最后一个可见视图在适配器的位置。
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading
                        && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
                    mPage++;
                    UiUtils.logD(SearchResultActivity.class, "page = " + mPage + "");
                    loadSearchResult(String.valueOf(mPage), mOrderby);
                    loading = true;
                }
            }
        });
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
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
//                finish();
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
                isPriceUp = true;
                break;
            case R.id.search_tv_sale:
                // 销量排序
                mOrderby = Constant.SALEDOWN;
                loadSearchResult(String.valueOf(mPage), mOrderby);
                mTvSale.setSelected(true);
                mComplex.setSelected(false);
                mPrice.setSelected(false);
                mOther.setSelected(false);
                isPriceUp = true;
                break;
            case R.id.search_rl_price:
                // 价格排序
                mPrice.setSelected(true);
                mComplex.setSelected(false);
                mTvSale.setSelected(false);
                mOther.setSelected(false);
                if (isPriceUp) {
                    mOrderby = Constant.PRICEUP;
                    loadSearchResult(String.valueOf(mPage), mOrderby);
                    mPriceDown.setSelected(true);
                    mPriceUp.setSelected(false);
                    isPriceUp = false;
                } else {
                    mOrderby = Constant.PRICEDOWN;
                    loadSearchResult(String.valueOf(mPage), mOrderby);
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
                isPriceUp = true;
                break;
        }
    }

    private void loadSearchResult(String page, String orderby) {
        mGoodsShowPresenter.loadGoodsShowData(page, Constant.PAGE_NUM, sId, orderby);
        UiUtils.logD(GoodsShowActivity.class, sId + page + Constant.PAGE_NUM + orderby);
    }


    public void onGoodsShowError(String message) {
        mSearchError.setImageResource(R.mipmap.ic_error_page);
        mSearchEmpty.setVisibility(View.VISIBLE);
    }
}
