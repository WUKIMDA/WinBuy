package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import buy.win.com.winbuy.model.net.SelectCartBean;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class ShopCartFragment extends Fragment {

    private List<SelectCartBean.CartBean> mCartLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("购物");
        return textView;
    }

    public void onConnectError(String message) {

    }

    public void onServerBug(int code) {

    }

    public void onSuccess(SelectCartBean bean) {

        mCartLists = bean.getCart();
    }
}
