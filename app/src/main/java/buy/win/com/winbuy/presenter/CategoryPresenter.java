package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.view.fragment.CategoryFragment;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class CategoryPresenter extends BaseNetPresenter<CategoryAllBean> {
    CategoryFragment mCategoryFragment;

    public CategoryPresenter(CategoryFragment categoryFragment) {
        mCategoryFragment = categoryFragment;
    }

    public void loadCategoryData(){
       RetrofitUtil.getApiService().getCategoryAllProduct().enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {
        mCategoryFragment.onConnectError(message);
    }

    @Override
    public void onServerBug(int code) {
        mCategoryFragment.onServerBug(code);
    }

    @Override
    public void onSuccess(CategoryAllBean bean) {
        mCategoryFragment.onSuccess(bean);

    }


}
