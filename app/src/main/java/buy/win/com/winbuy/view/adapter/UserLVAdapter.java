package buy.win.com.winbuy.view.adapter;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
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
import buy.win.com.winbuy.view.fragment.AboutFragment;
import buy.win.com.winbuy.view.fragment.FeedbackFragment;
import buy.win.com.winbuy.view.fragment.HelpCenterFragment;
import buy.win.com.winbuy.view.fragment.MoreFragment;

/**
 * Created by 林特烦 on 2017/6/15.
 */

public class UserLVAdapter extends BaseAdapter {
    private Context mContext;
    private String[] descList = new String[]{"用户反馈", "帮助中心", "关于"};
    private int[] imgList = new int[]{R.mipmap.user_listview1, R.mipmap.user_listview2, R.mipmap.user_listview3};
    private ArrayList<UserLVbean> mUserLVBeanList;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();

    private void initFragment() {
        mFragmentList.add(new FeedbackFragment());
        mFragmentList.add(new HelpCenterFragment());
        mFragmentList.add(new AboutFragment());
    }

    public UserLVAdapter(Context context) {
        this.mContext = context;
        initFragment();
        mUserLVBeanList = new ArrayList<UserLVbean>();
        for (int i = 0; i < imgList.length; i++) {
            UserLVbean userLVbean = new UserLVbean(descList[i], imgList[i]);
            mUserLVBeanList.add(userLVbean);
        }
    }

    @Override
    public int getCount() {
        if (mUserLVBeanList != null) {
            return mUserLVBeanList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mUserLVBeanList != null) {
            return mUserLVBeanList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_user, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(position);
        convertView.setBackgroundColor(Color.WHITE);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.image)
        ImageView mImage;
        @Bind(R.id.title)
        TextView mTitle;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenFragmentByPosition(mPosition);
                }
            });
        }

        public void setData(int position) {
            mPosition = position;
            mTitle.setText(mUserLVBeanList.get(position).getTitle());
            mImage.setImageResource(mUserLVBeanList.get(position).getImageId());
        }
    }

    private void OpenFragmentByPosition(int position) {
        MoreFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, mFragmentList.get(position)).commit();
    }
}


class UserLVbean {
    private String title;
    private int imageId;

    public UserLVbean() {
        super();
    }

    public UserLVbean(String title, int imageId) {
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}