package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {

        mEtSearch.setCursorVisible(false);


    }

    private void initData() {

        mEtSearch.setText("测试文字");
    }

    private boolean isPriceUp = true;
    @OnClick({R.id.iv_back, R.id.et_searchtext_search, R.id.iv_pic_type,R.id.search_ll_complex, R.id.search_tv_sale, R.id.search_rl_price, R.id.search_ll_other})
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
                break;
            case R.id.search_ll_complex:
                // 综合排序
                mComplex.setSelected(true);
                mTvSale.setSelected(false);
                mPrice.setSelected(false);
                mOther.setSelected(false);
                break;
            case R.id.search_tv_sale:
                // 销量排序
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
                if(isPriceUp) {
                    mPriceDown.setSelected(true);
                    mPriceUp.setSelected(false);
                    isPriceUp = false;
                }else {
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
}
