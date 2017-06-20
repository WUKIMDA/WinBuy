package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.view.adapter.MoreCenterAdapter;
import buy.win.com.winbuy.view.adapter.MoreLVAdapter;

/**
 * Created by 林特烦 on 2017/6/15.
 */

public class MoreFragment extends Fragment {
    @Bind(R.id.gridview)
    GridView mGridview;
    @Bind(R.id.listview)
    ListView mListview;

    public static FragmentManager mFragmentManager;
    @Bind(R.id.ib_setting)
    ImageButton mIbSetting;


    private MoreFragment() {
    }

    private static final MoreFragment instance = new MoreFragment();

    //防止直接生成一个实例
    public synchronized static MoreFragment getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_more, null);
        ButterKnife.bind(this, rootView);
        mFragmentManager = getActivity().getFragmentManager();
        mGridview.setAdapter(new MoreCenterAdapter(getActivity()));
        mListview.setAdapter(new MoreLVAdapter(getActivity()));
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ib_setting)
    public void onViewClicked() {
        mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, new SettingFragment()).commit();
    }
}
