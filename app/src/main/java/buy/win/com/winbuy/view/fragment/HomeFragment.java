package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zhouwei.mzbanner.MZBannerView;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.presenter.HomePresenter;
import buy.win.com.winbuy.view.activity.SearchActivity;
import buy.win.com.winbuy.view.adapter.HomeFrgmRecyViewAdapter;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    public MZBannerView mMZBanner;
    private View mRootView;
    private HomePresenter mHomePresenter;
    private RecyclerView mRv_homeFrgm;
    private HomeFrgmRecyViewAdapter mHomeFrgmRecyViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = View.inflate(getActivity(), R.layout.fragment_home, null);
        mHomePresenter = new HomePresenter(HomeFragment.this);
        tempBtn(mRootView);
        initRecyclerView(mRootView);
        return mRootView;
    }

    private void initRecyclerView(View rootView) {
        mRv_homeFrgm = (RecyclerView) rootView.findViewById(R.id.rv_home_fragment);
        mRv_homeFrgm.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //默认分两列,但是头View需要占两格
        //http://blog.csdn.net/kyleceshen/article/details/50296273
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        mRv_homeFrgm.setLayoutManager(gridLayoutManager);

        mHomeFrgmRecyViewAdapter = new HomeFrgmRecyViewAdapter(getActivity());
        mHomeFrgmRecyViewAdapter.setAppBeanList(mHomePresenter.getApps());
        mRv_homeFrgm.setAdapter(mHomeFrgmRecyViewAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
//    private void initBanner(View view) {
//        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
//        // 设置页面点击事件
//        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
//            @Override
//            public void onPageClick(View view, int position) {
//                Toast.makeText(getActivity(), "click page:" + position, Toast.LENGTH_LONG).show();
//            }
//        });
//        Log.e(TAG, "mHomeTopicListsmHomeTopicListsmHomeTopicLists" + mHomeTopicLists.toString());
//    }


    public void onHomeSuccess(HomeAllBean bean) {
        List<HomeAllBean.HomeTopicBean> mHomeTopicLists = bean.getHomeTopic();
        mHomeTopicLists.remove(mHomeTopicLists.size() - 1);
        mHomeFrgmRecyViewAdapter.setHomeAllBeenList(mHomeTopicLists);
        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
    }
    public void onHomeConnectError(String message) {
        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
    }
    public void onHomeServerBug(int code) {
        Log.d(TAG, "onHomeServerBug " + code);
        Toast.makeText(getActivity(), "服务器正在修复中", Toast.LENGTH_SHORT).show();
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
