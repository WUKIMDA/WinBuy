package buy.win.com.winbuy.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.model.net.LimitbuyBean;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.utils.NumToTime;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.activity.CommodityActivity;
import buy.win.com.winbuy.view.activity.TopPicActivity;
import buy.win.com.winbuy.view.activity.TopicPlistActivity;

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

    /**
     * 6.item的局部赋值
     * notifyItemChanged(int position, Object payload)
     *
     * @param holder   根据之前传入的position,获取每一个的holder.意思就是这个方法调用的次数为mHomeBottomBeenList.size()
     * @param position 前一个方法notifyItemChanged的参数position来到了此处
     * @param payloads 前一个方法notifyItemChanged的参数payload来到了此处
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        // 7.头View布局的判断(本例特殊,本因是不需要进行多余判断,但是为了防止以后忘记或是改需求)
        if (payloads.isEmpty() || position == 0) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            // 8.一般情况操作是获取集合内数据再进行赋值payloads.get(position).我有成员变量,所以就特例了.请立即回过头看"难点1"
            /** 难点2:timeArr[position-1]此处为什么又要-1?
             *  因为传入的position的范围是[1~mHomeBottomBeenList.size()]
             *  当你看到此处说明我已全部讲解完成.
             */
            ((ViewHolder) holder).mLeftTimeTextView.setText(NumToTime.secToTime(timeArr[position - 1]));
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
        return mHomeBottomBeenList.size() + 1;    //总长度+1是因为存在titleView
    }

    private List<HomeAllBean.HomeTopicBean> mHomeAllBeenList = new ArrayList<>();

    public void setHomeAllBeenList(List<HomeAllBean.HomeTopicBean> list) {
        mHomeAllBeenList = list;
        notifyDataSetChanged();
    }

    private class TitleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout mLlHomeTop;
        private final MZBannerView mBannerHomeTop;
        private final TextView mCxkk;
        private final TextView mTjpp;
        private final TextView mXpsj;
        private final TextView mRmdp;
