package buy.win.com.winbuy.view.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.OrderListAllBean;
import buy.win.com.winbuy.presenter.OrderCanclePresenter;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.activity.OrderDetailActivity;

/**
 * Created by BUTTON on 2017-06-18.
 */

public class OrderListAdapter extends BaseAdapter {


    private List<OrderListAllBean.OrderListBean> mData = new ArrayList<>();

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public OrderListAllBean.OrderListBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderListViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(UiUtils.getContext(), R.layout.item_order_item, null);
            holder = new OrderListViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OrderListViewHolder) convertView.getTag();
        }
        holder.refreshUI(mData.get(position));

        return convertView;
    }

    public void setData(List<OrderListAllBean.OrderListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public class OrderListViewHolder {
//        @Bind(R.id.iv_order_item_seller_logo)
//        ImageView mIvOrderItemSellerLogo;
        @Bind(R.id.tv_order_name)
        TextView mTvOrderName;
        @Bind(R.id.tv_order_type)
        TextView mTvOrderType;
        @Bind(R.id.tv_order_time)
        TextView mTvOrderTime;
        @Bind(R.id.tv_order_orderid)
        TextView mTvOrderOrderid;
        @Bind(R.id.tv_order_money)
        TextView mTvOrderMoney;
        @Bind(R.id.tv_order_item_flag)
        TextView mTvOrderItemFlag;
        private String mOrderId;

        OrderListViewHolder(final View view) {
            ButterKnife.bind(this, view);
            mTvOrderOrderid.setVisibility(View.GONE);
            mTvOrderItemFlag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new OrderCanclePresenter().orderCancle(ShareUtils.getUserId(UiUtils.getContext(),""), mOrderId);
                    view.setVisibility(View.GONE);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进商品详情
                    Intent intent = new Intent(UiUtils.getContext(), OrderDetailActivity.class);
                    intent.putExtra("orderId",mOrderId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    UiUtils.getContext().startActivity(intent);
                }
            });
        }

        public void refreshUI(OrderListAllBean.OrderListBean orderListBean) {
            int flag = orderListBean.getFlag(); //标识1=>未完成订单,可取消 2=>正在处理，可取消 3=>已完成
            //   //订单ID
            mOrderId = orderListBean.getOrderId();
            int price = orderListBean.getPrice();    //订单金额
            String status = orderListBean.getStatus();//    //订单显示状态
            String time = orderListBean.getTime();      //   //下单时间

            //根据订单状态显示和处理按钮
            flagSelect(flag);

            mTvOrderType.setText(status);
            mTvOrderMoney.setText("￥："+price);
            mTvOrderName.setText("订单号"+ mOrderId);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/ MM/ dd HH:mm:ss");
            Date date= new Date(Long.parseLong(time));
            String timeFormat = sdf.format(date);
            mTvOrderTime.setText(timeFormat);


        }

        ///标识1=>未完成订单,可取消 2=>正在处理，可取消 3=>已完成

        public String flagSelect(int flag) {
            switch (flag) {
                case 1:
                    mTvOrderItemFlag.setText("取消订单");
                    mTvOrderItemFlag.setVisibility(View.VISIBLE);
                    return "未完成";
                case 2:
                    mTvOrderItemFlag.setText("取消订单");
                    mTvOrderItemFlag.setVisibility(View.VISIBLE);
                    return "正在处理";
                case 3:
                    mTvOrderItemFlag.setText("已完成");
                    mTvOrderItemFlag.setVisibility(View.GONE);
                    return "已完成";
                default:
                    mTvOrderItemFlag.setVisibility(View.GONE);
                    return "订单异常";
            }

        }

    }

}
