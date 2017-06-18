package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.utils.UiUtils;

/**
 * Created by lenovo on 2017/6/17.
 */

public class SearchRvListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SearchBean.ProductListBean> mSearchBean = new ArrayList<>();

    public SearchRvListAdapter(Context context) {
        mContext = context;
    }


    public void setBean(List<SearchBean.ProductListBean> bean) {
        mSearchBean = bean;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(mContext).inflate(R.layout.item_search_vertical, parent, false);
        ListViewHolder lvHolder = new ListViewHolder(listView);
        return lvHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListViewHolder lvHolder = (ListViewHolder) holder;
        if (mSearchBean != null) {
            lvHolder.setData(mSearchBean.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mSearchBean != null) {
            return mSearchBean.size();
        }
        return 0;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView mIvIcon;
        @Bind(R.id.tv_name_v)
        TextView mTvNameV;
        @Bind(R.id.tv_newprice_v)
        TextView mTvNewpriceV;
        @Bind(R.id.tv_oldprice_v)
        TextView mTvOldpriceV;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(SearchBean.ProductListBean bean) {
            UiUtils.logD(SearchRvListAdapter.class, bean.toString());
            String pic = bean.getPic();
            String name = bean.getName();
            int price = bean.getPrice();
            int marketPrice = bean.getMarketPrice();

            Picasso.with(mContext).load(Constant.URL_HOST + pic).into(mIvIcon);
            mTvNameV.setText(name);
            mTvNewpriceV.setText("￥" + String.valueOf(price));
            mTvOldpriceV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mTvOldpriceV.setText("￥" + String.valueOf(marketPrice));
        }
    }
}
