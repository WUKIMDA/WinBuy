package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CommentDataBean;
import buy.win.com.winbuy.model.net.CommodityProductBean;
import buy.win.com.winbuy.model.net.ErrorBean;
import buy.win.com.winbuy.model.net.FavoriteBean;
import buy.win.com.winbuy.presenter.AddCartPresenter;
import buy.win.com.winbuy.presenter.CommentPresenter;
import buy.win.com.winbuy.presenter.CommodityProductPresenter;
import buy.win.com.winbuy.presenter.ScrollViewContainer;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.utils.MyImageLoader;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.utils.StatusBarUtil;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.view.adapter.CommentAdapter;
import buy.win.com.winbuy.view.commodityView.GradationScrollView;
import buy.win.com.winbuy.view.commodityView.NoScrollListView;
import buy.win.com.winbuy.view.commodityView.PropertyViewGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BUTTON on 2017-06-16.
 * 选择属性---确定之后加到购物车,
 * 加入购物车是选择默认属性,立
 * 即购买携带数据跳转到结算页面
 */
public class CommodityActivity extends Activity implements GradationScrollView.ScrollViewListener {

    @Bind(R.id.scrollview)
    GradationScrollView scrollView;

    @Bind(R.id.slider)
    SliderLayout mSlider;
    @Bind(R.id.ll_good_detail)
    RelativeLayout llTitle;
    @Bind(R.id.ll_offset)
    LinearLayout llOffset;
    @Bind(R.id.iv_good_detai_collect_select)
    ImageView ivCollectSelect;//收藏选中
    @Bind(R.id.iv_good_detai_collect_unselect)
    ImageView ivCollectUnSelect;//收藏未选中
    @Bind(R.id.sv_container)
    ScrollViewContainer container;
    @Bind(R.id.tv_good_detail_title_good)
    TextView tvGoodTitle;
    @Bind(R.id.nlv_good_detial_imgs)
    NoScrollListView nlvImgs;//图片详情
    @Bind(R.id.custom_indicator)
    PagerIndicator mCustomIndicator;
    @Bind(R.id.tv_commdoty_name)
    TextView mTvCommdotyName;
    @Bind(R.id.tv_commdoty_buyLimit)
    TextView mTvCommdotyBuyLimit;
    @Bind(R.id.tv_limitPrice)
    TextView mTvLimitPrice;
    @Bind(R.id.tv_vipPrice)
    TextView mTvVipPrice;
    @Bind(R.id.tv_good_detail_discount)
    TextView mTvGoodDetailDiscount;
    @Bind(R.id.tv_commdoty_inventoryArea)
    TextView mTvCommdotyInventoryArea;
    @Bind(R.id.ll_good_detail_service)
    LinearLayout mLlGoodDetailService;
    @Bind(R.id.tv_good_detail_cate)
    TextView mTvGoodDetailCate;
    @Bind(R.id.tv_commdoty_commentCount)
    TextView mTvCommdotyCommentCount;
    @Bind(R.id.nlv_)
    NoScrollListView mNlv;
    @Bind(R.id.tv_good_detail_tuodong)
    TextView mTvGoodDetailTuodong;
    @Bind(R.id.tv_good_detail_daodi)
    TextView mTvGoodDetailDaodi;
    @Bind(R.id.iv_good_detai_back)
    ImageView mIvGoodDetaiBack;

    @Bind(R.id.iv_good_detai_share)
    ImageView mIvGoodDetaiShare;
    @Bind(R.id.tv_good_detail_shop)
    TextView mTvGoodDetailShop;
    @Bind(R.id.ll_good_detail_collect)
    LinearLayout mLlGoodDetailCollect;
    @Bind(R.id.tv_good_detail_shop_cart)
    TextView mTvGoodDetailShopCart;
    @Bind(R.id.tv_good_detail_buy)
    TextView mTvGoodDetailBuy;
    @Bind(R.id.ll_good_detail_bottom)
    LinearLayout mLlGoodDetailBottom;
    @Bind(R.id.bottom)
    LinearLayout mBottom;

