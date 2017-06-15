package buy.win.com.winbuy.presenter;

import java.util.Map;

import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.model.net.LoginBean;
import buy.win.com.winbuy.model.net.SearchBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by BUTTON on 2017-06-15.
 */

public interface ApiService {

    @GET("home")
    Call<HomeAllBean> getHomeData();

    @GET("search")
    Call<SearchBean> searchProduct(
            @Query("page") String page,
            @Query("pageNum") String pageNum,
            @Query("orderby") String orderby,
            @Query("keyword") String keyword
    );


    @FormUrlEncoded //POST请求中
    @POST("login")
    Call<LoginBean> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("login")
    Call<LoginBean> login2(@FieldMap Map<String, String> params);


    @GET("userinfo")
    Call<SearchBean> getUserInfo(@Header("userid") String userid);


}
