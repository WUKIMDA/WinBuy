package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.AddressBean;
import buy.win.com.winbuy.presenter.ApiService;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.view.activity.AddressListActivity;
import buy.win.com.winbuy.view.activity.LonginAndRegisterActivity;
import buy.win.com.winbuy.view.customview.MineHomeRelativeLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class UserFragment extends Fragment {


    Context mContext;
    @Bind(R.id.name_user)
    TextView mNameUser;
    @Bind(R.id.like_baby)
    MineHomeRelativeLayout mLikeBaby;
    @Bind(R.id.seller)
    MineHomeRelativeLayout mSeller;
    @Bind(R.id.service)
    MineHomeRelativeLayout mService;
    @Bind(R.id.guess_like)
    MineHomeRelativeLayout mGuessLike;
    @Bind(R.id.address_manager)
    MineHomeRelativeLayout mAddressManager;
    private String mUserName;

    public UserFragment(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.mine_home, null);


        ButterKnife.bind(this, view);
        return view;

        /*TextView textView = new TextView(getActivity());
        textView.setText("我的");
        return textView;*/
    }

    @Override
    public void onResume() {
        super.onResume();

        mUserName = ShareUtils.getUserName(mContext, null);
        if(mUserName != null) {

            mNameUser.setText(mUserName);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.name_user, R.id.like_baby, R.id.seller, R.id.service, R.id.guess_like, R.id.address_manager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name_user:

                //跳转登录或者注册界面
                Intent intent = new Intent(mContext, LonginAndRegisterActivity.class);
                startActivity(intent);

                break;
            case R.id.like_baby:
                break;
            case R.id.seller:
                break;
            case R.id.service:
                break;
            case R.id.guess_like:
                break;
            case R.id.address_manager:
                //点击跳转地址管理activity
                clickAddressManager();

                break;
        }
    }

    private void clickAddressManager() {
        //请求网络数据
        String userId = ShareUtils.getUserId(mContext, null);

        ApiService addressListService = RetrofitUtil.getApiService();

        addressListService.getAddressList(userId).enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if(response.isSuccessful()) {

                    AddressBean body = response.body();
                   /* if(!TextUtils.isEmpty(body.getError())) {
                        Toast.makeText(mContext, "请先登录你的用户名", Toast.LENGTH_SHORT).show();

                    }else {*/
                        //请求数据成功  跳转页面
                        Intent intent = new Intent(mContext,AddressListActivity.class);
                        startActivity(intent);


                   //}
                }else {
                    Toast.makeText(mContext, "服务器繁忙,请稍候重试", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {
                Toast.makeText(mContext, "请检查你的网络", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
