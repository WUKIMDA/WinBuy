package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.presenter.HomePresenter;
import buy.win.com.winbuy.view.activity.SearchActivity;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class HomeFragment extends Fragment {
    private MZBannerView mMZBanner;
    private List<HomeAllBean.HomeTopicBean> mHomeTopicLists;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_home, null);
        initBanner(rootView);
        tempBtn(rootView);
        return rootView;

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
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });


    }

    public void onHomeSuccess(HomeAllBean bean) {
        mHomeTopicLists = bean.getHomeTopic();
    }

    public void onHomeSercerBug(int code) {

    }

    public void onHomeConnectError(String message) {

    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
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
        mMZBanner.start();//开始轮播
    }
}
