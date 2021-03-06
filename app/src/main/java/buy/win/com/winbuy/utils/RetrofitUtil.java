package buy.win.com.winbuy.utils;

import buy.win.com.winbuy.presenter.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dada on 2017/6/14.
 */


public class RetrofitUtil {

    public static Retrofit getRetrofit() {
        return new Retrofit
                .Builder()
                .baseUrl(Constant.URL_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

}
