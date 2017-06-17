package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.utils.Constant;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<CategoryAllBean.CategoryBean> mDatas;
    private Context mContext;

    public GridViewAdapter(Context context,List<CategoryAllBean.CategoryBean> datas) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mDatas != null){
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(mContext).inflate(R.layout.category_gridview_item,parent,false);
        convertView = View.inflate(mContext,R.layout.category_gridview_item,null);
        ImageView img = (ImageView) convertView.findViewById(R.id.gridview_iv);
        TextView txv = (TextView) convertView.findViewById(R.id.gridview_tv);
        Glide.with(mContext).load(Constant.URL_HOST + mDatas.get(position).getPic()).into(img);
        txv.setText(mDatas.get(position).getName());
        return convertView;
    }

}
