package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.TopPicActivity;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopPicActivityPresenter extends BaseNetPresenter<TopPicBean>{
    private TopPicActivity mTopPicActivity;

    public TopPicActivityPresenter(TopPicActivity topPicActivity) {
        mTopPicActivity = topPicActivity;
    }
    public void loadTopicData() {
        RetrofitUtil.getApiService().getTopicAllProduct("1","10").enqueue(mCallBack);
    }
    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(TopPicBean bean) {
        mTopPicActivity.onTopicSuccess(bean);
    }
}
