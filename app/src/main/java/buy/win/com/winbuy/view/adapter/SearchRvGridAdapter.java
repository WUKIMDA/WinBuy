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
import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.view.activity.CommodityActivity;

/**
 * Created by lenovo on 2017/6/17.
 */

public class SearchRvGridAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SearchBean.ProductListBean> mSearchBean = new ArrayList<>();

    public SearchRvGridAdapter(Context context) {
        mContext = context;
    }

    public void setSearchBean(List<SearchBean.ProductListBean> searchBean) {
        mSearchBean = searchBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View gridView = LayoutInflater.from(mContext).inflate(R.layout.item_search_horizontal, parent, false);
        GridViewHolder gridHolder = new GridViewHolder(gridView);
        return gridHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridViewHolder gridHolder = (GridViewHolder) holder;
        if (mSearchBean != null) {
            gridHolder.setData(mSearchBean.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mSearchBean != null) {
            return mSearchBean.size();
        }
        return 0;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView)
        ImageView mImageView;
        @Bind(R.id.tv_name_h)
        TextView mTvNameH;
        @Bind(R.id.tv_newprice_h)
        TextView mTvNewpriceH;
        @Bind(R.id.tv_oldprice_h)
        TextView mTvOldpriceH;

        GridViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchBean.ProductListBean bean = mSearchBean.get(getPosition());
                    int id = bean.getId();
                    Intent intent = new Intent(mContext,CommodityActivity.class);
                    intent.putExtra("pId", String.valueOf(id));
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(SearchBean.ProductListBean bean) {
            Picasso.with(mContext).load(Constant.URL_HOST + bean.getPic()).into(mImageView);
            mTvNameH.setText(bean.getName());
            mTvNewpriceH.setText("￥" + bean.getPrice() + "");
            mTvOldpriceH.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mTvOldpriceH.setText("￥" + bean.getMarketPrice());
        }
    }
}
