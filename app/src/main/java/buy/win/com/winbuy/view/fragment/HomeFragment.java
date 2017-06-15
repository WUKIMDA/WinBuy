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

    public MZBannerView mMZBanner;
    public List<HomeAllBean.HomeTopicBean> mHomeTopicLists = new ArrayList<>();
    //    private HomePresenter mHomePresenter;
    private View mRootView;
    private HomePresenter mHomePresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = View.inflate(getActivity(), R.layout.fragment_home, null);
        tempBtn(mRootView);
        initBanner(mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomePresenter = new HomePresenter(HomeFragment.this);
        mHomePresenter.loadHomeData();
    }

    private void tempBtn(View rootView) {
        Button tempBtn = (Button) rootView.findViewById(R.id.btn_search);
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initBanner(View view) {
        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
        // 设置页面点击事件
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getActivity(), "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });
        Log.e(TAG, "mHomeTopicListsmHomeTopicListsmHomeTopicLists" + mHomeTopicLists.toString());
    }


    public void onHomeSuccess(HomeAllBean bean) {
        mHomeTopicLists = bean.getHomeTopic();
        mHomeTopicLists.remove(mHomeTopicLists.size() - 1);
        mHomePresenter.bannerSetData();
        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
    }

    public void onHomeConnectError(String message) {
        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
    }


    public void onHomeServerBug(int code) {
        Log.d(TAG, "onHomeServerBug " + code);
        Toast.makeText(getActivity(), "服务器正在修复中", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMZBanner == null) {
            return;
        }
        mMZBanner.start();//开始轮播
    }
}
