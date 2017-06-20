package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.OrdersumbitBean;

/**
 * Created by Ziwen on 2017/6/20.
 */

public class OrdersumbitActivity extends Activity implements View.OnClickListener {
    private TextView mOrderInfo;
    private Button mFukuan;
    private Context mContext;
    private OrdersumbitBean.OrderInfoBean mInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersumbit);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        mOrderInfo = (TextView) findViewById(R.id.orderInfo);
        mFukuan = (Button) findViewById(R.id.fukuan);
        mFukuan.setOnClickListener(this);
    }


    private void initData() {
        Intent intent = getIntent();
        mInfoBean = (OrdersumbitBean.OrderInfoBean) intent.getSerializableExtra("orderInfo");
        if (mInfoBean == null) {
            return;
        }
        String text = "";
//        mOrderInfo.setText("订单编号:" + infoBean.orderId);
//        mOrderInfo.setText("订单金额:" + infoBean.price);
        int paymentType = mInfoBean.paymentType;
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
        text += "订单编号:" + mInfoBean.orderId;
        text += "订单金额:" + mInfoBean.price;
        text += "支付方式:" + payType;
        mOrderInfo.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fukuan:
                showPayDialog();
                break;
        }
    }

    private void showPayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("付款" + mInfoBean.price + "元");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "成功付款" + mInfoBean.price + "元", Toast.LENGTH_SHORT).show();
                setResult(3838438);
                finish();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}
