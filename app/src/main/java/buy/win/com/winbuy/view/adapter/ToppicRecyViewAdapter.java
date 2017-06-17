package buy.win.com.winbuy.view.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import buy.win.com.winbuy.view.activity.TopicPlistActivity;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class ToppicRecyViewAdapter extends RecyclerView.Adapter {
    private TopPicActivity mTopPicActivity;

    public ToppicRecyViewAdapter(TopPicActivity topPicActivity) {
        mTopPicActivity = topPicActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mTopPicActivity).inflate(R.layout.item_topic_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (mTopicList.size() == 0) {   // TODO: 2017/6/17 注释掉试一下
            return;
        }
        ViewGroup.LayoutParams layoutParams = ((ViewHolder) holder).mIconImageView.getLayoutParams();
        layoutParams.height = (int) ((layoutParams.width *  mTopPicActivity.mTopPicActivityPresenter.mHeighsArr[position] + 0.5f));
        ((ViewHolder) holder).mIconImageView.setLayoutParams(layoutParams);
        ((ViewHolder) holder).setData(position);
    }


    @Override
    public int getItemCount() {
        return mTopicList.size();
    }

    private List<TopPicBean.TopicBean> mTopicList = new ArrayList<>();

    public void setListData(List<TopPicBean.TopicBean> topicList) {
        mTopicList = topicList;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIconImageView;
        private TextView mNameTextView;

        public ViewHolder(View rootView) {
            super(rootView);
            mIconImageView = (ImageView) rootView.findViewById(R.id.iv_topic_img);
            mNameTextView = (TextView) rootView.findViewById(R.id.tv_topic_name);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mTopPicActivity, TopicPlistActivity.class);
                    intent.putExtra("bean",  mTopicList.get(getPosition()));
                    mTopPicActivity.startActivity(intent);
                    Log.e("ToppicRecyViewAdapter", "onClick: " + getPosition());
                }
            });
        }

        public void setData(int position) {
            TopPicBean.TopicBean topicBean = mTopicList.get(position);
            mIconImageView.setImageBitmap(mTopPicActivity.mTopPicActivityPresenter.mBitmapsArr[position]);
            mNameTextView.setText(topicBean.name);
        }
    }
}
