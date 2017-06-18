package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import buy.win.com.winbuy.model.net.GoodsBean;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.view.activity.CommodityActivity;

/**
 * Created by lenovo on 2017/6/17.
 */

public class GoodsRvGridAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<GoodsBean.ProductListBean> mSearchBean = new ArrayList<>();

    public GoodsRvGridAdapter(Context context) {
        mContext = context;
    }


    public void setBean(List<GoodsBean.ProductListBean> bean) {
        mSearchBean = bean;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(mContext).inflate(R.layout.item_goods_grid, parent, false);
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


    class ListViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.goods_icon_grid)
        ImageView mGoosIconList;
        @Bind(R.id.goods_name_grid)
        TextView mGoodsNameList;
        @Bind(R.id.goods_newprice_grid)
        TextView mGoodsNewpriceList;
        @Bind(R.id.goods_oldprice_grid)
        TextView mGoodsOldpriceList;
        @Bind(R.id.goods_comment_grid)
        TextView mGoodsCommentList;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsBean.ProductListBean bean = mSearchBean.get(getPosition());
                    int id = bean.getId();
                    Intent intent = new Intent(mContext,CommodityActivity.class);
                    intent.putExtra("pId", String.valueOf(id));
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(GoodsBean.ProductListBean bean) {
            String pic = bean.getPic();
            String name = bean.getName();
            int price = bean.getPrice();
            int marketPrice = bean.getMarketPrice();

            Picasso.with(mContext).load(Constant.URL_HOST + pic).into(mGoosIconList);
            mGoodsNameList.setText(name);
            mGoodsNewpriceList.setText("￥" + String.valueOf(price));
            mGoodsOldpriceList.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mGoodsOldpriceList.setText("￥" + String.valueOf(marketPrice));
            mGoodsCommentList.setText(String.valueOf(bean.getCommentCount())+ "条评价");
        }


    }
}
