package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by Ziwen on 2017/6/18.
 */

public class CheckoutActivity extends Activity implements View.OnClickListener {

    private CheckoutPresent mCheckoutPresenter;
    private TextView mAddressInfo;
    private RadioGroup mCheckoutProm;
    private TextView mPaymentList;
    private TextView mDeliveryList;
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
        mCheckoutProm = (RadioGroup) findViewById(R.id.checkoutProm);
        mCheckoutProm.setOnClickListener(this);
        mPaymentList = (TextView) findViewById(R.id.paymentList);
        mPaymentList.setOnClickListener(this);
        mDeliveryList = (TextView) findViewById(R.id.deliveryList);
        mDeliveryList.setOnClickListener(this);
        mCheckoutAddup = (TextView) findViewById(R.id.checkoutAddup);
        mCheckoutAddup.setOnClickListener(this);
    }

    private void setViewData() {
        Log.e("CheckoutActivity", "setViewData: "+mList.toString() );
        mAddressInfo.setText(mList.get(0));
        mCheckoutAddup.setText(mList.get(1));
        RadioButton prom1 = (RadioButton) mCheckoutProm.findViewById(R.id.checkoutProm1);
        prom1.setText(mList.get(2));
    }

    private void loadView() {
        mCheckoutPresenter.upCheckout("20248", "1:3:1,2,3,4|2:2:2,3");
    }

    public void checkOutSuccess(CheckoutAllBean bean) {
        Log.e("CheckoutActivity", "checkOutSuccess: "+bean.toString() );
        parseBean(bean);
    }

    public List<String> mList = new ArrayList<>();
    private void parseBean(CheckoutAllBean bean) {
        CheckoutAllBean.AddressInfoBean addressInfo = bean.getAddressInfo();
        mList.add(addressInfo.toString());

        CheckoutAllBean.CheckoutAddupBean checkoutAddup = bean.getCheckoutAddup();
        mList.add(checkoutAddup.toString());

        List<String> checkoutProm = bean.getCheckoutProm();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < checkoutProm.size(); i++) {
            sb.append("促销信息" + checkoutProm.get(i));
        }
        mList.add(sb.toString());

        List<CheckoutAllBean.DeliveryListBean> deliveryList = bean.getDeliveryList();
        for (int i = 0; i < deliveryList.size(); i++) {
            CheckoutAllBean.DeliveryListBean deliveryListBean = deliveryList.get(i);
            int type = deliveryListBean.getType();
            String des = deliveryListBean.getDes();
            mList.add("送货类型" + type + "" + des);
        }

        List<CheckoutAllBean.PaymentListBean> paymentList = bean.getPaymentList();
        for (int i = 0; i < paymentList.size(); i++) {
            CheckoutAllBean.PaymentListBean paymentListBean = paymentList.get(i);
            int type = paymentListBean.getType();
            String des = paymentListBean.getDes();
            mList.add("支付方式" + type + "" + des);
        }

        List<CheckoutAllBean.ProductListBean> productList = bean.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            CheckoutAllBean.ProductListBean productListBean = productList.get(i);
            int prodNum = productListBean.getProdNum();
            CheckoutAllBean.ProductListBean.ProductBean product = productListBean.getProduct();
            int id = product.getId();
            String name = product.getName();
            String pic = product.getPic();
            int price = product.getPrice();

        }

        setViewData();
    }

    @Override
    public void onClick(View v) {

    }
}
