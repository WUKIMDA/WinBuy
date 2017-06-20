package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.model.net.OrdersumbitBean;
import buy.win.com.winbuy.presenter.CheckoutOrdersumbitPresenter;
import buy.win.com.winbuy.presenter.CheckoutPresent;
import buy.win.com.winbuy.utils.ShareUtils;
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
    private Button mCheckoutSubmit;
    private CheckoutProductListAdapter mCheckoutProductListAdapter;
    private CheckoutOrdersumbitPresenter mCheckoutOrdersumbitPresenter;
    private String mUserId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mCheckoutPresenter = new CheckoutPresent(this);
        mCheckoutOrdersumbitPresenter = new CheckoutOrdersumbitPresenter(this);

        initView();
        loadView();
    }

    private void initView() {
        mRvCheckout = (RecyclerView) findViewById(R.id.rv_checkout);
        mRvCheckout.setLayoutManager(new LinearLayoutManager(this));
        mRvCheckout.setOnClickListener(this);
        mCheckoutAddupFreight = (TextView) findViewById(R.id.checkoutAddup_freight);
        mCheckoutAddupTotalPrice = (TextView) findViewById(R.id.checkoutAddup_totalPrice);
        mCheckoutSubmit = (Button) findViewById(R.id.checkout_submit);
        mCheckoutSubmit.setOnClickListener(this);


    }

    private void loadView() {
        mUserId = ShareUtils.getUserId(this, "20428");
        mCheckoutPresenter.upCheckout(mUserId, "1:3:1,2,3,4|2:2:2,3");
        mCheckoutProductListAdapter = new CheckoutProductListAdapter(this);
        mRvCheckout.setAdapter(mCheckoutProductListAdapter);
    }

    public void checkOutSuccess(CheckoutAllBean bean) {
        parseBean(bean);
    }

    private CheckoutAllBean mCheckoutAllBean;

    private void parseBean(CheckoutAllBean bean) {
        mCheckoutAllBean = bean;
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

        CheckoutAllBean.CheckoutAddupBean checkoutAddup = bean.getCheckoutAddup();
        mCheckoutAddupFreight.setText("共" + checkoutAddup.getFreight() + "件商品");
        mCheckoutAddupTotalPrice.setText("小计: ¥" + checkoutAddup.getTotalPrice());
    }

    private static final String TAG = "CheckoutActivity";


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout_submit:
                String addressId = String.valueOf(mCheckoutAllBean.getAddressInfo().getId());
                String paymentType = String.valueOf(mCheckoutProductListAdapter.mMPaymentList.getCheckedRadioButtonId());
                String deliveryType = String.valueOf(mCheckoutProductListAdapter.mMDeliveryList.getCheckedRadioButtonId());
                int invoiceTypetemp = mCheckoutAllBean.getAddressInfo().getIsDefault();
                String invoiceType;
                if (invoiceTypetemp == 2) {
                    invoiceType = "单位";
                } else {
                    invoiceType = "个人";
                }
                String invoiceTitle = "传智慧播客教育科技有限公司";
                String invoiceContent = "1";

                mCheckoutOrdersumbitPresenter.checkoutOrdersumbit(mUserId, "1:3:1,2,3,4|2:2:2,3", addressId, paymentType, deliveryType, invoiceType, invoiceTitle, invoiceContent);
                break;
        }
    }

    public void ordersumbitSuccess(OrdersumbitBean bean) {
        Intent intent = new Intent(this, OrdersumbitActivity.class);
        intent.putExtra("orderInfo", bean.orderInfo);
        startActivity(intent);
    }
}
