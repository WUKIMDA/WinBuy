package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class FeedbackFragment extends Fragment {
    @Bind(R.id.assess)
    EditText mAssess;
    @Bind(R.id.contact)
    EditText mContact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_more_feedback, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ib_back, R.id.bt_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                MoreFragment.mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, MoreFragment.getInstance()).commit();
                break;
            case R.id.bt_commit:
                String assess = mAssess.getText().toString();
                String contact = mContact.getText().toString();
                if(TextUtils.isEmpty(assess)||TextUtils.isEmpty(contact)){
                    Toast.makeText(getActivity(), "内容不能为空，请仔细检查再提交", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "提交成功，谢谢您的反馈！我们会做得更好", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
}
