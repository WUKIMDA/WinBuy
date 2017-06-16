package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.presenter.CategoryPresenter;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class CategoryFragment extends Fragment {

    private List<CategoryAllBean.CategoryBean> mCategoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("分类");

        //罗林海测试
        loadData();
        //mCategoryList
        System.out.println("分类数据" + mCategoryList.toString());

        return textView;
    }

    private void loadData() {

        CategoryPresenter categoryPresenter = new CategoryPresenter(this);
        categoryPresenter.loadCategoryData();
    }

    public void onConnectError(String message) {
        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
    }

    public void onServerBug(int code) {
        Toast.makeText(getActivity(), "服务器正在修复中", Toast.LENGTH_SHORT).show();
    }

    public void onSuccess(CategoryAllBean bean) {
        mCategoryList = bean.getCategory();
        System.out.println("分类数据" + mCategoryList.toString());
        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
    }

}
