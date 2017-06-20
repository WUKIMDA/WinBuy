package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;

/**
 * Created by 林特烦 on 2017/6/18.
 */

public class SettingLVAdapter extends BaseAdapter {
    private Context mContext;
    private String[] functions = new String[]{"版本检测", "清除缓存"};

    public SettingLVAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return functions.length;
    }

    @Override
    public Object getItem(int position) {
        return functions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_setting_lv, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.setData(position);
        convertView.setBackgroundColor(Color.WHITE);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.imgView)
        ImageView mImgView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            mTitle.setText(functions[position]);
        }
    }
}
