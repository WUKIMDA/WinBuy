package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import buy.win.com.winbuy.R;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class HistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_user_history, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
