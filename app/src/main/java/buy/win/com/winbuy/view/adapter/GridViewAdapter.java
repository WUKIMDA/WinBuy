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
import buy.win.com.winbuy.utils.UiUtils;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class GridViewAdapter extends BaseAdapter {

    private final List<CategoryAllBean.CategoryBean> mDatas;
    private Context mContext;

    public GridViewAdapter(Context context, List<CategoryAllBean.CategoryBean> datas) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
//        if (mDatas != null && mDatas.size() > 0){
//            return mDatas.get(position);
//        }
        return null;
    }

    @Override
    public long getItemId(int position) {
//        if (mDatas != null && mDatas.size() > 0){
//            return position;
//        }
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(mContext).inflate(R.layout.category_gridview_item,parent,false);
        CategoryAllBean.CategoryBean bean = mDatas.get(position);
        convertView = View.inflate(mContext, R.layout.category_gridview_item, null);
        ImageView img = (ImageView) convertView.findViewById(R.id.gridview_iv);
        TextView txv = (TextView) convertView.findViewById(R.id.gridview_tv);
        UiUtils.logD(GridViewAdapter.class, bean.getPic());
        UiUtils.logD(GridViewAdapter.class, bean.getName());

        if (bean.getPic() != null && bean.getPic().length() > 0 && bean.getName() != null && bean.getName().length() > 0) {
            Glide.with(mContext).load(Constant.URL_HOST + bean.getPic()).into(img);
            txv.setText(bean.getName());
        }

        return convertView;
    }

}
