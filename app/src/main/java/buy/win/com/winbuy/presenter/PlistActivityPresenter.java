package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.TopicPlistBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.activity.TopicPlistActivity;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class PlistActivityPresenter extends BaseNetPresenter<TopicPlistBean>  {
    private TopicPlistActivity mTopicPlistActivity;

    public PlistActivityPresenter(TopicPlistActivity topicPlistActivity) {
        mTopicPlistActivity = topicPlistActivity;
    }

    public void loadTopicPlistData(String id) {
        RetrofitUtil.getApiService().getTopicPlist("1", "10",id,"saleDown").enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {
        mTopicPlistActivity.onTopicPlistServerBug(code);
    }

    @Override
    public void onSuccess(TopicPlistBean bean) {
        mTopicPlistActivity.onTopicPlistSuccess(bean);
    }
}
