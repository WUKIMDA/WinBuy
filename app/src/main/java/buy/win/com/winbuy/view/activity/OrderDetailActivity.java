package buy.win.com.winbuy.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.utils.UiUtils;

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
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    private String mUserId;
    private String mOrderId;

    public View detailView;
    public TextView mTvOrderStatus;
    public TextView mTvOrderTime;
    public ImageView mOrderPosition;
    public TextView mTvOrderName;
    public TextView mTvOrderArea;
    public TextView mTvGoodsTotalCount;
    public TextView mTvGoodsDeliverfee;
    public TextView mTvInvoiceTitle;
    public TextView mTvInvoiceContent;
    public TextView mTvPaymentinfo;
    public Button mBtCancelOrder;
    private TextView mTvOrderId;
    private TextView mDeliveryInfoType;
    private TextView mTvCountPrice;
    private ListView mGoodsInfo;
    private OrderDetailAdapter mOrderDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mListviewOrder.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单详情");


//        mUserId = "20428";
//        mOrderId = "098593";
        mUserId = ShareUtils.getUserId(this, "");
        Intent intent = getIntent();
        if (intent != null) {
            mOrderId = intent.getStringExtra("orderId");
        }
        if (TextUtils.isEmpty(mUserId)) {
            Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
            return;
        }

        detailView = LayoutInflater.from(this).inflate(R.layout.activity_order_detail, mScrollView, false);

        mOrderDetail.addView(detailView);

        init();
    }


    private void init() {
        mTvOrderId = (TextView) this.detailView.findViewById(R.id.tv_orderid);
        mTvCountPrice = (TextView) this.detailView.findViewById(R.id.tv_countPrice);
        mDeliveryInfoType = (TextView) this.detailView.findViewById(R.id.tv_order_deliveryInfoType);
        this.mTvOrderStatus = (TextView) this.detailView.findViewById(R.id.tv_order_status);
        this.mTvOrderTime = (TextView) this.detailView.findViewById(R.id.tv_order_time);
        this.mOrderPosition = (ImageView) this.detailView.findViewById(R.id.order_position);
        this.mTvOrderName = (TextView) this.detailView.findViewById(R.id.tv_order_name);
        this.mTvOrderArea = (TextView) this.detailView.findViewById(R.id.tv_order_area);
        this.mTvGoodsTotalCount = (TextView) this.detailView.findViewById(R.id.tv_goods_totalCount);
        this.mTvGoodsDeliverfee = (TextView) this.detailView.findViewById(R.id.tv_goods_deliverfee);
        this.mTvInvoiceTitle = (TextView) this.detailView.findViewById(R.id.tv_invoice_title);
        this.mTvInvoiceContent = (TextView) this.detailView.findViewById(R.id.tv_invoice_content);
        this.mTvPaymentinfo = (TextView) this.detailView.findViewById(R.id.tv_paymentinfo);
        this.mBtCancelOrder = (Button) this.detailView.findViewById(R.id.bt_cancel_order);
        mGoodsInfo = (ListView) this.detailView.findViewById(R.id.lv_goods_info);

        mBtCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCancleClick();
                mOrderDetailAdapter.notifyDataSetChanged();
            }
        });
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
        mTvOrderId.setText("订单号: " + orderId);
        mTvOrderStatus.setText("订单状态: " + status);
        mTvOrderTime.setText(timeFormat);


        //送货地址
        OrderDetailBean.AddressInfoBean addressInfo = bean.getAddressInfo();
        int addressInfoId = addressInfo.getId();//地址簿ID
        String addressArea = addressInfo.getAddressArea();//区
        String addressDetail = addressInfo.getAddressDetail();//具体地址
        String name = addressInfo.getName();//收货人

        mTvOrderName.setText(name);
        mTvOrderArea.setText("送货地址:" + addressArea + "--" + addressDetail);

        //支付方式
        OrderDetailBean.PaymentInfoBean paymentInfo = bean.getPaymentInfo();
        int type = paymentInfo.getType();
        String paymentInfoType = paymentInfoType(type);//支付类型
        mTvPaymentinfo.setText("支付方式:" + paymentInfoType);


        ////送货时间
        OrderDetailBean.DeliveryInfoBean deliveryInfo = bean.getDeliveryInfo();
        String deliveryInfoType = deliveryInfoType(deliveryInfo.getType());//送货时间
        mDeliveryInfoType.setText("送货时间:" + deliveryInfoType);


        //发票信息
        OrderDetailBean.InvoiceInfoBean invoiceInfo = bean.getInvoiceInfo();
        String invoiceContent = invoiceInfo.getInvoiceContent();
        String invoiceTitle = invoiceInfo.getInvoiceTitle();
        mTvInvoiceTitle.setText("发票抬头:" + invoiceTitle);
        mTvInvoiceContent.setText("发票信息:" + invoiceContent);

        //商品列表
        List<OrderDetailBean.ProductListBean> productList = bean.getProductList();

        mOrderDetailAdapter = new OrderDetailAdapter();
        mOrderDetailAdapter.setData(productList);
        mGoodsInfo.setAdapter(mOrderDetailAdapter);
        fixListViewHeight(mGoodsInfo);

