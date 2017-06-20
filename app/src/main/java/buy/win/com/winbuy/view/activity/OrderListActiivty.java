package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.OrderListAllBean;
import buy.win.com.winbuy.presenter.OrderListsPresenter;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.view.adapter.OrderListAdapter;

//type	1/2/3	1=>近一个月订单(改为10分钟)  2=>一个月前订单(改为10分钟)  0=>已取消订单
public class OrderListActiivty extends Activity {

    @Bind(R.id.ib_back)
    ImageButton mIbBack;
    @Bind(R.id.listview_order)
    ListView mListviewOrder;
    private String userId;
    private OrderListAdapter mOrderListAdapter;
    private RelativeLayout mPagerEmpty;
    private RelativeLayout mPagerLoading;
    private LinearLayout mMLlOrderlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        ButterKnife.bind(this);
        initView();

//        userId = "20428";
        userId = ShareUtils.getUserId(this, "");
        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(this, "请重新登录", Toast.LENGTH_SHORT).show();
        }

        mOrderListAdapter = new OrderListAdapter();
        mListviewOrder.setAdapter(mOrderListAdapter);

    }

    private void initView() {
        mMLlOrderlist = (LinearLayout) findViewById(R.id.ll_orderlist);
        mPagerEmpty = (RelativeLayout) findViewById(R.id.pager_empty);
        mPagerEmpty.setVisibility(View.GONE);
        mPagerLoading = (RelativeLayout) findViewById(R.id.pager_loading);
        mPagerLoading.setVisibility(View.VISIBLE);
    }

    public void onSuccess(OrderListAllBean bean) {
        mPagerLoading.setVisibility(View.GONE);
        List<OrderListAllBean.OrderListBean> orderList = bean.getOrderList();
        if (orderList == null||orderList.size() <= 0){//没有数据
            mPagerEmpty.setVisibility(View.VISIBLE);
            mMLlOrderlist.setVisibility(View.GONE);
        } else {
            mPagerEmpty.setVisibility(View.GONE);
            mMLlOrderlist.setVisibility(View.VISIBLE);
        }
        refreshUI(orderList);
    }


    private void refreshUI(List<OrderListAllBean.OrderListBean> orderList) {
        mOrderListAdapter.setData(orderList);
    }

    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        OrderListsPresenter orderListsPresenter = new OrderListsPresenter(this);
        //访问网络
        orderListsPresenter.loadOrderLists(userId, "1", "0", "10");
    }



}
