package buy.win.com.winbuy.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.OrderCancleBean;
import buy.win.com.winbuy.model.net.OrderDetailBean;
import buy.win.com.winbuy.presenter.OrderCanclePresenter;
import buy.win.com.winbuy.presenter.OrderDetailPresent;

/**
 * Created by BUTTON on 2017-06-18.
 */

public class OrderDetailActivity extends AppCompatActivity {

    @Bind(R.id.ib_back)
    ImageButton mIbBack;
    @Bind(R.id.listview_order)
    ListView mListviewOrder;
    @Bind(R.id.orderDetail)
    FrameLayout mOrderDetail;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;
    private String mUserId;
    private String mOrderId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mListviewOrder.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);

        mUserId = "20428";//TODO
        mOrderId = "098593";//TODO
        //mUserId = ShareUtils.getUserId(this, "");
//        Intent intent = getIntent();
//        if (intent != null) {
//            mOrderId = intent.getStringExtra("orderId");
//        }

        if (TextUtils.isEmpty(mUserId)) {
            Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO 填充FramLayout布局显示数据


    }

    private void loadService() {
        OrderDetailPresent orderDetailPresent = new OrderDetailPresent(this);
        orderDetailPresent.orderDetail(mUserId, mOrderId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        finish();
    }

    public void onSuccess(OrderDetailBean bean) {
        String response = bean.getResponse();
        if ("error".equals(response)) {
            Toast.makeText(getApplicationContext(), "没有该订单详情", Toast.LENGTH_SHORT).show();
            return;
        }


        //订单信息
        OrderDetailBean.OrderInfoBean orderInfo = bean.getOrderInfo();
        int flag = orderInfo.getFlag();
        flagSelect(flag);//根据状态显示或者隐藏--->订单操作按钮
        String orderId = orderInfo.getOrderId();//订单号
        String status = orderInfo.getStatus();//订单状态
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/ MM/ dd HH:mm:ss");
        Date date = new Date(Long.parseLong(orderInfo.getTime()));
        String timeFormat = sdf.format(date);//时间


        //送货地址
        OrderDetailBean.AddressInfoBean addressInfo = bean.getAddressInfo();
        int addressInfoId = addressInfo.getId();//地址簿ID
        String addressArea = addressInfo.getAddressArea();//区
        String addressDetail = addressInfo.getAddressDetail();//具体地址
        String name = addressInfo.getName();//收货人


        //支付方式
        OrderDetailBean.PaymentInfoBean paymentInfo = bean.getPaymentInfo();
        int type = paymentInfo.getType();
        String paymentInfoType = paymentInfoType(type);//支付类型


        ////送货时间
        OrderDetailBean.DeliveryInfoBean deliveryInfo = bean.getDeliveryInfo();
        String deliveryInfoType = deliveryInfoType(deliveryInfo.getType());//送货时间


        //发票信息
        OrderDetailBean.InvoiceInfoBean invoiceInfo = bean.getInvoiceInfo();
        String invoiceContent = invoiceInfo.getInvoiceContent();
        String invoiceTitle = invoiceInfo.getInvoiceTitle();

        //商品列表
        List<OrderDetailBean.ProductListBean> productList = bean.getProductList();
        //TODO:设置给适配器展示
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter();
        orderDetailAdapter.setData(productList);

//        //享受促销信息
//        List<String> prom = bean.getProm();//TODO不要了


        //总计
        OrderDetailBean.CheckoutAddupBean checkoutAddup = bean.getCheckoutAddup();
        int freight = checkoutAddup.getFreight();//运费
        int totalCount = checkoutAddup.getTotalCount();//商品数量总计
        int totalPrice = checkoutAddup.getTotalPrice();// //商品金额总计
        int totalPoint = checkoutAddup.getTotalPoint();//商品积分总计

        //TODO 2017年6月18日20:44:05


        Toast.makeText(getApplicationContext(), "执行完毕", Toast.LENGTH_SHORT).show();


    }


    //点击取消订单按钮
    public void orderCancleClicl() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setMessage("您要取消该订单吗?")

                .setPositiveButton("取消订单", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        new OrderCanclePresenter().orderCancle(mUserId, mOrderId);

                    }
                })
                .setNegativeButton("我不取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(OrderCancleBean event) {
        //操作控件,隐藏按钮,状态修改


    }


    class OrderDetailAdapter extends BaseAdapter {

        private List<OrderDetailBean.ProductListBean> mDataLists = new ArrayList<>();

        @Override
        public int getCount() {
            if (mDataLists != null) {
                return mDataLists.size();
            }
            return 0;
        }

        @Override
        public OrderDetailBean.ProductListBean getItem(int position) {
            return mDataLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TODO 待加入
//            OrderDetailHolder holder = null;
//            //商品item
//            if (convertView == null){
//                //convertView = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.XXXXXXXX,parent,false);
//                holder = new OrderDetailHolder(convertView);
//                convertView.setTag(holder);
//            }else{
//                holder = (OrderDetailHolder)convertView.getTag();
//            }
//            holder.setItemData(mDataLists.get(position));
            return convertView;
        }

        public void setData(List<OrderDetailBean.ProductListBean> data) {
            mDataLists = data;
        }
    }


    // deliveryInfo/ //送货时间/1 => 周一至周五送货 2=> 双休日及公众假期送货 3=> 时间不限，工作日双休日及公众假期均可送货

    public String deliveryInfoType(String type) {
        switch (type) {
            case "1":
                return "周一至周五送货";
            case "2":
                return "双休日及公众假期送货";
            case "3":
                return "工作日双休日及公众假期均可送货";
            default:
                return "异常";
        }
    }

    //paymentInfo //支付方式  //支付类型，1=>货到付款 2=>货到POS机   3=>支付宝(待定)
    public String paymentInfoType(int type) {
        switch (type) {
            case 1:
                return "货到付款";
            case 2:
                return "货到POS机";
            case 3:
                return "支付宝";
            default:
                return "异常";
        }
    }

    ///标识1=>未完成订单,可取消 2=>正在处理，可取消 3=>已完成
    public String flagSelect(int flag) {
        switch (flag) {
            case 1:

                return "未完成";
            case 2:

                return "正在处理";
            case 3:

                return "已完成";
            default:

                return "订单异常";
        }

    }


}
