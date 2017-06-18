package buy.win.com.winbuy.presenter;

import buy.win.com.winbuy.model.net.AddressAllListBean;
import buy.win.com.winbuy.model.net.AddressBean;
import buy.win.com.winbuy.model.net.AreaBean;
import buy.win.com.winbuy.model.net.BrandBean;
import buy.win.com.winbuy.model.net.CartAllBean;
import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.model.net.CommentDataBean;
import buy.win.com.winbuy.model.net.CommodityProductBean;
import buy.win.com.winbuy.model.net.ErrorBean;
import buy.win.com.winbuy.model.net.DelectBean;
import buy.win.com.winbuy.model.net.FavoritesBean;
import buy.win.com.winbuy.model.net.HelpBean;
import buy.win.com.winbuy.model.net.HelpDetailBean;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.model.net.HotSearchBean;
import buy.win.com.winbuy.model.net.InvoiceAllBean;
import buy.win.com.winbuy.model.net.LimitbuyBean;
import buy.win.com.winbuy.model.net.LoginBean;
import buy.win.com.winbuy.model.net.OrderDetailBean;
import buy.win.com.winbuy.model.net.RegisterBean;
import buy.win.com.winbuy.model.net.SaveAddressBean;
import buy.win.com.winbuy.model.net.SearchBean;
import buy.win.com.winbuy.model.net.SelectCartBean;
import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.model.net.TopicPlistBean;
import buy.win.com.winbuy.model.net.VersionAllBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by BUTTON on 2017-06-15.
 */

public interface ApiService {

    /**
     * 首页
     *
     * @return
     */
    @GET("home")
    Call<HomeAllBean> getHomeAllProduct();


    @GET("category")
    Call<CategoryAllBean> getCategoryAllProduct();


    @GET("selectCart")
    Call<SelectCartBean> getSelectCartProduct(@Query("userId") String userId);


    /**
     * 搜索
     *
     * @param keyword
     * @param page
     * @param pageNum
     * @param orderby
     * @return
     */
    @GET("search")
    Call<SearchBean> getSearchProduct(
            @Query("keyword") String keyword,
            @Query("page") String page,
            @Query("pageNum") String pageNum,
            @Query("orderby") String orderby
    );
    /**
     * 热门搜索字段
     *
     * @return
     */
    @GET("search/recommend")
    Call<HotSearchBean> getHotBrand();

    /**
     * 促销
     *
     * @param page
     * @param pageNum
     * @return
     */
    @GET("topic")
    Call<TopPicBean> getTopicAllProduct(
            @Query("page") String page,
            @Query("pageNum") String pageNum
    );

    //plist?page=1&pageNum=8&id=1210&orderby=saleDown
    @GET("brand")
    Call<BrandBean> getBrandProduct();


    //http://product?pId=1
    @GET("product")
    Call<CommodityProductBean> getCommodityProdectData(@Query("pId") String pId);


    @FormUrlEncoded //POST请求中
    @POST("login")
    Call<LoginBean> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded //POST请求中
    @POST("register")
    Call<LoginBean> regist(@Field("username") String username, @Field("password") String password);

    @GET("addCart")//http://localhost:8080/market/addCart?userId=20428&productId=2&productCount=2&propertyId=1
    Call<CartAllBean> addCart(@Query("userId") String userId, @Query("productId") String productId, @Query("productCount") String productCount, @Query("propertyId") String propertyId);

    @GET("description")
    Call<String> descriptionData(@Query("pId") String pId);

    @GET("product/comment")
    Call<CommentDataBean> commentLoad(@Query("pId")String pId, @Query("page")String page, @Query("pageNum")String pageNum);


    /**
     * 发票
     * @return
     */
    @GET("invoice")
    Call<InvoiceAllBean> getInvoiceProduct();


    /**
     * 版本检测
     *
     * @return
     */
    @GET("version")
    Call<VersionAllBean> getVersionProduct();


    /**
     * 收藏夹
     *
     * @param userid
     * @param page
     * @param pageNum
     * @return
     */
    @GET("favorites")
    Call<FavoritesBean> getFavoriteProduct(
            @Header("userid") String userid,
            @Query("page") String page,
            @Query("pageNum") String pageNum

    );


    /**
     * 订单详情
     *
     * @param userid
     * @param orderId
     * @return
     */
    @GET("orderdetail")
    Call<OrderDetailBean> getOrderDetailProduct(@Header("userid") String userid,
                                                @Query("orderId") String orderId);


    /**
     * 地址列表
     *
     * @param userid
     * @return
     */
    @GET("addresslist")
    Call<AddressAllListBean> getAddressProduct(@Query("userid") String userid);

    @GET("userinfo")
    Call<SearchBean> getUserInfo(@Header("userid") String userid);

    /**
     * 删除地址
     *
     * @param userid
     * @param id
     * @return
     */
    @FormUrlEncoded //POST请求中
    @POST("addressdelete")
    Call addressDelete(@Header("userid") String userid, @Field("id") String id);

    /**
     * 帮助列表
     *
     * @return
     */
    @GET("help")
    Call<HelpBean> getHelpProduct();

    /**
     * 帮助内容获取
     *
     * @param id
     * @return
     */
    @GET("helpDetail")
    Call<HelpDetailBean> getHelpDetailProduct(@Query("id") String id);

    /**
     * 首页限时抢购
     */
    @GET("limitbuy")
    Call<LimitbuyBean> getLimitBuy(@Query("page") String page, @Query("pageNum") String pageNum);

    /**
     * 获取版本号
     */
    /*@GET("version")
    Call<UserVersionBean> getVersion();*/


    /**
     * 底部导航我的部分(包括 用户登录 地址管理 )  eason
     */
//    @FormUrlEncoded //POST请求中
//    @POST("login")//定义
//    Call<LoginBean> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded //POST请求中
    @POST("register")//定义
    Call<RegisterBean> register(@Field("username") String username, @Field("password") String password);


    @GET("addresslist")
    Call<AddressBean> getAddressList(@Header("userid") String userid);

    @GET("addressarea")
    Call<AreaBean> getaddressarea(@Query("id") String id);

    @GET("addressdelete")
    Call<DelectBean> deleteAddress(@Query("id") int id);

    @FormUrlEncoded
    @POST("addresssave")//定义
    Call<SaveAddressBean> saveAddress(
            @Header("userid") String userid,
            @Field("name") String name,
            @Field("phoneNumber") String phoneNumber,
            @Field("province") String province,
            @Field("city") String city,
            @Field("addressArea") String addressArea,
            @Field("addressDetail") String addressDetail,
            @Field("zipCode") String zipCode,
            @Field("isDefault") String isDefault
    );

    /**
     * 专题商品列表
     */
    @GET("topic/plist")
    Call<TopicPlistBean> getTopicPlist(@Query("page") String page, @Query("pageNum") String pageNum, @Query("id") String id, @Query("orderby") String orderby);

    //商品收藏
    @GET("product/favorites")
    Call<ErrorBean> upPidFavorites(String userid, String pId);


    @FormUrlEncoded
    @POST("userid")
    Call<CheckoutAllBean> checkout(@Header("userid")String userid,@Field("sku")String sku);

    //新品上架
    @GET("newproduct")
    Call<TopicPlistBean> getNewProduct(@Query("page") String page, @Query("pageNum") String pageNum,@Query("orderby") String orderby);

    //热门单品
    @GET("hotproduct")
    Call<TopicPlistBean> getHotProduct(@Query("page") String page, @Query("pageNum") String pageNum,@Query("orderby") String orderby);

    //新品上架
    @GET("brand")
    Call<TopicPlistBean> getBrand();

}
