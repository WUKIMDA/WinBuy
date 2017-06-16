package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import buy.win.com.winbuy.R;

/**
 * Created by 林特烦 on 2017/6/15.
 */
public class UserCenterAdapter extends BaseAdapter {
    private final ArrayList<UserCenterbean> mUserCentenList;
    private final Context mContext;
    //图片下文字
    private String[] descList = new String[]{"收藏夹", "关注店铺", "足迹"};
    //图片ID数组
    private int[] imgList = new int[]{R.mipmap.user_gridview1, R.mipmap.user_gridview2, R.mipmap.user_gridview3};

    public UserCenterAdapter(Context context) {
        mContext = context;
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
        if(mUserCentenList != null){
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
        UserCenterHolder userCentenHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv_user, null);
            userCentenHolder = new UserCenterHolder();
            userCentenHolder.title = (TextView) convertView.findViewById(R.id.title);
            userCentenHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(userCentenHolder);
        } else {
            userCentenHolder = (UserCenterHolder) convertView.getTag();
        }
        userCentenHolder.title.setText(mUserCentenList.get(position).getTitle());
        userCentenHolder.image.setImageResource(mUserCentenList.get(position).getImageId());
        convertView.setBackgroundColor(Color.WHITE);
        return convertView;
    }
}

class UserCenterHolder {
    public TextView title;
    public ImageView image;
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


