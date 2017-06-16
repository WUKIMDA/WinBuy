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

public class UserLVAdapter extends BaseAdapter {
    private Context mContext;
    private String[] descList = new String[]{"用户反馈", "帮助中心", "关于"};
    private int[] imgList = new int[]{R.mipmap.user_listview1, R.mipmap.user_listview2, R.mipmap.user_listview3};
    private ArrayList<UserLVbean> mUserLVBeanList;

    public UserLVAdapter(Context context) {
        this.mContext = context;
        mUserLVBeanList = new ArrayList<UserLVbean>();
        for (int i = 0; i < imgList.length; i++) {
            UserLVbean userLVbean = new UserLVbean(descList[i], imgList[i]);
            mUserLVBeanList.add(userLVbean);
        }
    }

    @Override
    public int getCount() {
        if(mUserLVBeanList != null){
            return mUserLVBeanList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mUserLVBeanList != null){
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
        UserLVHolder userLVHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_user, null);
            userLVHolder = new UserLVHolder();
            userLVHolder.title = (TextView) convertView.findViewById(R.id.title);
            userLVHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(userLVHolder);
        } else {
            userLVHolder = (UserLVHolder) convertView.getTag();
        }
        userLVHolder.title.setText(mUserLVBeanList.get(position).getTitle());
        userLVHolder.image.setImageResource(mUserLVBeanList.get(position).getImageId());
        convertView.setBackgroundColor(Color.WHITE);
        return convertView;
    }
}

class UserLVHolder {
    public TextView title;
    public ImageView image;
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