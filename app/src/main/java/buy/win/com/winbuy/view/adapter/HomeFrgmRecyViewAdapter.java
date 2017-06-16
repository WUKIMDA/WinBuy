package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.model.net.LimitbuyBean;
import buy.win.com.winbuy.utils.Constans;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class HomeFrgmRecyViewAdapter extends RecyclerView.Adapter {
    public static final int TYPE_TITLE = 0; //头部
    public static final int TYPE_SELLER = 1; //商家，普通条目

    private Context mContext;

    public HomeFrgmRecyViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    //头部Item和常规Item,所以需要定义两种类型.接下来还需要定义两种Holder
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_SELLER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_recycler_header, parent, false);
                TitleHolder titleHolder = new TitleHolder(view);
                return titleHolder;
            case TYPE_SELLER:
                View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_home_recycler_normal, parent, false);
                ViewHolder viewHolder = new ViewHolder(rootView);
                return viewHolder;
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.setData();
                break;
            case TYPE_SELLER:
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.setData(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mHomeBottomBeenList == null) {
            return 0;
        }
        return mHomeBottomBeenList.size()+1;    //总长度+1是因为存在titleView
    }

    private List<HomeAllBean.HomeTopicBean> mHomeAllBeenList = new ArrayList<>();

    public void setHomeAllBeenList(List<HomeAllBean.HomeTopicBean> list) {
        mHomeAllBeenList = list;
        notifyDataSetChanged();
    }

    private class TitleHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mLlHomeTop;
        private final MZBannerView mBannerHomeTop;

        public TitleHolder(View view) {
            super(view);
            mLlHomeTop = (LinearLayout) view.findViewById(R.id.ll_home_top);
            mBannerHomeTop = (MZBannerView) view.findViewById(R.id.banner_home_top);
        }

        public void setData() {
            if (mHomeAllBeenList.size() == 0) {
                return;
            }
            mBannerHomeTop.setPages(mHomeAllBeenList, new MZHolderCreator<BannerViewHolder>() {
                @Override
                public BannerViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
        }
    }

    private List<LimitbuyBean.ProductListBean> mHomeBottomBeenList = new ArrayList<>();
    public void setHomeBottomBeenList(List<LimitbuyBean.ProductListBean> productList) {
        mHomeBottomBeenList = productList;
        notifyDataSetChanged();
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mNameTextView;
        private final ImageView mIconImageView;

        public ViewHolder(View rootView) {
            super(rootView);
            mNameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
            mIconImageView = (ImageView) rootView.findViewById(R.id.imageView);
        }

        public void setData(int position) {
            //-1是为了防止索引越界异常,因为前面存在头View
            LimitbuyBean.ProductListBean bean = mHomeBottomBeenList.get(position-1);
            mNameTextView.setText(bean.name);
            Glide.with(mContext).load(Constans.URL_HOST +bean.pic).into(mIconImageView);
        }
    }
    public static class BannerViewHolder implements MZViewHolder<HomeAllBean.HomeTopicBean> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, HomeAllBean.HomeTopicBean homeTopicBean) {
            Glide.with(context).load(Constans.URL_HOST + homeTopicBean.getPic()).into(mImageView);
        }
    }



    private static final int DELAY = 138;
    private int mLastPosition = -1;
    /*public void showItemAnim(final View view, final int position) {
        mContext = view.getContext();
        if (position > mLastPosition) {
            view.setAlpha(0);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.item_right);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            view.setAlpha(1);
                        }


                        @Override
                        public void onAnimationEnd(Animation animation) {}



                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                    view.startAnimation(animation);
                }
            }, DELAY * position);
            mLastPosition = position;
        }
    }*/
}
