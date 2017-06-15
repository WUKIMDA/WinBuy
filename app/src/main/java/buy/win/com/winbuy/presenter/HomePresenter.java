package buy.win.com.winbuy.presenter;

import com.zhouwei.mzbanner.holder.MZHolderCreator;

import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.HomeFragment;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class HomePresenter extends BaseNetPresenter<HomeAllBean>{

    HomeFragment mHomeFragment;

    public HomePresenter(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }

    public void loadHomeData(){
        RetrofitUtil.getApiService().getHomeAllProduct().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        mHomeFragment.onHomeConnectError(message);
    }

    @Override
    public void onServerBug(int code) {
        mHomeFragment.onHomeServerBug(code);
    }

    @Override
    public void onSuccess(HomeAllBean bean) {
        mHomeFragment.onHomeSuccess(bean);
    }
    // 设置数据
    public void bannerSetData() {
        mHomeFragment.mMZBanner.setPages(mHomeFragment.mHomeTopicLists, new MZHolderCreator<HomeFragment.BannerViewHolder>() {
            @Override
            public HomeFragment.BannerViewHolder createViewHolder() {
                return new HomeFragment.BannerViewHolder();
            }
        });
    }

}
