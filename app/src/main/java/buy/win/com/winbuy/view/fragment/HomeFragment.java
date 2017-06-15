package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.presenter.HomePresenter;
import buy.win.com.winbuy.utils.Constans;
import buy.win.com.winbuy.view.activity.SearchActivity;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private MZBannerView mMZBanner;
    private List<HomeAllBean.HomeTopicBean> mHomeTopicLists = new ArrayList<>();
    private HomePresenter mHomePresenter;
    private View mRootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = View.inflate(getActivity(), R.layout.fragment_home, null);
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.loadHomeData();

        tempBtn(mRootView);
        return mRootView;

    }

    private void tempBtn(View rootView) {
        Button tempBtn = (Button)rootView.findViewById(R.id.btn_search);
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomePresenter homePresenter = new HomePresenter(HomeFragment.this);
        homePresenter.loadHomeData();
    }


    private int[] RES = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6};

    private void initBanner(View view) {
        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
        // 设置页面点击事件
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getActivity(), "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < RES.length; i++) {
            list.add(RES[i]);
        }
        Log.e(TAG, "mHomeTopicListsmHomeTopicListsmHomeTopicLists"+mHomeTopicLists.toString() );
        // 设置数据
        mMZBanner.setPages(mHomeTopicLists, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });


    }

    public void onHomeSuccess(HomeAllBean bean) {
        mHomeTopicLists = bean.getHomeTopic();
        mHomeTopicLists.remove(mHomeTopicLists.size()-1);
        initBanner(mRootView);
        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
    }



    public void onHomeConnectError(String message) {
        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
    }


    public void onHomeServerBug(int code) {
        Log.d(TAG, "onHomeServerBug "+ code);
        Toast.makeText(getActivity(), "服务器正在修复中", Toast.LENGTH_SHORT).show();
    }

    public class BannerViewHolder implements MZViewHolder<HomeAllBean.HomeTopicBean> {
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
            Glide.with(context).load(Constans.URL_HOST+homeTopicBean.getPic()).into(mImageView);
        }

//        @Override
//        public void onBind(Context context, int position, Integer data) {
//            // 数据绑定
//           // mImageView.setImageResource();
//            Log.e(TAG, "onBind: ******************************"+data );
//            Glide.with(context).load(Constans.URL_HOST+mHomeTopicLists.get(data).getPic()).into(mImageView);
//        }
    }
    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMZBanner==null) {
            return;
        }
        mMZBanner.start();//开始轮播
    }
}
