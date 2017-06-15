package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class CategoryPresenter extends BaseNetPresenter<CategoryAllBean> {

    public void loadCategoryData(){
       RetrofitUtil.getApiService().getCategoryAllProduct().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(CategoryAllBean bean) {
        //TODO
//        setTextTemp(bean.toString());
//        getTextTemp();


    }


}
