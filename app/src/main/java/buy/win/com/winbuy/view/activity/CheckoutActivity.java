package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.presenter.CheckoutPresent;
import buy.win.com.winbuy.view.adapter.CheckoutProductListAdapter;

/**
 * Created by Ziwen on 2017/6/18.
 */

public class CheckoutActivity extends Activity implements View.OnClickListener {

    private CheckoutPresent mCheckoutPresenter;
    private Toolbar mToolbarCheckout;
    private RecyclerView mRvCheckout;
    private TextView mCheckoutAddupFreight;
    private TextView mCheckoutAddupTotalPrice;
    private CheckoutProductListAdapter mCheckoutProductListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mCheckoutPresenter = new CheckoutPresent(this);

        initView();
        loadView();
    }

    private void initView() {
        mRvCheckout = (RecyclerView) findViewById(R.id.rv_checkout);
        mRvCheckout.setLayoutManager(new LinearLayoutManager(this));
        mRvCheckout.setOnClickListener(this);
        mCheckoutAddupFreight = (TextView) findViewById(R.id.checkoutAddup_freight);
        mCheckoutAddupFreight.setOnClickListener(this);
        mCheckoutAddupTotalPrice = (TextView) findViewById(R.id.checkoutAddup_totalPrice);
        mCheckoutAddupTotalPrice.setOnClickListener(this);


    }

    private void loadView() {
        mCheckoutPresenter.upCheckout("20428", "1:3:1,2,3,4|2:2:2,3");
        mCheckoutProductListAdapter = new CheckoutProductListAdapter(this);
        mRvCheckout.setAdapter(mCheckoutProductListAdapter);
    }

    public void checkOutSuccess(CheckoutAllBean bean) {
        parseBean(bean);
    }

    private void parseBean(CheckoutAllBean bean) {

        // 收货地址
        CheckoutAllBean.AddressInfoBean addressInfo = bean.getAddressInfo();
        mCheckoutProductListAdapter.setAddressInfo(addressInfo);

        // 商品详情
        List<CheckoutAllBean.ProductListBean> productList = bean.getProductList();
        mCheckoutProductListAdapter.setProductListBeanList(productList);

        // 送货时间
        List<CheckoutAllBean.DeliveryListBean> deliveryList = bean.getDeliveryList();
        mCheckoutProductListAdapter.setOtherDeliveryListInfo(deliveryList);

        // 支付方式
        List<CheckoutAllBean.PaymentListBean> paymentList = bean.getPaymentList();
        mCheckoutProductListAdapter.setOtherPaymentListInfo(paymentList);
        mCheckoutProductListAdapter.notifyDataSetChanged();
        // 享受促销信息
        List<String> checkoutPromListInfo = bean.getCheckoutProm();
        mCheckoutProductListAdapter.setCheckoutPromListInfo(checkoutPromListInfo);

        mCheckoutProductListAdapter.notifyDataSetChanged();
        Log.e(TAG, "parseBean: parseBeanparseBeanparseBeanparseBean" );
    }

    private static final String TAG = "CheckoutActivity";


    @Override
    public void onClick(View v) {

    }
}
