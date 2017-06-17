package buy.win.com.winbuy.presenter;

import org.greenrobot.eventbus.EventBus;

import buy.win.com.winbuy.model.net.CommentDataBean;
import buy.win.com.winbuy.utils.RetrofitUtil;

/**
 * Created by BUTTON on 2017-06-16.
 */

public class CommentPresenter extends BaseNetPresenter<CommentDataBean> {




    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(CommentDataBean bean) {
        //方式1
       // System.out.println("方式1" + bean.getComment().toString());

        //方式2
        EventBus.getDefault().post(bean);

    }

    public void loadCommentData(String pId, String page, String pageNum) {
        RetrofitUtil.getApiService().commentLoad(pId, page, pageNum).enqueue(mCallBack);

    }
}
