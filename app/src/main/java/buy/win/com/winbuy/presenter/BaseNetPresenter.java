package buy.win.com.winbuy.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络框架基类
 */
public abstract class BaseNetPresenter<T> {



    public Callback mCallBack = new Callback<T>() {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {//服务器有响应
            if (response.isSuccessful()) {//拿到数据
                T body = response.body();
                textTemp = body.toString();
                onSuccess(body);
                Log.d("Text", "成功" + textTemp);


            } else {//服务器出现异常
                Log.d("Text", "失败");
                onServerBug(response.code());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {//服务器没响应
            onConnectError(t.getMessage());
        }
    };

    public abstract void onConnectError(String message);

    public abstract void onServerBug(int code);

    public abstract void onSuccess(T bean);


    //测试
    public String textTemp;

    public String getTextTemp() {
        return textTemp;
    }

}
