package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CommentDataBean;
import buy.win.com.winbuy.model.net.CommodityProductBean;
import buy.win.com.winbuy.model.net.ErrorBean;
import buy.win.com.winbuy.presenter.AddCartPresenter;
import buy.win.com.winbuy.presenter.CheckoutPresent;
import buy.win.com.winbuy.presenter.CommentPresenter;
import buy.win.com.winbuy.presenter.CommodityProductPresenter;
import buy.win.com.winbuy.presenter.ScrollViewContainer;
import buy.win.com.winbuy.utils.Constans;
import buy.win.com.winbuy.utils.MyImageLoader;
import buy.win.com.winbuy.utils.RetrofitUtil;
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
    @Bind(R.id.iv_good_detai_shop)
    ImageView mIvGoodDetaiShop;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        look_all_comment = (LinearLayout) findViewById(R.id.look_all_comment);
        listview_comment = (ListView) findViewById(R.id.listview_comment);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);


        Intent intent = getIntent();
        if (intent != null) {
            String pId = intent.getStringExtra("pId");
        }
//        loadService(pId);
        loadService();


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

    @Override
    protected void onStart() {
        super.onStart();
        //如果收藏,回显


    }


    //private void loadService(String pId) {
    public void loadService() {
        CommodityProductPresenter commodityProductPresenter = new CommodityProductPresenter(this);
        //TODO:模拟加载第pId是"1"的商品
//        commodityProductPresenter.loadCommdityProductData(pId);
        commodityProductPresenter.loadCommdityProductData("1");

        CommentPresenter commentPresenter = new CommentPresenter();
        //TODO：模拟
//        commentPresenter.loadCommentData(pId,page,pageNum);
        commentPresenter.loadCommentData("1", "1", "10");
        //后续EventBus自动获取数据后onEventMainThread()

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CommentDataBean commentDataLists) {
//        List<CommentDataBean.CommentBean> comment = commentDataLists.getComment();
//        System.out.println("测试" + comment.toString());
        if (commentDataLists == null) {
            return;
        }
        int size = commentDataLists.getComment().size();
//        mTvCommdotyCommentCount.setText("商品评论(" + size + ")");
        mTvCommdotyCommentCount.setText("商品评论(" + 1 + ")");
        mCommentAdapter.setData(commentDataLists);


    }


    @OnClick({R.id.iv_good_detai_back, R.id.iv_good_detai_shop, R.id.iv_good_detai_share, R.id.tv_good_detail_shop_cart,
            R.id.tv_good_detail_buy, R.id.tv_good_detail_cate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_good_detai_back:
                finish();
                break;
            case R.id.iv_good_detai_shop:
                //进入购物车
                break;
            case R.id.iv_good_detai_share:
                //第三方分享
                break;
            case R.id.ll_good_detail_collect:
                //收藏:取反,GONE
                //favorites?userid=85915&pId=1  //TODO:用户ID
                pidFavorites();


                break;
            case R.id.tv_good_detail_shop_cart:
                //加入购物车
                //addCart?userId=20428&productId=2&productCount=2&propertyId=1
                AddCartPresenter addCartPresenter = new AddCartPresenter();
                //用户id,商品id,商品数量,商品属性默认颜色1 TODO
                //addCartPresenter.addCard("用户ID", pId, "1", "1");

                break;
            case R.id.tv_good_detail_buy:
                //checkCommit
                checkCommit();

                break;
            case R.id.tv_good_detail_cate:
                //产品分类选择,popw
                dialogShow();
                break;

        }
    }

    private void checkCommit() {
        CheckoutPresent checkoutPresent = new CheckoutPresent();
        //TODO:用户ID
//         checkoutPresent.upCheckout(userId,sku);
    }

    /**
     * 商品收藏
     */
    private void pidFavorites() {
        //TODO  userId
        RetrofitUtil.getApiService().upPidFavorites("用户ID", pId).enqueue(new Callback<ErrorBean>() {
            @Override
            public void onResponse(Call<ErrorBean> call, Response<ErrorBean> response) {
                if (response.isSuccessful()) {
                    final ErrorBean body = response.body();
                    UiUtils.postTask(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CommodityActivity.this, body.getError(), Toast.LENGTH_SHORT).show();
                        }
                    });
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
    //KIMDA:详细描述：product/description?pId=2


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

    }


    private String mSelectSize;
    private String mSelectColor;
    /**
     * 对话框操作事件
     */
    private void initDialogEvent() {
        Glide.with(this).load(Constans.URL_HOST + imageIndex0).into(mIvCommodity);
        mPropertySize.addItemViews(mSizeLists, PropertyViewGroup.BTN_MODE);
        mPropertyColor.addItemViews(mColorLists, PropertyViewGroup.TEV_MODE);

        mPropertySize.setGroupClickListener(new PropertyViewGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int item) {
                mSelectSize = mSizeLists.get(item);
            }
        });

        mPropertyColor.setGroupClickListener(new PropertyViewGroup.OnGroupItemClickListener() {

            @Override
            public void onGroupItemClick(int item) {
                //TODO保存数据
                mSelectColor = mColorLists.get(item);
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

                if (mCommodityCount >=mBuyLimit) {
                    return;
                }
                mCommodityCount++;
                dialogNum.setText("" + mCommodityCount);
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommodityDialog.dismiss();
                System.out.println("我选择了"+mSelectColor + mSelectSize +mCommodityCount);
            }
        });
    }

    /**整合sku商品属性的方法*/
    public void productPropertySelect(String key){
        if (mProductPropertyLists.contains(key)){
            //TODO
        }



    }

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
        System.out.println("bean"+ bean.toString());
        if (product == null) {
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        pId = String.valueOf(product.getId());

        //大图bigPics
        List<String> bigPicsLists = product.getBigPic();
        imgsUrl = new ArrayList<>();
        for (int i = 0; i < bigPicsLists.size(); i++) {
            imgsUrl.add(Constans.URL_HOST + bigPicsLists.get(i));
        }
        imgAdapter.addAll(imgsUrl);
        nlvImgs.setAdapter(imgAdapter);
        imgAdapter.notifyDataSetChanged();

        //top的详情图
        List<String> picsLists = product.getPics();
        imageIndex0 = picsLists.get(0);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        for (int i = 0; i < picsLists.size(); i++) {
            url_maps.put("" + i, Constans.URL_HOST + picsLists.get(i));
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
        mTvVipPrice.setText("会员价:" + product.getPrice());
        mTvGoodDetailDiscount.setText("市场价:" + product.getMarketPrice());

        //商品属性
        mProductPropertyLists = product.getProductProperty();
        int pSize = mProductPropertyLists.size();

        mColorLists = new ArrayList<>();
        mSizeLists = new ArrayList<>();

        for (int i = 0; i < pSize; i++) {
            CommodityProductBean.ProductBean.ProductPropertyBean productPropertyBean = mProductPropertyLists.get(i);
            if ("颜色".equals(productPropertyBean.getK())) {
                mColorLists.add(productPropertyBean.getV());
            } else {//尺码
                mSizeLists.add(productPropertyBean.getV());
            }
        }
        Log.d("商品属性", mColorLists.toString() + mSizeLists.toString());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