    private QuickAdapter<String> imgAdapter;
    private List<String> imgsUrl;

    private int height;
    private int width;
    private ArrayList<String> mColorLists;
    private ArrayList<String> mSizeLists;
    private String imageIndex0;
    private int mLimitPrice;
    private String mName;
    private LinearLayout look_all_comment;
    private ListView listview_comment;
    private CommentAdapter mCommentAdapter;
    private String pId;
    private int mBuyLimit;
    private Dialog mCommodityDialog;
    private List<CommodityProductBean.ProductBean.ProductPropertyBean> mProductPropertyLists;

    private Map<String, String> skuMap = new HashMap<>();
    private String userId;
    private String mReplaceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        look_all_comment = (LinearLayout) findViewById(R.id.look_all_comment);
        listview_comment = (ListView) findViewById(R.id.listview_comment);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        //加载用户id
        userId = ShareUtils.getUserId(this, "");

        Intent intent = getIntent();
        if (intent != null) {
            pId = intent.getStringExtra("pId");
        }
        loadService(pId);
//        loadService();

        //透明状态栏
        StatusBarUtil.setTranslucentForImageView(this, llOffset);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) llOffset.getLayoutParams();
        params1.setMargins(0, -StatusBarUtil.getStatusBarHeight(this) / 4, 0, 0);
        llOffset.setLayoutParams(params1);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mSlider.getLayoutParams();
        params.height = getScreenHeight(this) * 2 / 3;
        mSlider.setLayoutParams(params);

        container = new ScrollViewContainer(getApplicationContext());


        mCommentAdapter = new CommentAdapter();
        listview_comment.setAdapter(mCommentAdapter);

        initImgDatas();

        initListeners();
    }


    private void loadService(String pId) {
        if (TextUtils.isEmpty(pId)) {
            Toast.makeText(getApplicationContext(), "请求商品id失败,请传商品id", Toast.LENGTH_SHORT).show();
            return;
        }
        CommodityProductPresenter commodityProductPresenter = new CommodityProductPresenter(this);

        commodityProductPresenter.loadCommdityProductData(pId);

        CommentPresenter commentPresenter = new CommentPresenter();
        commentPresenter.loadCommentData(pId, "1", "10");
        //后续EventBus自动获取数据后onEventMainThread()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CommentDataBean commentDataLists) {
        if (commentDataLists == null) {
            return;
        }
        List<CommentDataBean.CommentBean> comment = commentDataLists.getComment();

        if (comment.size() <= 0) {//没有评论
            look_all_comment.setVisibility(View.GONE);
            return;
        } else {
            look_all_comment.setVisibility(View.VISIBLE);
        }
        int size = commentDataLists.getComment().size();
//        mTvCommdotyCommentCount.setText("商品评论(" + size + ")");
        mTvCommdotyCommentCount.setText("商品评论(" + 1 + ")");
        mCommentAdapter.setData(commentDataLists);
    }


    @OnClick({R.id.iv_good_detai_back, R.id.iv_good_detai_share,R.id.ll_good_detail_collect, R.id.tv_good_detail_shop_cart,
            R.id.tv_good_detail_buy, R.id.tv_good_detail_cate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_good_detai_back:
                finish();
                break;
            case R.id.iv_good_detai_share:
                //第三方分享
                break;
            case R.id.ll_good_detail_collect:
                if (TextUtils.isEmpty(userId)){
                    dialogLogin();
                    break;
                }
                //收藏:取反,GONE
                pidFavorites();
                break;
            case R.id.tv_good_detail_shop_cart:
                //加入购物车
                //addCart?userId=20428&productId=2&productCount=2&propertyId=1
                AddCartPresenter addCartPresenter = new AddCartPresenter();
                //用户id,商品id,商品数量,商品属性默认颜色1 TODO
                if (TextUtils.isEmpty(userId) && TextUtils.isEmpty(pId)) {
                    dialogLogin();
                    break;
                }
                addCartPresenter.addCard(userId, pId, "1", "1");

                break;
            case R.id.tv_good_detail_buy:
                //立即购买
                //checkCommit
                orderDetail();
                break;
            case R.id.tv_good_detail_cate:
                //产品分类选择,popw
                dialogShow();
                break;

        }
    }

    private void orderDetail() {
        //TODO:用户ID
        if (TextUtils.isEmpty(userId)) {
//            Toast.makeText(getApplicationContext(), "请登录在试", Toast.LENGTH_SHORT).show();
            dialogLogin();
            return;
        }
        //如果用户没有选择过属性,默认
        if (TextUtils.isEmpty(sku)) {
            //pid , 默认: 商品数量,颜色,尺寸
            sku = pId + ":" + "1" + ":" + "1" + "," + "3";
        }
        //单个商品立即购买Intent传输
        Intent intent = new Intent(this, CheckoutActivity.class);
//        intent.putExtra("userId",userId);
        intent.putExtra("sku", sku);
        startActivity(intent);
        Log.d("立即购买", sku + "跳转结算中心");

    }

    /**
     * 商品收藏
     */
    private void pidFavorites() {
        RetrofitUtil.getApiService().upPidFavorites(userId, pId).enqueue(new Callback<ErrorBean>() {
            @Override
            public void onResponse(Call<ErrorBean> call, Response<ErrorBean> response) {
                if (response.isSuccessful()) {
                    final ErrorBean body = response.body();
                    String bodyResponse = body.getResponse();
                    String error = body.getError();
                    if (("当前商品已经添加过收藏".equals(error)) || ("addfavorites".equals(bodyResponse))) {
                        ivCollectSelect.setVisibility(View.VISIBLE);
                        ivCollectUnSelect.setVisibility(View.GONE);
                    }
                    if ("还未登陆".equals(error)) {
                        UiUtils.postTask(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CommodityActivity.this, "请登陆", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ErrorBean> call, Throwable t) {

            }
        });
    }


    private void dialogShow() {

        mCommodityDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.dialog_commodity, null);
        mCommodityDialog.setContentView(root);

        Window dialogWindow = mCommodityDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//		lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//		lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCommodityDialog.setCanceledOnTouchOutside(true);
        mCommodityDialog.show();

        initDialog(root);
        initDialogEvent();

    }

    public ImageView mIvCommodity;
    public TextView mTvCommdityName;
    public TextView mTvCommdityPrice;
    public PropertyViewGroup mPropertyColor;
    public PropertyViewGroup mPropertySize;
    public Button mBtnSubmit;
    public TextView reduceNum;
    EditText dialogNum;
    TextView addNumber;

    private void initDialog(LinearLayout root) {
        this.mIvCommodity = (ImageView) root.findViewById(R.id.iv_commodity);
        this.mTvCommdityName = (TextView) root.findViewById(R.id.tv_commdity_name);
        this.mTvCommdityPrice = (TextView) root.findViewById(R.id.tv_commdity_price);
        this.mPropertyColor = (PropertyViewGroup) root.findViewById(R.id.property_color);
        this.mPropertySize = (PropertyViewGroup) root.findViewById(R.id.property_size);
        this.mBtnSubmit = (Button) root.findViewById(R.id.btn_submit);
        addNumber = (TextView) root.findViewById(R.id.dialog_increaseNum);
        dialogNum = (EditText) root.findViewById(R.id.dialog_num);
        reduceNum = (TextView) root.findViewById(R.id.dialog_reduceNum);

        mTvCommdityName.setText(mName);
        mTvCommdityPrice.setText("￥" + mLimitPrice);

        //回显或初始化数量
        dialogNum.setText("" + mCommodityCount);

        //TODO    回显失败
        if (!TextUtils.isEmpty(mSelectColor) && mColorLists != null) {
            for (int i = 0; i < mColorLists.size(); i++) {
                //颜色按钮选中
                if (mSelectColor.equals(mColorLists.get(i))) {
                    mPropertyColor.viewSelect(i);
                }
            }
        }
        if (!TextUtils.isEmpty(mSelectSize) && mSizeLists != null) {
            for (int i = 0; i < mSizeLists.size(); i++) {
                if (mSelectSize.equals(mSizeLists.get(i))) {
                    mPropertySize.viewSelect(i);
                }
            }
        }

    }



    private String mSelectSize;
    private String mSelectColor;

    /**
     * 对话框操作事件
     */
    private void initDialogEvent() {

        Glide.with(this).load(imageIndex0).into(mIvCommodity);

        mPropertySize.addItemViews(mSizeLists, PropertyViewGroup.BTN_MODE);
        mPropertyColor.addItemViews(mColorLists, PropertyViewGroup.TEV_MODE);

        mPropertySize.setGroupClickListener(new PropertyViewGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int item) {
                mSelectSize = skuMap.get(mSizeLists.get(item));
            }
        });

        mPropertyColor.setGroupClickListener(new PropertyViewGroup.OnGroupItemClickListener() {

            @Override
            public void onGroupItemClick(int item) {
                mSelectColor = skuMap.get(mColorLists.get(item));
            }

        });

        reduceNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCommodityCount == 1) {
                    return;
                }
                mCommodityCount--;
                dialogNum.setText("" + mCommodityCount);
            }
        });

        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCommodityCount >= mBuyLimit) {
                    return;
                }
                mCommodityCount++;
                dialogNum.setText("" + mCommodityCount);
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mSelectColor) || TextUtils.isEmpty(mSelectSize)) {
                    return;
                }
                mCommodityDialog.dismiss();
                sku = pId + ":" + mCommodityCount + ":" + mSelectColor + "," + mSelectSize;
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    String sku;

    /**
     * 属性操作商品的数量
     */
    int mCommodityCount = 1;


    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private void initImgDatas() {
        width = getScreenWidth(getApplicationContext());

        imgAdapter = new QuickAdapter<String>(this, R.layout.adapter_good_detail_imgs) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                ImageView iv = helper.getView(R.id.iv_adapter_good_detail_img);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
                params.width = width;
                params.height = width / 2;
                iv.setLayoutParams(params);
                MyImageLoader.getInstance().displayImageCen(getApplicationContext(), item, iv, width, width / 2);
            }
        };

    }

    private void initListeners() {

        ViewTreeObserver vto = mSlider.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = mSlider.getHeight();

                scrollView.setScrollViewListener(CommodityActivity.this);
            }
        });
    }

    /*** 滑动监听 */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            llTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            tvGoodTitle.setTextColor(Color.argb((int) alpha, 1, 24, 28));
            llTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }

    public void onSuccess(CommodityProductBean bean) {

        CommodityProductBean.ProductBean product = bean.getProduct();
        System.out.println("bean" + bean.toString());
        if (product == null) {
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        pId = String.valueOf(product.getId());

        //大图bigPics
        List<String> bigPicsLists = product.getBigPic();
        imgsUrl = new ArrayList<>();
        //判空
        bigPicsSizeCheck(bigPicsLists);
        for (int i = 0; i < bigPicsLists.size(); i++) {
            imgsUrl.add(Constant.URL_HOST + bigPicsLists.get(i));
        }
        mReplaceUrl = imgsUrl.get(0);
        imgAdapter.addAll(imgsUrl);
        nlvImgs.setAdapter(imgAdapter);
        imgAdapter.notifyDataSetChanged();

        HashMap<String, String> url_maps = new HashMap<String, String>();
        //top的详情图
        List<String> picsLists = product.getPics();
        //对商品top图判空
        if (picsLists.size() <= 0) {//没有商品头图片
//            url_maps.put("" + 0, Constant.URL_HOST +"images/product/detail/bigcar1.jpg");
            url_maps.put("" + 0, mReplaceUrl);
            imageIndex0 = mReplaceUrl;
        } else {
            for (int i = 0; i < picsLists.size(); i++) {
                url_maps.put("" + i, Constant.URL_HOST + picsLists.get(i));
            }
            imageIndex0 = url_maps.get("0");
        }
        for (String desc : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(url_maps.get(desc));
            mSlider.addSlider(textSliderView);
            mSlider.stopAutoCycle();
        }


        //基本数据填充
        mName = product.getName();
        mTvCommdotyName.setText(mName);
        mBuyLimit = product.getBuyLimit();
        mTvCommdotyBuyLimit.setText("限购:" + mBuyLimit);

        mTvCommdotyInventoryArea.setText(product.getInventoryArea());
        mLimitPrice = product.getLimitPrice();
        mTvLimitPrice.setText("抢购价:￥" + mLimitPrice);
        mTvVipPrice.setText("会员价:￥" + product.getPrice());
        mTvGoodDetailDiscount.setText("市场价:" + product.getMarketPrice());

        //商品属性
        mProductPropertyLists = product.getProductProperty();
        int pSize = mProductPropertyLists.size();

        mColorLists = new ArrayList<>();
        mSizeLists = new ArrayList<>();

        for (int i = 0; i < pSize; i++) {
            CommodityProductBean.ProductBean.ProductPropertyBean productPropertyBean = mProductPropertyLists.get(i);
            if ("颜色".equals(productPropertyBean.getK())) {
                mColorLists.add("" + productPropertyBean.getV());
            } else {//尺码
                mSizeLists.add("" + productPropertyBean.getV());
            }
            skuMap.put(productPropertyBean.getV(), "" + productPropertyBean.getId());
        }
        Log.d("商品属性", "=====" + mColorLists.toString() + mSizeLists.toString());
    }

    private void bigPicsSizeCheck(List<String> bigPicsLists) {
        if (bigPicsLists.size() <= 0) {
            imgsUrl.add(Constant.URL_HOST + "/images/product/detail/bigcar1.jpg");
            imgsUrl.add(Constant.URL_HOST + "/images/product/detail/bigcar2.jpg");
            imgsUrl.add(Constant.URL_HOST + "/images/product/detail/bigcar3.jpg");
            imgsUrl.add(Constant.URL_HOST + "/images/product/detail/bigcar4.jpg");
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        startFavorityService();
    }

    List<String> favoritesPidLists = new ArrayList<>();
    private void startFavorityService() {
        RetrofitUtil.getApiService().getFavorite(userId,"1","10").enqueue(new Callback<FavoriteBean>() {
            @Override
            public void onResponse(Call<FavoriteBean> call, Response<FavoriteBean> response) {
                if (response.isSuccessful()){
                    FavoriteBean body = response.body();
                    List<FavoriteBean.ProductListBean> productList = body.getProductList();
                    for (int i = 0; i < productList.size(); i++) {
                        FavoriteBean.ProductListBean productListBean = productList.get(i);
                        String id = productListBean.getId();
                        favoritesPidLists.add(id);
                    }
                    if (favoritesPidLists.contains(pId)){
                        ivCollectSelect.setVisibility(View.VISIBLE);
                        ivCollectUnSelect.setVisibility(View.GONE);
                    }else{
                        ivCollectSelect.setVisibility(View.GONE);
                        ivCollectUnSelect.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<FavoriteBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void dialogLogin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage("亲,要先登录哦030 ").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(CommodityActivity.this, LonginAndRegisterActivity.class);
                startActivity(intent);
            }
        }).create().show();
    }

}
