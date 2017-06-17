package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.view.activity.TopPicActivity;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class ToppicRecyViewAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ToppicRecyViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_home_recycler_normal, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewGroup.LayoutParams layoutParams = ((ViewHolder) holder).mIconImageView.getLayoutParams();
        layoutParams.height = ((TopPicActivity)mContext).heighsArr[position];
        ((ViewHolder) holder).mIconImageView.setLayoutParams(layoutParams);
//            LayoutParams lp = holder.mPriceTextView.getLayoutParams();
//            lp.height = mHeights.get(position);
//
//            holder.mPriceTextView.setLayoutParams(lp);
//            holder.mPriceTextView.setText(mDatas.get(position));
//
//            // 如果设置了回调，则设置点击事件
//            if (mOnItemClickLitener != null) {
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
//                    }
//                });
//
//                holder.itemView.setOnLongClickListener(new OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                        removeData(pos);
//                        return false;
//                    }
//                });
//            }
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    private List<TopPicBean.TopicBean> mTopicList = new ArrayList<>();

    public void setListData(List<TopPicBean.TopicBean> topicList) {
        mTopicList = topicList;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIconImageView;
        private final TextView mNameTextView;
        private TextView mLimitPriceTextView;
        private TextView mPriceTextView;
        private TextView mLeftTimeTextView;

        public ViewHolder(View rootView) {
            super(rootView);
            mIconImageView = (ImageView) rootView.findViewById(R.id.imageView);
            mNameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
            mLimitPriceTextView = (TextView) rootView.findViewById(R.id.tv_home_limitPrice);
            mPriceTextView = (TextView) rootView.findViewById(R.id.tv_home_price);
            mLeftTimeTextView = (TextView) rootView.findViewById(R.id.tv_home_leftTime);
        }

        public void setData(int position) {
            //-1是为了防止索引越界异常,因为前面存在头View
//            mLeftTimeTextView.setText(NumToTime.secToTime(timeArr[position - 1]));
//            LimitbuyBean.ProductListBean bean = mHomeBottomBeenList.get(position - 1);
//            Glide.with(mContext).load(Constans.URL_HOST + bean.pic).crossFade().into(mIconImageView);
//            mNameTextView.setText(bean.name);
//            mLimitPriceTextView.setText("¥" + bean.limitPrice);
//            mPriceTextView.setText("¥" + bean.price);
//            mPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
