package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.view.activity.GoodsShowActivity;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class CategoryRcvAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADVIEW = 0;
    private static final int TYPE_COMMON = 1;
    private Context mContext;
    private List<CategoryAllBean.CategoryBean> mDatas = new ArrayList<>();
    private int mSelectedId;
    private List<CategoryAllBean.CategoryBean> mRcvDatas = new ArrayList<>();

    public CategoryRcvAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADVIEW;
        }
        return TYPE_COMMON;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewholder = null;
        switch (viewType) {
            case TYPE_HEADVIEW:
                View headView = LayoutInflater.from(mContext).inflate(R.layout.category_rcv_headview, parent, false);
                viewholder = new HeadViewHolder(headView);
                break;
            case TYPE_COMMON:
                View commonView = LayoutInflater.from(mContext).inflate(R.layout.category_rcv_itemview, parent, false);
                viewholder = new CommonViewHolder(commonView);
                break;
        }
        return viewholder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            HeadViewHolder headView = (HeadViewHolder) holder;
            headView.setData(mDatas, mSelectedId);
        } else {
            CommonViewHolder headView = (CommonViewHolder) holder;
            headView.setData(mDatas, mRcvDatas.get(position));
//            Toast.makeText(mContext,mRcvDatas.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        if (mRcvDatas != null) {
            return mRcvDatas.size();
        }
        return 0;
    }

    public void setDatas(List<CategoryAllBean.CategoryBean> datas, int selectedId, List<CategoryAllBean.CategoryBean> rcvDatas) {
        mDatas = datas;
        mSelectedId = selectedId;
        mRcvDatas.clear();
        rcvDatas.add(0,null);
        mRcvDatas.addAll(rcvDatas);
        notifyDataSetChanged();
    }


    class HeadViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.headview)
        ImageView mHeadview;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(List<CategoryAllBean.CategoryBean> data, int id) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getId() == id) {
                    Glide.with(mContext).load(Constant.URL_HOST + data.get(i).getPic()).into(mHeadview);
                }
            }
        }
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.category_rcv_item_tv)
        TextView mCategoryRcvItemTv;
        @Bind(R.id.category_rcv_item_gv)
        GridView mCategoryRcvItemGv;

        CommonViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        public void setData(List<CategoryAllBean.CategoryBean> datas, CategoryAllBean.CategoryBean categoryBean) {
            mCategoryRcvItemTv.setText(categoryBean.getName());

            List<CategoryAllBean.CategoryBean> mGridDatas = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).getParentId() == categoryBean.getId()) {
                    mGridDatas.add(datas.get(i));
                }
            }

            GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, mGridDatas);
            mCategoryRcvItemGv.setAdapter(gridViewAdapter);
            mCategoryRcvItemGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //// TODO: 2017/6/15 0015
                    Intent intent = new Intent(mContext,GoodsShowActivity.class);
                    intent.putExtra("sId","125");
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