//        private final TextView mCnxh;


        public TitleHolder(View view) {
            super(view);
            mLlHomeTop = (LinearLayout) view.findViewById(R.id.ll_home_top);
            (mCxkk = (TextView) view.findViewById(R.id.toppic)).setOnClickListener(this);
            (mTjpp = (TextView) view.findViewById(R.id.tjpp)).setOnClickListener(this);
            (mXpsj = (TextView) view.findViewById(R.id.xpsj)).setOnClickListener(this);
            (mRmdp = (TextView) view.findViewById(R.id.rmdp)).setOnClickListener(this);
//            (mCnxh = (TextView) view.findViewById(R.id.cnxh)).setOnClickListener(this);
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
//            mBannerHomeTop.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
//                @Override
//                public void onPageClick(View view, int position) {
//                    //Toast.makeText(mContext,"click page:"+position,Toast.LENGTH_LONG).show();
//
//                    Intent intent = new Intent(mContext, TopicPlistActivity.class);
//                    intent.putExtra("bean", mHomeAllBeenList.get(position));
//                    mContext.startActivity(intent);
//
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.toppic:
                    UiUtils.startActivity(mContext, TopPicActivity.class);
                    break;
                case R.id.tjpp:
                    Toast.makeText(mContext, "tjpp", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.xpsj:
                    Intent intent = new Intent(mContext, TopicPlistActivity.class);
                    intent.putExtra("intentKey", "newproductValue");
                    mContext.startActivity(intent);
                    break;
                case R.id.rmdp:
                    Intent intent2 = new Intent(mContext, TopicPlistActivity.class);
                    intent2.putExtra("intentKey", "hotproductValue");
                    mContext.startActivity(intent2);
                    break;
//                case R.id.cnxh:
//                    UiUtils.startActivity(mContext, CheckoutActivity.class);
////                    UiUtils.startActivity(mContext, OrdersumbitActivity.class);
//                    break;
            }
        }
    }

    private List<LimitbuyBean.ProductListBean> mHomeBottomBeenList = new ArrayList<>();

    public void setHomeBottomBeenList(List<LimitbuyBean.ProductListBean> productList) {
        mHomeBottomBeenList = productList;
        notifyDataSetChanged();
        countTime();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIconImageView;
        private final TextView mNameTextView;
        private TextView mLimitPriceTextView;
        private TextView mPriceTextView;
        private TextView mLeftTimeTextView;

        public ViewHolder(View rootView) {
            super(rootView);
            mIconImageView = (ImageView) rootView.findViewById(R.id.imageView);
            mNameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
            mLimitPriceTextView = (TextView) rootView.findViewById(R.id.tv_home_limitPrice);
            mPriceTextView = (TextView) rootView.findViewById(R.id.tv_home_price);
            mLeftTimeTextView = (TextView) rootView.findViewById(R.id.tv_home_leftTime);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    LimitbuyBean.ProductListBean bean = mHomeBottomBeenList.get(position - 1);
                    String s = String.valueOf(bean.id);
                    Intent intent = new Intent(mContext, CommodityActivity.class);
                    intent.putExtra("pId", s);
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(int position) {
            //-1是为了防止索引越界异常,因为前面存在头View
            mLeftTimeTextView.setText(NumToTime.secToTime(timeArr[position - 1]));
            LimitbuyBean.ProductListBean bean = mHomeBottomBeenList.get(position - 1);
            Glide.with(mContext).load(Constant.URL_HOST + bean.pic).crossFade().into(mIconImageView);
            mNameTextView.setText(bean.name);
            mLimitPriceTextView.setText("¥" + bean.limitPrice);
            mPriceTextView.setText("¥" + bean.price);
            mPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    private int[] timeArr;

    /**
     * notifyItemChanged(int position, Object payload)
     * 意思是指定position条目的holder进行数据刷新,此方法执行后会执行{@link #onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads)}
     * 只需要在onBindViewHolder内进行item的局部赋值即可.
     */
    private void countTime() {
        // 1.获取List内所有Bean.leftTime
        timeArr = new int[mHomeBottomBeenList.size()];
        for (int i = 0; i < mHomeBottomBeenList.size(); i++) {
            timeArr[i] = mHomeBottomBeenList.get(i).leftTime;
        }
        // 2.在子线程进行记时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                    // 3.数组内所有的时间值自减1
                    for (int i = 0; i < timeArr.length; i++) {
                        timeArr[i] -= 1;
                        final int finalI = i;
                        // 4.在UI线程进行刷新数据
                        UiUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /** 5.需求:所有的holder都要求刷新时间
                                 * notifyItemChanged(int position, Object payload)
                                 * @param position holder的索引
                                 * @param payload 需要传递的数据(类似于handler内的msg)但是我并没有利用此参数传递数据
                                 * 后续将执行
                                 * {@link #onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads)}
                                 */
                                /** 看完注释8再回来看这里
                                 *  难点1:假如传入j而不是j+1,那么holder将会遍历头View同时忽略最后一个holder,所以+1
                                 *  请继续看难点2
                                 */
                                notifyItemChanged(finalI + 1, timeArr);
                            }
                        });
                    }
                }
            }

        }).start();
    }

    public static class BannerViewHolder implements MZViewHolder<HomeAllBean.HomeTopicBean> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            Log.e("HomeFrgmRecyViewAdapter", "createView: " + mImageView.getWidth());
            Log.e("HomeFrgmRecyViewAdapter", "createView: " + mImageView.getHeight());
            return view;
        }

        @Override
        public void onBind(Context context, int i, HomeAllBean.HomeTopicBean homeTopicBean) {
            Glide.with(context).load(Constant.URL_HOST + homeTopicBean.getPic()).into(mImageView);
            //Bitmap bitmap = UrlToBitmap.getImageFromNet(Constant.URL_HOST + homeTopicBean.getPic());
//            mImageView.setBackground(bitmap);
        }
    }

}
