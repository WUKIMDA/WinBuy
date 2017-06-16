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
import buy.win.com.winbuy.model.net.UserVersionBean;
import buy.win.com.winbuy.presenter.UserAboutPresenter;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class AboutFragment extends Fragment {
    private float version = 1.0f;
    @Bind(R.id.textView)
    TextView mTextView;
    private UserAboutPresenter mAboutPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_user_about, null);
        ButterKnife.bind(this, rootView);
        mAboutPresenter = new UserAboutPresenter(AboutFragment.this);
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
        UserFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, UserFragment.getInstance()).commit();
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
                    UserFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, UserFragment.getInstance()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    public void onSuccess(UserVersionBean bean) {
        UserVersionBean.VersionBean versionBean = bean.version;
        version = versionBean.version;
        mTextView.setText("版本号: v" + version);
    }

    public void onError() {
        mTextView.setText("版本号: v" + version);
    }

    public void onServerBug() {
        mTextView.setText("版本号: v" + version);
    }
}
