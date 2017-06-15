package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.ResponseInfo;
import buy.win.com.winbuy.utils.Constans;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 网络框架基类
 */
public abstract class BaseNetPresenter {


    public Retrofit mRetrofit;
    public ApiService mApiService;

    public BaseNetPresenter() {
        mRetrofit = new Retrofit.Builder().baseUrl(Constans.URL_HOST).addConverterFactory(GsonConverterFactory.create()).build();
        mApiService = mRetrofit.create(ApiService.class);
    }


    public Callback mCallBack = new Callback<ResponseInfo>() {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {//服务器有响应
            if (response.isSuccessful()) {//拿到数据
                ResponseInfo responseInfo = response.body();
                String data = responseInfo.getData();
                onSuccess(data);
            } else {//服务器出现异常
                onServerBug(response.code());
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {//服务器没响应
            onConnectError(t.getMessage());
        }
    };

    public abstract void onConnectError(String message);

    public abstract void onServerBug(int code);

    public abstract void onSuccess(String jsonData);


}
