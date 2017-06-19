package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.presenter.CheckoutPresent;
import buy.win.com.winbuy.utils.BeanToString;

/**
 * Created by Ziwen on 2017/6/18.
 */

public class CheckoutActivity extends Activity implements View.OnClickListener {

    private CheckoutPresent mCheckoutPresenter;
    private TextView mAddressInfo;
    private RecyclerView mProductList;
    private RadioGroup mDeliveryList;
    private RadioGroup mPaymentList;
    private TextView mCheckoutAddup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mCheckoutPresenter = new CheckoutPresent(this);
        initView();
        loadView();

    }

    private void initView() {
        mAddressInfo = (TextView) findViewById(R.id.addressInfo);
        mAddressInfo.setOnClickListener(this);
        mProductList = (RecyclerView) findViewById(R.id.productList);
        mProductList.setOnClickListener(this);
        mDeliveryList = (RadioGroup) findViewById(R.id.deliveryList);
        mDeliveryList.setOnClickListener(this);
        mPaymentList = (RadioGroup) findViewById(R.id.paymentList);
        mPaymentList.setOnClickListener(this);
        mCheckoutAddup = (TextView) findViewById(R.id.checkoutAddup);
        mCheckoutAddup.setOnClickListener(this);
    }

    private void setViewData() {
        mAddressInfo.setText(mList.get(0));
        mCheckoutAddup.setText(mList.get(1));

    }

    private void loadView() {
        mCheckoutPresenter.upCheckout("20428", "1:3:1,2,3,4|2:2:2,3");
    }

    public void checkOutSuccess(CheckoutAllBean bean) {
        Log.e("CheckoutActivity", "checkOutSuccess: " + bean.toString());
        parseBean(bean);
    }

    public List<String> mList = new ArrayList<>();

    private void parseBean(CheckoutAllBean bean) {
        // 收货地址
        CheckoutAllBean.AddressInfoBean addressInfo = bean.getAddressInfo();
        mList.add(BeanToString.addressInfoBean2String(addressInfo));

        // 总计
        CheckoutAllBean.CheckoutAddupBean checkoutAddup = bean.getCheckoutAddup();
        mList.add(BeanToString.checkoutAddupBean2String(checkoutAddup));

        // 享受促销信息
        List<String> checkoutProm = bean.getCheckoutProm();
        mList.add(BeanToString.CheckoutProm2String(checkoutProm));

        // 送货时间
        List<CheckoutAllBean.DeliveryListBean> deliveryList = bean.getDeliveryList();
        for (int i = 0; i < deliveryList.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(deliveryList.get(i).getDes());
            mDeliveryList.addView(radioButton);
        }

        // 支付方式
        List<CheckoutAllBean.PaymentListBean> paymentList = bean.getPaymentList();
        for (int i = 0; i < paymentList.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(paymentList.get(i).getDes());
            mPaymentList.addView(radioButton);
        }

        // 商品列表
        List<CheckoutAllBean.ProductListBean> productList = bean.getProductList();
        mList.add("这里以后是商品列表" + productList.toString());

        setViewData();
    }

    @Override
    public void onClick(View v) {

    }
}
