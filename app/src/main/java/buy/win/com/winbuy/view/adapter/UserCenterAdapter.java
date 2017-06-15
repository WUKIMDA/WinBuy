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
    private final ArrayList<Picture> pictures;
    private final LayoutInflater inflater;
    //图片下文字
    private String[] desc = new String[]{"收藏夹", "关注店铺", "足迹"};
    //图片ID数组
    private int[] images = new int[]{R.mipmap.user_gridview1, R.mipmap.user_gridview2, R.mipmap.user_gridview3};

    public UserCenterAdapter(Context context) {
        pictures = new ArrayList<Picture>();
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < images.length; i++) {
            Picture picture = new Picture(desc[i], images[i]);
            pictures.add(picture);
        }
    }

    @Override
    public int getCount() {
        if (pictures != null) {
            return pictures.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(pictures != null){
            return pictures.get(position);
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
            convertView = inflater.inflate(R.layout.item_gv_user, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(pictures.get(position).getTitle());
        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        convertView.setBackgroundColor(Color.WHITE);
        return convertView;
    }
}

class ViewHolder {
    public TextView title;
    public ImageView image;
}

class Picture {
    private String title;
    private int imageId;

    public Picture() {
        super();
    }

    public Picture(String title, int imageId) {
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


