package buy.win.com.winbuy.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.TopicPlistBean;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.view.activity.TopicPlistActivity;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopicPlistRecyViewAdapter extends RecyclerView.Adapter {
    private TopicPlistActivity mTopicPlistActivity;

    public TopicPlistRecyViewAdapter(TopicPlistActivity topicPlistActivity) {
        mTopicPlistActivity = topicPlistActivity;
    }

    List<TopicPlistBean.ProductListBean> mList;

    public void setListData(List<TopicPlistBean.ProductListBean> productList) {
        mList = productList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mTopicPlistActivity).inflate(R.layout.item_topic_plist_recycler, parent, false);
        PlistViewHolder plistViewHolder = new PlistViewHolder(rootView);
        return plistViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlistViewHolder plistViewHolder = (PlistViewHolder) holder;
        plistViewHolder.setData(position);
    }
    @Override
    public int getItemCount() {
        if (mList==null||mList.size()==0) {
            return 0;
        }
        return mList.size();
    }

    private class PlistViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIoconImageView;
        private TextView mNameTextView;

        public PlistViewHolder(View view) {
            super(view);
            mIoconImageView = (ImageView) view.findViewById(R.id.iv_topiclist_img);
            mNameTextView = (TextView) view.findViewById(R.id.tv_topiclist_name);
        }
        public void setData(int position) {
            TopicPlistBean.ProductListBean bean = mList.get(position);
            Glide.with(mTopicPlistActivity).load(Constant.URL_HOST + bean.pic).crossFade().into(mIoconImageView);
            mNameTextView.setText(bean.name);
        }
    }
}
