package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HelpDetailBean;
import buy.win.com.winbuy.presenter.HelpCenterPresenter;
import buy.win.com.winbuy.view.adapter.HelpCenterEPLVAdapter;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class HelpCenterFragment extends Fragment {
    @Bind(R.id.eplistview)
    ExpandableListView mExpandableEPListView;
    @Bind(R.id.listview)
    ListView mListview;
    public HelpCenterPresenter mHelpCenterPresenter;
    private HelpCenterEPLVAdapter mHelpCenterLVAdapter;

    @OnClick(R.id.ib_back)
    public void onClick() {
        MoreFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, MoreFragment.getInstance()).commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_more_help, null);
        ButterKnife.bind(this, rootView);
        mHelpCenterPresenter = new HelpCenterPresenter(this);
        mHelpCenterLVAdapter = new HelpCenterEPLVAdapter(getActivity());
        mExpandableEPListView.setAdapter(mHelpCenterLVAdapter);
//        mListview.setAdapter(new HelpCenterLVAdapter(getActivity()));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHelpCenterPresenter.loadHelpDetail();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    MoreFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, MoreFragment.getInstance()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    public void onSuccess(List<HelpDetailBean.HelpDetailListBean> helpDetailList) {
        mHelpCenterLVAdapter.setTitles(helpDetailList);
        mHelpCenterLVAdapter.setContent(helpDetailList);
    }
}
