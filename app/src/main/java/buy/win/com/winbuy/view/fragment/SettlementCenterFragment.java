package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import buy.win.com.winbuy.R;


/**
 * Created by SeasonAndSun on 2017/6/17.
 */
//activity_settlementCenter
public class SettlementCenterFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.settlement_center, null);
        ((TextView) rootView.findViewById(R.id.tv)).setText("结算中心");

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
