package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.SaveAddressBean;
import buy.win.com.winbuy.presenter.ApiService;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.ShareUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created eason
 */

public class AddAddressActivity extends Activity {

    private static final String TAG = "AddAddressActivity";

    @Bind(R.id.btn_back)
    ImageView mBtnBack;
    @Bind(R.id.tv_save)
    TextView mTvSave;
    @Bind(R.id.rl_title)
    RelativeLayout mRlTitle;
    @Bind(R.id.mine)
    EditText mMine;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.address)
    TextView mAddress;
    @Bind(R.id.detailsAddress)
    EditText mDetailsAddress;
    private CityPicker mCityPicker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.address_add_activity);
        ButterKnife.bind(this);



    }




    @OnClick({R.id.btn_back, R.id.tv_save, R.id.mine, R.id.phone, R.id.address, R.id.detailsAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_save:

                clickSave();
                break;
            case R.id.mine:
                break;
            case R.id.phone:
                break;
            case R.id.address:
                //Toast.makeText(this, "diank ", Toast.LENGTH_SHORT).show();
                selectCity();
                break;
            case R.id.detailsAddress:
                break;

        }
    }

    private void selectCity() {

        mCityPicker = new CityPicker.Builder(AddAddressActivity.this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#234Dfa")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        mCityPicker.show();



        mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];

                mAddress.setText(province+city+district);
            }
            @Override
            public void onCancel() {
                Toast.makeText(AddAddressActivity.this, "已取消", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clickSave() {

        if (TextUtils.isEmpty(mMine.getText().toString())
                || TextUtils.isEmpty(mPhone.getText().toString())
                || TextUtils.isEmpty(mAddress.getText().toString())
                || TextUtils.isEmpty(mDetailsAddress.getText().toString()) ){

            Toast.makeText(AddAddressActivity.this, "请完整填写您的信息", Toast.LENGTH_SHORT).show();
        } else {

            //请求网络
            //Toast.makeText(AddAddressActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
           String mine = mMine.getText().toString().trim();
            String phone = mPhone.getText().toString().trim();
            String address = mAddress.getText().toString().trim();//
            String detailsAddress = mDetailsAddress.getText().toString().trim();
            //String jieguo = str.substring(str.indexOf("第")+1,str.indexOf("号"));
            //String province = address.substring(0,address.indexOf("省"));
            //String city = address.substring(address.indexOf("省")+1,address.indexOf("市"));
            //String addressArea = address.substring(address.indexOf("市")+1,address.indexOf("区"));



            //RetrofitUtils.getApiservice2()
            ApiService apkservice = RetrofitUtil.getApiservice2();

            Log.e("body", "clickSave "+ShareUtils.getUserId(this,null));

            apkservice.saveAddress(ShareUtils.getUserId(this,null),mine,phone,address,"武汉市","洪山区",detailsAddress,"643109","1")
            //apkservice.saveAddress(ShareUtils.getUserId(this,null),"您的世界","18071811314","武汉市","武汉市","洪山区","我韩式","643109","1")

                    .enqueue(new Callback<SaveAddressBean>() {
                @Override
                public void onResponse(Call<SaveAddressBean> call, Response<SaveAddressBean> response) {
                    if(response.isSuccessful()) {

                        SaveAddressBean body = response.body();

                        if(!TextUtils.isEmpty(body.getError())) {

                            Toast.makeText(AddAddressActivity.this, body.getError(), Toast.LENGTH_SHORT).show();
                        }else {

                            List<SaveAddressBean.AddressListBean> addressList = response.body().getAddressList();
                            //Log.e("body", "onResponse "+addressList.toString());

                            finish();

                        }

                    }else {
                        //返回数据失败 404 500 505 话费
                        Toast.makeText(AddAddressActivity.this, "服务器正在维修中,请稍候", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SaveAddressBean> call, Throwable t) {
                    Log.d(TAG, "onFailure "+t.getMessage());

                    //没有连上服务器,没有网络,
                    Toast.makeText(AddAddressActivity.this, "请检查你的网络", Toast.LENGTH_SHORT).show();

                }
            });



        }


    }

}
