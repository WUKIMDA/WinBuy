package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.FavoriteBean;

/**
 * Created by demo on 2017/6/20.
 */

public class FavoriteAdapter extends BaseAdapter {

    Context mContext;
    //private String mListCount;
    //private List<FavoriteBean.ProductListBean> mProductList;

    public FavoriteAdapter(Context context) {
        mContext = context;
    }

    List<FavoriteBean.ProductListBean> mList = new ArrayList<>();

    public void setList(List<FavoriteBean.ProductListBean> list) {
        this.mList = list;
        notifyDataSetChanged();
        //mListCount = mList.get(0).getListCount();
       //mProductList = mList.get(0).getProductList();
    }

    @Override
    public int getCount() {
        if(mList == null) {
            return 0;
        }

        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {


            convertView = LayoutInflater.from(mContext).inflate(R.layout.favorite_item_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        /*if(mProductList.size() == 0) {
            //返回一张空图片
            View emptyView = LayoutInflater.from(mContext).inflate(R.layout.favorite_empty, null);
            return emptyView;

        }else {*/
            holder.setData(mList.get(position));

        //}

        return convertView;
    }

     class ViewHolder {
        @Bind(R.id.iv)
        ImageView mIv;
        @Bind(R.id.tv_name)
        TextView mTvName;
        @Bind(R.id.price)
        TextView mPrice;
        @Bind(R.id.marketprice)
        TextView mMarketprice;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


         public void setData(FavoriteBean.ProductListBean productListBean) {

             //TODO:设置数据

             mTvName.setText(productListBean.getName());

         }
     }
}
