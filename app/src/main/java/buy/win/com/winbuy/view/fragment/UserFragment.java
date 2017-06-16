package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.view.adapter.UserCenterAdapter;
import buy.win.com.winbuy.view.adapter.UserLVAdapter;

/**
 * Created by 林特烦 on 2017/6/15.
 */

public class UserFragment extends Fragment {
    @Bind(R.id.ll_login)
    LinearLayout mLlLogin;
    @Bind(R.id.username)
    TextView mUsername;
    @Bind(R.id.phone)
    TextView mPhone;
    @Bind(R.id.ll_userinfo)
    LinearLayout mLlUserinfo;
    @Bind(R.id.gridview)
    GridView mGridview;
    @Bind(R.id.listview)
    ListView mListview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_user, null);
        ButterKnife.bind(this, rootView);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        mGridview.setAdapter(new UserCenterAdapter(getActivity(),fragmentManager));
        mListview.setAdapter(new UserLVAdapter(getActivity(),fragmentManager));
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_login)
    public void onClick() {
    }
}
