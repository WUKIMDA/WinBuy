package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.presenter.HomePresenter;
import buy.win.com.winbuy.utils.UiUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class HomeFragment  extends Fragment {

    private List<HomeAllBean.HomeTopicBean> mHomeTopic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View rootView = View.inflate(getActivity(), R.layout.fragment_home, null);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomePresenter homePresenter = new HomePresenter();
        homePresenter.loadHomeData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread (HomeAllBean event) {
        mHomeTopic = event.getHomeTopic();
        Log.d(TAG, "onEventMainThread "+"  "+mHomeTopic.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
