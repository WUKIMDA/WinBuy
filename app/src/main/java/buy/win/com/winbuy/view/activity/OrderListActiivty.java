package buy.win.com.winbuy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.OrderListAllBean;
import buy.win.com.winbuy.presenter.OrderListsPresenter;
import buy.win.com.winbuy.view.adapter.OrderListAdapter;

//type	1/2/3	1=>近一个月订单(改为10分钟)  2=>一个月前订单(改为10分钟)  0=>已取消订单
public class OrderListActiivty extends AppCompatActivity {

    @Bind(R.id.ib_back)
    ImageButton mIbBack;
    @Bind(R.id.listview_order)
    ListView mListviewOrder;
    private String userId;
    private OrderListAdapter mOrderListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orderlist);
        ButterKnife.bind(this);
        OrderListsPresenter orderListsPresenter = new OrderListsPresenter(this);

        userId = "20428";//TODO
//        userId = ShareUtils.getUserId(this, "");
        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(this, "请重新登录", Toast.LENGTH_SHORT).show();
        }
        //访问网络
        orderListsPresenter.loadOrderLists(userId, "1", "0", "10");


        mOrderListAdapter = new OrderListAdapter();
        mListviewOrder.setAdapter(mOrderListAdapter);

    }

    public void onSuccess(OrderListAllBean bean) {
        List<OrderListAllBean.OrderListBean> orderList = bean.getOrderList();

        refreshUI(orderList);


    }

    private void refreshUI(List<OrderListAllBean.OrderListBean> orderList) {
        mOrderListAdapter.setData(orderList);
    }


    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        finish();
    }
}
