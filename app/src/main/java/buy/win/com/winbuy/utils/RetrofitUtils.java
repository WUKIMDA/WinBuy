package buy.win.com.winbuy.utils;

import buy.win.com.winbuy.presenter.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by demo on 2017/6/15.
 */

public class RetrofitUtils {

    public static ApiService getService() {

        ApiService apkservice = new Retrofit
                .Builder()
                //.client(client)
                .baseUrl(Const.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);

        return apkservice;
    }


}
