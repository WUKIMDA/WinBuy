package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.OrdersumbitBean;

/**
 * Created by Ziwen on 2017/6/20.
 */

public class OrdersumbitActivity extends Activity {
    private TextView mOrderInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersumbit);
        initView();
        initData();
    }

    private void initView() {
        mOrderInfo = (TextView) findViewById(R.id.orderInfo);
    }


    private void initData() {
        Intent intent = getIntent();
        OrdersumbitBean.OrderInfoBean infoBean = (OrdersumbitBean.OrderInfoBean) intent.getSerializableExtra("orderInfo");
        String text = "";
//        mOrderInfo.setText("订单编号:" + infoBean.orderId);
//        mOrderInfo.setText("订单金额:" + infoBean.price);
        int paymentType = infoBean.paymentType;
        String payType = "";
        switch (paymentType) {
            case 1:
                payType = "到付-现金";
                break;
            case 2:
                payType = "到付-POS机";
                break;
            case 3:
                payType = "支付宝";
                break;
        }
//        mOrderInfo.setText("支付方式:" + payType);
        text += "订单编号:" + infoBean.orderId;
        text += "订单金额:" + infoBean.price;
        text += "支付方式:" + payType;
        mOrderInfo.setText(text);
    }
}
