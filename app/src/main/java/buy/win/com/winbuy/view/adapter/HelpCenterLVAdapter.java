package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HelpDetailBean;

/**
 * Created by 林特烦 on 2017/6/17.
 */

public class HelpCenterLVAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    public HelpCenterLVAdapter(Context context) {
        mContext = context;
    }

    private List<String> titleList = new ArrayList<>();

    public void setTitles(List<HelpDetailBean.HelpDetailListBean> helpDetailList) {
        titleList.add(helpDetailList.get(0).getTitle());
        notifyDataSetChanged();
    }

    private List<String> contentList = new ArrayList<>();

    public void setContent(List<HelpDetailBean.HelpDetailListBean> helpDetailList) {
        contentList.add(helpDetailList.get(0).getContent());
    }

    @Override
    public int getGroupCount() {
        if (titleList != null) {
            return titleList.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (titleList != null) {
            return titleList.get(groupPosition);
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (contentList != null) {
            return contentList.get(groupPosition);
        }
        return null;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_more_helpgroup, null);
        GroupHolder groupHolder = new GroupHolder(convertView);
        groupHolder.setData(groupPosition);
        return convertView;
    }

    class GroupHolder {
        @Bind(R.id.textView)
        TextView mTextView;
        @Bind(R.id.imageView)
        ImageView mImageView;

        GroupHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    RotateAnimation rotateAnimation = new RotateAnimation();
                }
            });
        }

        public void setData(int position) {
            mTextView.setText(position + 1 + "   " + titleList.get(position));
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_more_helpchild, null);
        ChildHolder childHolder = new ChildHolder(convertView);
        childHolder.setData(groupPosition);
        return convertView;
    }

    class ChildHolder {
        @Bind(R.id.textView)
        TextView mTextView;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            mTextView.setText(contentList.get(position));
        }
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
