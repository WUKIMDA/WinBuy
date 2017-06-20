package buy.win.com.winbuy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.LoginBean;
import buy.win.com.winbuy.model.net.RegisterBean;
import buy.win.com.winbuy.presenter.ApiService;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.ShareUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by demo on 2017/6/17.
 */

public class LonginAndRegisterActivity extends AppCompatActivity {

    @Bind(R.id.btn_back)
    ImageView mBtnBack;
    @Bind(R.id.mine_login)
    TextView mMineLogin;
    @Bind(R.id.mine_register)
    TextView mMineRegister;
    @Bind(R.id.username)
    EditText mUsername;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.ll_icon)
    LinearLayout mLlIcon;
    @Bind(R.id.one_key_login)
    TextView mOneKeyLogin;
    @Bind(R.id.activity_login)
    LinearLayout mActivityLogin;
    @Bind(R.id.btn_register_back)
    ImageView mBtnRegisterBack;
    @Bind(R.id.mine_register_login)
    TextView mMineRegisterLogin;
    @Bind(R.id.mine_redister_register)
    TextView mMineRedisterRegister;
    @Bind(R.id.register_username)
    EditText mRegisterUsername;
    @Bind(R.id.register_password)
    EditText mRegisterPassword;
    @Bind(R.id.confirm_password)
    EditText mConfirmPassword;
    @Bind(R.id.btn_register)
    Button mBtnRegister;
    @Bind(R.id.ll_register_icon)
    LinearLayout mLlRegisterIcon;
    @Bind(R.id.one_key_register)
    TextView mOneKeyRegister;
    @Bind(R.id.activity_register)
    LinearLayout mActivityRegister;
    private String mUsername1;
    private String mPassword1;
    private String mUsername2;
    private String mPassword2;
    private String mConfirmPassword2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginandregister);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.mine_register, R.id.btn_login, R.id.activity_login, R.id.btn_register_back, R.id.mine_register_login, R.id.btn_register, R.id.activity_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.mine_register:
                //隐藏登录 打开注册
                mActivityLogin.setVisibility(View.GONE);
                mActivityRegister.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_login:
                //请求数据
                loadLoginDataOnservice();
                break;
            case R.id.btn_register_back:
                finish();
                break;
            case R.id.mine_register_login:

                mActivityLogin.setVisibility(View.VISIBLE);
                mActivityRegister.setVisibility(View.GONE);
                break;

            case R.id.btn_register:
                //请求数据
                loadRegisterDataOnservice();
                break;

        }
    }

    private void loadRegisterDataOnservice() {

        mUsername2 = mRegisterUsername.getText().toString().trim();
        mPassword2 = mRegisterPassword.getText().toString().trim();
        mConfirmPassword2 = mConfirmPassword.getText().toString().trim();

        if(!mPassword2.equals(mConfirmPassword2)) {

            Toast.makeText(LonginAndRegisterActivity.this, "您两次填写的密码不一样,请重新输入", Toast.LENGTH_SHORT).show();
        }else {

            checkRegisterData();
        }

    }

    private void checkRegisterData() {

            ApiService service = RetrofitUtil.getApiService();

            service.register(mUsername2,mPassword2).enqueue(new Callback<RegisterBean>() {
                @Override
                public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                    if(response.isSuccessful()) {

                        RegisterBean registerBean = response.body();
                        if(!TextUtils.isEmpty(registerBean.getError())) {

                            Toast.makeText(LonginAndRegisterActivity.this, "您申请的用户名已经存在,请从新申请", Toast.LENGTH_SHORT).show();

                        }else {
                            //注册成功 保存数据userid  然后跳到主页
                            String userid = registerBean.getUserInfo().getUserid();
                            Toast.makeText(LonginAndRegisterActivity.this, userid, Toast.LENGTH_SHORT).show();

                            ShareUtils.setUserId(LonginAndRegisterActivity.this,userid);
                            ShareUtils.setUserName(LonginAndRegisterActivity.this,mUsername2);

                            //跳到主页
                            //Intent intent = new Intent(LonginAndRegisterActivity.this,MineHomeActivity.class);
                            //startActivity(intent);

                            finish();



                        }

                    }else {
                        Toast.makeText(LonginAndRegisterActivity.this, "服务器繁忙,请稍后再试", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterBean> call, Throwable t) {
                    Toast.makeText(LonginAndRegisterActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

                }
            });
        }


    private void loadLoginDataOnservice() {
        //拿到数据请求服务器
        mUsername1 = mUsername.getText().toString().trim();
        mPassword1 = mPassword.getText().toString().trim();

        //发送数据到服务器
        checkLoginData();
    }

    private void checkLoginData() {

       /* ApiService apkservice = new Retrofit
                .Builder()
                .baseUrl(Const.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);*/

        ApiService apkservice = RetrofitUtil.getApiService();


        apkservice.login(mUsername1, mPassword1).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.isSuccessful()) {

                    LoginBean loginBean = response.body();
                    if (!TextUtils.isEmpty(loginBean.getError())) {
                        Toast.makeText(LonginAndRegisterActivity.this, "账户或者密码错误", Toast.LENGTH_SHORT).show();
                    } else {

                        //返回数据成功
                        //String userid = loginBean.getUserInfo().getUserid();

                        Toast.makeText(LonginAndRegisterActivity.this, "检查用户名成功", Toast.LENGTH_SHORT).show();
                        //跳到主页界面
                        //finish();

                        String userid = loginBean.getUserInfo().getUserid();
                        ShareUtils.setUserId(LonginAndRegisterActivity.this, userid);
                        ShareUtils.setUserName(LonginAndRegisterActivity.this,mUsername1);
                        //Intent intent = new Intent(LonginAndRegisterActivity.this, MineHomeActivity.class);
                        //intent.putExtra("Lusername",mUsername1);
                        //startActivity(intent);


                        finish();

                    }


                } else {
                    //返回数据失败 404 500 505 话费
                    Toast.makeText(LonginAndRegisterActivity.this, "服务器正在维修中,请稍候", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

                //没有连上服务器,没有网络,
                Toast.makeText(LonginAndRegisterActivity.this, "请检查你的网络", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

