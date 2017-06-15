package buy.win.com.winbuy.presenter;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.other.xzwtempfile.AppBean;
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
    public List<AppBean> getApps() {
        List<AppBean> appBeen = new ArrayList<>();
        appBeen.add(new AppBean("Google+", R.mipmap.ic_google_48dp, 4.6f));
        appBeen.add(new AppBean("Gmail", R.mipmap.ic_gmail_48dp, 4.8f));
        appBeen.add(new AppBean("Inbox", R.mipmap.ic_inbox_48dp, 4.5f));
        appBeen.add(new AppBean("Google Keep", R.mipmap.ic_keep_48dp, 4.2f));
        appBeen.add(new AppBean("Google Drive", R.mipmap.ic_drive_48dp, 4.6f));
        appBeen.add(new AppBean("Hangouts", R.mipmap.ic_hangouts_48dp, 3.9f));
        appBeen.add(new AppBean("Google Photos", R.mipmap.ic_photos_48dp, 4.6f));
        appBeen.add(new AppBean("Messenger", R.mipmap.ic_messenger_48dp, 4.2f));
        appBeen.add(new AppBean("Sheets", R.mipmap.ic_sheets_48dp, 4.2f));
        appBeen.add(new AppBean("Slides", R.mipmap.ic_slides_48dp, 4.2f));
        appBeen.add(new AppBean("Docs", R.mipmap.ic_docs_48dp, 4.2f));
        return appBeen;
    }
}
