package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.AddressBean;
import buy.win.com.winbuy.presenter.ApiService;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.adapter.AddressListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created eason
 */

public class AddressListActivity extends Activity {
    @Bind(R.id.btn_back)
    ImageView mBtnBack;
    @Bind(R.id.add_address)
    TextView mAddAddress;
    @Bind(R.id.lv_address_list)
    ListView mLvAddressList;
    @Bind(R.id.tv_none)
    TextView mTvNone;
    private List<AddressBean.AddressListBean> mAddressList = new ArrayList<>();
    private AddressListAdapter mAdapter;
    private static final String TAG = "AddressListActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.address_list_activity);
        ButterKnife.bind(this);

       /* mBtnBack = (ImageView) findViewById(R.id.btn_back);
        mAddAddress = (TextView) findViewById(R.id.add_address);
        mLvAddressList = (ListView) findViewById(R.id.lv_address_list);
        mTvNone = (TextView) findViewById(R.id.tv_none);*/

        mAdapter = new AddressListAdapter(this);
        mLvAddressList.setAdapter(mAdapter);
        //getDataOnService();
        if(mAddressList.size() == 0) {
            mTvNone.setVisibility(View.VISIBLE);
            mLvAddressList.setVisibility(View.GONE);
        }else {
            mTvNone.setVisibility(View.GONE);
            mLvAddressList.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataOnService();
        // mAdapter.notifyDataSetChanged();

//        if(mAddressList.size() == 0) {
//            mTvNone.setVisibility(View.VISIBLE);
//            mLvAddressList.setVisibility(View.GONE);
//        }else {
//            mTvNone.setVisibility(View.GONE);
//            mLvAddressList.setVisibility(View.VISIBLE);
//
//        }
    }

    private void getDataOnService() {

        //Log.d(TAG, "precessIntent 意图为null");
        //首先请求网络获取数据 然后打开
        ApiService apkservice = RetrofitUtil.getApiservice2();

        String userId = ShareUtils.getUserId(this, null);
        //TODO
        apkservice.getAddressList(userId).enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if (response.isSuccessful()) {
                    AddressBean body = response.body();
                    Log.e("body", "onResponse "+body.getAddressList());
                  if (!TextUtils.isEmpty(body.getError())) {
                        Toast.makeText(AddressListActivity.this, body.getError(), Toast.LENGTH_SHORT).show();

                      //login
                      Intent intent = new Intent(AddressListActivity.this,LonginAndRegisterActivity.class);
                      startActivity(intent);

                    } else {
                        //成功
                       mAddressList = body.getAddressList();
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess(mAddressList);
                        }
                    });


                    }
                } else {
                    Toast.makeText(AddressListActivity.this, "服务区繁忙", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {

                Toast.makeText(AddressListActivity.this, "请检查您的网络", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void onSuccess(List<AddressBean.AddressListBean> addressList) {

        if (addressList != null) {
            if (addressList.size() == 0) {
                mTvNone.setVisibility(View.VISIBLE);
                mLvAddressList.setVisibility(View.GONE);
            } else {
                mAdapter.setList(mAddressList);
                mTvNone.setVisibility(View.GONE);
                mLvAddressList.setVisibility(View.VISIBLE);

            }
        }
    }



    @OnClick({R.id.btn_back, R.id.add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.add_address:
                Toast.makeText(this, "点击了新增地址", Toast.LENGTH_SHORT).show();
                clickAddress();
                break;
        }
    }

    private void clickAddress() {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivity(intent);
    }


}
