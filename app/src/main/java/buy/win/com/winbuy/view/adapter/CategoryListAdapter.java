package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CategoryAllBean;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class CategoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CategoryAllBean.CategoryBean> mDatas = new ArrayList<>();
    private int mSelection = 0;

    public void setSelection(int selection){
        mSelection = selection;
    }
    public CategoryListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mDatas == null){
            return 0;
        }
        return mDatas.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.category_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setData(mDatas.get(position),position == mSelection);

        return convertView;
    }

    public void setDatas(List<CategoryAllBean.CategoryBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }


    static class ViewHolder {

        @Bind(R.id.category_list_item_tv)
        TextView mCategoryListItemTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(CategoryAllBean.CategoryBean bean,boolean selected) {
            mCategoryListItemTv.setEnabled(!selected);
            mCategoryListItemTv.setText(bean.getName());
        }
    }
}
