package buy.win.com.winbuy.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.view.activity.CheckoutActivity;

/**
 * Created by Ziwen on 2017/6/19.
 */

public class CheckoutProductListAdapter extends RecyclerView.Adapter {
    private CheckoutActivity mCheckoutActivity;

    public CheckoutProductListAdapter(CheckoutActivity checkoutActivity) {
        mCheckoutActivity = checkoutActivity;
    }

    private List<CheckoutAllBean.ProductListBean> mList;

    public void setList(List<CheckoutAllBean.ProductListBean> list) {
        this.mList = list;
    }

    public static final int TYPE_ADDRESS = 0;   // 地址信息
    public static final int TYPE_PRODUCT = 1;  // 商品条目
    public static final int TYPE_OTHER = 2;     // 其他信息

//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return TYPE_ADDRESS;
//        }
//        if (mList==null||mList.size()==0) {
//           return position==0?TYPE_ADDRESS:TYPE_OTHER;
//        }else {
//
//            if (position == 0) {
//                return TYPE_SELLER;
//            }
//        }
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
