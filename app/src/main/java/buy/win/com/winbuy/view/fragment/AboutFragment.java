package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.VersionAllBean;
import buy.win.com.winbuy.presenter.MoreAboutPresenter;
import buy.win.com.winbuy.utils.SPUtils;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class AboutFragment extends Fragment {
    @Bind(R.id.textView)
    TextView mTextView;
    private MoreAboutPresenter mAboutPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_more_about, null);
        ButterKnife.bind(this, rootView);
        mAboutPresenter = new MoreAboutPresenter(AboutFragment.this);
        float version = SPUtils.getVersion(getActivity());
        mTextView.setText("版本号: v" + version);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAboutPresenter.loadVersion();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ib_back)
    public void onClick() {
        MoreFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, MoreFragment.getInstance()).commit();
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

    public void onSuccess(VersionAllBean bean) {
        float mVersion = bean.getVersion().getVersion();
        mTextView.setText("版本号: v" + mVersion);
        SPUtils.saveVersion(getActivity(),mVersion);
    }
}
