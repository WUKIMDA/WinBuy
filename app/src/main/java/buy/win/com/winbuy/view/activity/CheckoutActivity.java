package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.presenter.CheckoutPresent;

/**
 * Created by Ziwen on 2017/6/18.
 */

public class CheckoutActivity extends Activity{

    private CheckoutPresent mCheckoutPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mCheckoutPresenter = new CheckoutPresent(this);
        initView();
        loadView();
    }

    private void initView() {
        RecyclerView viewById = (RecyclerView) findViewById(R.id.rc_checkout);
    }

    private void loadView() {
        mCheckoutPresenter.upCheckout("20248","1:3:1,2,3,4|2:2:2,3");
    }
    public List<String> mList = new ArrayList<>();
    public void checkOutSuccess(CheckoutAllBean bean) {
        CheckoutAllBean.AddressInfoBean addressInfo = bean.getAddressInfo();
        mList.add(addressInfo.toString());

        CheckoutAllBean.CheckoutAddupBean checkoutAddup = bean.getCheckoutAddup();
        mList.add(checkoutAddup.toString());

        List<String> checkoutProm = bean.getCheckoutProm();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < checkoutProm.size(); i++) {
            sb.append("促销信息"+checkoutProm.get(i));
        }
        mList.add(sb.toString());

        List<CheckoutAllBean.DeliveryListBean> deliveryList = bean.getDeliveryList();
        for (int i = 0; i < deliveryList.size(); i++) {
            CheckoutAllBean.DeliveryListBean deliveryListBean = deliveryList.get(i);
            int type = deliveryListBean.getType();
            String des = deliveryListBean.getDes();
            mList.add("送货类型"+type+""+des);
        }

        List<CheckoutAllBean.PaymentListBean> paymentList = bean.getPaymentList();
        for (int i = 0; i < paymentList.size(); i++) {
            CheckoutAllBean.PaymentListBean paymentListBean = paymentList.get(i);
            int type = paymentListBean.getType();
            String des = paymentListBean.getDes();
            mList.add("支付方式"+type+""+des);
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
    }
}