//        //享受促销信息
//        List<String> prom = bean.getProm();//不要了

        //总计
        OrderDetailBean.CheckoutAddupBean checkoutAddup = bean.getCheckoutAddup();
        int freight = checkoutAddup.getFreight();//运费
        int totalCount = checkoutAddup.getTotalCount();//商品数量总计
        int totalPrice = checkoutAddup.getTotalPrice();// //商品金额总计
        int totalPoint = checkoutAddup.getTotalPoint();//商品积分总计
        mTvGoodsDeliverfee.setText("运费: " + freight);
        mTvGoodsTotalCount.setText(totalCount + "件商品");
        mTvCountPrice.setText("总金额:￥" + totalPrice);

        Toast.makeText(getApplicationContext(), "执行完毕", Toast.LENGTH_SHORT).show();


    }


    //点击取消订单按钮
    public void orderCancleClick() {
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
        //操作控件,隐藏按钮,状态修改//取消成功:手动修改
        mBtCancelOrder.setVisibility(View.GONE);
        mTvOrderStatus.setText("订单已取消");
    }
    /**解决listView只显示一条数据的bug*/
    public void fixListViewHeight(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int index = 0, len = listAdapter.getCount(); index< len; index++) {
            View listViewItem = listAdapter.getView(index , null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

  public  class OrderDetailAdapter extends BaseAdapter {

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
            OrderDetailHolder holder = null;
//            //商品item
            if (convertView == null) {
                convertView = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.item_order_detail_list, parent, false);
                holder = new OrderDetailHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (OrderDetailHolder) convertView.getTag();
            }

            holder.setItemData(mDataLists.get(position));
            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            int height = layoutParams.height;

            return convertView;
        }

      public void setData(List<OrderDetailBean.ProductListBean> data) {
            mDataLists = data;
        }
    }

    class OrderDetailHolder {
        public View rootView;
        public ImageView mGoosIconList;
        public TextView mGoodsNameList;
        public TextView mGoodsNewprice;
        public TextView mGoodsCommentProperty;
        private OrderDetailBean.ProductListBean mItemData;
        private TextView mGoodsCount;

        public OrderDetailHolder(View rootView) {
            this.rootView = rootView;
            this.mGoosIconList = (ImageView) rootView.findViewById(R.id.goos_icon_list);
            this.mGoodsNameList = (TextView) rootView.findViewById(R.id.goods_name_list);
            this.mGoodsNewprice = (TextView) rootView.findViewById(R.id.goods_newprice);
            this.mGoodsCommentProperty = (TextView) rootView.findViewById(R.id.goods_comment_Property);
            mGoodsCount = (TextView) rootView.findViewById(R.id.goods_count);

        }

        public void setItemData(OrderDetailBean.ProductListBean itemData) {
            mItemData = itemData;
            int prodNum = mItemData.getProdNum();
            mGoodsCount.setText("数量:" + prodNum);
            OrderDetailBean.ProductListBean.ProductBean product = mItemData.getProduct();
            String name = product.getName();
            String picUrl = product.getPic();
            int price = product.getPrice();
            String property = "";
            List<OrderDetailBean.ProductListBean.ProductBean.ProductPropertyBean> productProperty = product.getProductProperty();
            for (int i = 0; i < productProperty.size(); i++) {
                property += productProperty.get(i).getV()+"  ";
            }
            Glide.with(UiUtils.getContext()).load(Constant.URL_HOST + picUrl).into(mGoosIconList);
            mGoodsNameList.setText(name);
            mGoodsNewprice.setText("" + price);
            mGoodsCommentProperty.setText(property);

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
                mBtCancelOrder.setVisibility(View.VISIBLE);
                return "未处理";
            case 2:
                mBtCancelOrder.setVisibility(View.VISIBLE);
                return "正在处理";
            case 3:
                mBtCancelOrder.setVisibility(View.GONE);
                return "已完成";
            default:
                mBtCancelOrder.setVisibility(View.GONE);
                return "订单异常";
        }
    }
}
