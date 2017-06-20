package buy.win.com.winbuy.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CommentDataBean;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.commodityView.CircleImageView;

/**
 * Created by BUTTON on 2017-06-17.
 */

public class CommentAdapter extends BaseAdapter {

    private List<CommentDataBean.CommentBean> mCommentLists;

    @Override
    public int getCount() {
        if (mCommentLists == null) {
            return 0;
        }
        return mCommentLists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(UiUtils.getContext(), R.layout.item_comment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setItemUI(mCommentLists.get(position));
        return convertView;
    }

    public void setData(CommentDataBean data) {
        mCommentLists = data.getComment();
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @Bind(R.id.ci_icon)
        CircleImageView mCiIcon;
        @Bind(R.id.tv_username)
        TextView mTvUsername;
        @Bind(R.id.tv_time)
        TextView mTvTime;
        @Bind(R.id.tv_content)
        TextView mTvContent;
//        @Bind(R.id.tv_title)
//        TextView mTvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setItemUI(CommentDataBean.CommentBean commentBean) {
            //mTvTitle.setText(commentBean.getTitle());
            mTvContent.setText(commentBean.getContent());
            mCiIcon.setImageResource(R.drawable.ic_launcher);
            mTvTime.setText(commentBean.getTime()+"年");
            mTvUsername.setText(commentBean.getUsername());
        }
    }
}
