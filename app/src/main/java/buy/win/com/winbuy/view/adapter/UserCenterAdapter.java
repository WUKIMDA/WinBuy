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
import buy.win.com.winbuy.view.fragment.AttentionFragment;
import buy.win.com.winbuy.view.fragment.CollectFragment;
import buy.win.com.winbuy.view.fragment.HistoryFragment;
import buy.win.com.winbuy.view.fragment.MoreFragment;

/**
 * Created by 林特烦 on 2017/6/15.
 */
public class UserCenterAdapter extends BaseAdapter {
    private final ArrayList<UserCenterbean> mUserCentenList;
    private final Context mContext;
    private String[] descList = new String[]{"收藏夹", "关注店铺", "足迹"};
    private int[] imgList = new int[]{R.mipmap.user_gridview1, R.mipmap.user_gridview2, R.mipmap.user_gridview3};
    List<Fragment> mFragmentList = new ArrayList<Fragment>();

    private void initFragment() {
        mFragmentList.add(new CollectFragment());
        mFragmentList.add(new AttentionFragment());
        mFragmentList.add(new HistoryFragment());
    }
    public UserCenterAdapter(Context context) {
        mContext = context;
        initFragment();
        mUserCentenList = new ArrayList<UserCenterbean>();
        for (int i = 0; i < imgList.length; i++) {
            UserCenterbean userCenterbean = new UserCenterbean(descList[i], imgList[i]);
            mUserCentenList.add(userCenterbean);
        }
    }

    @Override
    public int getCount() {
        if (mUserCentenList != null) {
            return mUserCentenList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mUserCentenList != null) {
            return mUserCentenList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder userCentenHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv_user, null);
            userCentenHolder = new ViewHolder(convertView);
            convertView.setTag(userCentenHolder);
        } else {
            userCentenHolder = (ViewHolder) convertView.getTag();
        }
        userCentenHolder.setData(position);
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
            mTitle.setText(mUserCentenList.get(position).getTitle());
            mImage.setImageResource(mUserCentenList.get(position).getImageId());
        }
    }

    private void OpenFragmentByPosition(int position) {
        MoreFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, mFragmentList.get(position)).commit();
    }
}

class UserCenterbean {
    private String title;
    private int imageId;

    public UserCenterbean() {
        super();
    }

    public UserCenterbean(String title, int imageId) {
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


