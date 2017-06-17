package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class HelpCenterLVAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> titleList = new ArrayList<>();

    public HelpCenterLVAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getCount() {
        if(titleList != null) {
            return titleList.size();
        }
        return 0;
    }


    @Override
    public Object getItem(int position) {
        if(titleList!= null) {
            return titleList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_more_helplv, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.setData(position);
        return convertView;
    }

    public void setTitles(List<HelpDetailBean.HelpDetailListBean> helpDetailList) {
        titleList.add(helpDetailList.get(0).getTitle());
        notifyDataSetChanged();
    }


    class ViewHolder {
        @Bind(R.id.textView)
        TextView mTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            mTextView.setText(position + 1 + "   " + titleList.get(position));
        }
    }
}
